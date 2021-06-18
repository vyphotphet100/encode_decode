package com.encoding.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Utils {

	public static String base64ToBinaryString(String base64) {
		String binStr = "";
		for (char ch : base64.toCharArray()) {
			String subBinStr = Integer.toBinaryString(ch);
			while (subBinStr.length() != 8)
				subBinStr = "0" + subBinStr;
			binStr += subBinStr;
		}
		return binStr;
	}
	
	public static String binaryStringToBase64(String binStr) {
		String base64 = "";
		while (!binStr.equals("")) {
			String subStr = binStr.substring(0, 8);
			binStr = binStr.substring(8);
			char ch = (char)Integer.parseInt(subStr, 2);
			base64 += String.valueOf(ch);
		}
		return base64;
	}
	
	public static String removeDoubleSlash(String str) {
		while (str.contains("//"))
			str = str.replace("//", "/");
		return str;
	}

	@SuppressWarnings("resource")
	public static byte[] getByteBySource(String source) {
		try {
			source = Utils.removeDoubleSlash(source);
			InputStream stream = new FileInputStream(source);
			if (stream != InputStream.nullInputStream()) {
				byte[] res = stream.readAllBytes();
				stream.close();
				return res;
			}
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
