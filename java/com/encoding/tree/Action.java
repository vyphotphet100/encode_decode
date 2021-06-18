package com.encoding.tree;

public class Action {

	public static Boolean insert(Node root, Node newNode) {
		Node curNode = root;
		int height=0;
		while (curNode != null) {
			if (newNode.getData() < curNode.getData()) {
				if (curNode.getLeft() == null) {
					height++;
					curNode.setLeft(newNode);
					root.setHeight(height);
					break;
				}
				else 
					curNode = curNode.getLeft();
			}
			else if (newNode.getData() > curNode.getData()) {
				if (curNode.getRight() == null){
					height++;
					curNode.setRight(newNode);
					root.setHeight(height);
					break;
				}
				else 
					curNode = curNode.getRight();
			}
			else 
				return false;
			height++;
		}
		
		return true;
	}
	
	public static Node createTreeFromString(String treeStr) {
		if (treeStr == null)
			return null;
		
		// get root node
		String dataStr = String.valueOf(treeStr.charAt(0)) + String.valueOf(treeStr.charAt(1));
		Integer dataInt = Integer.parseInt(dataStr);
		Node root = new Node(dataInt);
		
		for (int i=3; i<treeStr.length(); i+=3) {
			dataStr = String.valueOf(treeStr.charAt(i)) + String.valueOf(treeStr.charAt(i+1));
			dataInt = Integer.parseInt(dataStr);
			Action.insert(root, new Node(dataInt));
		}
		
		return root;
	}
	
	public static String search(Node root, int data) {
		Node curNode = root;
		String binStr = "";
		while (true) {
			if (data < curNode.getData()) {
				if (curNode.getLeft() == null) {
					return "Key is wrong.";
				}
				else {
					curNode = curNode.getLeft();
					binStr += "0";
				}
					
			}
			else if (data > curNode.getData()) {
				if (curNode.getRight() == null){
					return "Key is wrong.";
				}
				else {
					curNode = curNode.getRight();
					binStr += "1";
				}
			}
			else 
				return binStr;
		}
		
	}
}
