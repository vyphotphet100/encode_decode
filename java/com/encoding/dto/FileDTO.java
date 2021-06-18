package com.encoding.dto;

public class FileDTO {

	private String fileName;
	private String fileType;
	private String base64;
	private String encodedStr;
	private String keyTree;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getBase64() {
		return base64;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	public String getKeyTree() {
		return keyTree;
	}
	public void setKeyTree(String keyTree) {
		this.keyTree = keyTree;
	}
	public String getEncodedStr() {
		return encodedStr;
	}
	public void setEncodedStr(String encodedStr) {
		this.encodedStr = encodedStr;
	}
	
	
}
