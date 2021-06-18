package com.encoding.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.encoding.dto.FileDTO;
import com.encoding.service.IEncodeService;
import com.encoding.tree.Action;
import com.encoding.tree.Node;
import com.encoding.utils.Utils;


@Service
public class EncodeService implements IEncodeService {

	private final int bound = 10000;
	
	@Override
	public FileDTO encode(FileDTO fileDto) {
		Node root = Action.createTreeFromString(fileDto.getKeyTree());

		// get height of tree
		int height = root.getHeight();

		// convert base64 to binary string
		String binRestStr = "";
		String binStr = "";
		if (fileDto.getBase64().length() > bound) {
			binRestStr = fileDto.getBase64().substring(bound);
			binStr = Utils.base64ToBinaryString(fileDto.getBase64().substring(0, bound));
		}
		else 
			binStr = Utils.base64ToBinaryString(fileDto.getBase64());
		

		// encode
		Random rand = new Random();
		String encodedStr = "";
			
		for (int i = 0; i < binStr.length();) {
			Integer subBinLen = rand.nextInt(height + 1);
			if (subBinLen == 0)
				subBinLen = 1;

			String subBin = "";
			for (int k = i;; k++) {
				if (k < i + subBinLen && k < binStr.length())
					subBin += String.valueOf(binStr.charAt(k));
				else
					break;
			}

			Node curNode = root;
			for (char ch : subBin.toCharArray()) {
				if (ch == '0') {
					if (curNode.getLeft() != null) {
						curNode = curNode.getLeft();
						continue;
					} else {
						curNode = null;
						break;
					}
				}

				else if (ch == '1') {
					if (curNode.getRight() != null) {
						curNode = curNode.getRight();
						continue;
					} else {
						curNode = null;
						break;
					}
				}
			}

			if (curNode == null)
				encodedStr += "n" + subBinLen.toString() + subBin;
			else
				encodedStr += curNode.getData().toString();
			i += subBinLen;
		}

		// write out to file
		fileDto.setFileName(fileDto.getFileName() + "_en");
		fileDto.setEncodedStr(encodedStr + binRestStr);
		

		try {
			File file = new File("src/main/resources/" + fileDto.getFileName() + "." + fileDto.getFileType());
			file.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			writer.write(fileDto.getEncodedStr());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileDto;
	}

	@Override
	public FileDTO decode(FileDTO fileDto) {
		Node root = Action.createTreeFromString(fileDto.getKeyTree());

		String encodedStr = fileDto.getEncodedStr();
		String decodedStr = "";
		Boolean checkAddEncodedStr = false;
		for (int i = 0; i < encodedStr.length();) {
			if (encodedStr.charAt(i) != 'n' && isInteger(String.valueOf(encodedStr.charAt(i)))) {
				String dataNodeStr = String.valueOf(encodedStr.charAt(i)) + String.valueOf(encodedStr.charAt(i + 1));
				Integer dataNode = Integer.valueOf(dataNodeStr);
				decodedStr += Action.search(root, dataNode);
				i += 2;
			} else if (encodedStr.charAt(i) == 'n'){				
				Integer lenSubStr = Integer.valueOf(String.valueOf(encodedStr.charAt(i + 1)));
				String subStr = "";
				for (int k = 0; k < lenSubStr; k++)
					subStr += String.valueOf(encodedStr.charAt(i + 2 + k));

				decodedStr += subStr;
				i += lenSubStr + 2;
			} else {
				encodedStr = encodedStr.substring(i); 
				checkAddEncodedStr = true;
				break;
			}
		}

		// write out to file
		fileDto.setFileName(fileDto.getFileName() + "_de");
		if (checkAddEncodedStr)
			fileDto.setBase64(Utils.binaryStringToBase64(decodedStr) + encodedStr);
		else 
			fileDto.setBase64(Utils.binaryStringToBase64(decodedStr));

		byte[] data = Base64.getDecoder().decode(fileDto.getBase64());
		try (OutputStream stream = new FileOutputStream(
				"src/main/resources/" + fileDto.getFileName() + "." + fileDto.getFileType())) {
			stream.write(data);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileDto;
	}
	
	private Boolean isInteger(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
