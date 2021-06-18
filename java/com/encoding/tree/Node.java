package com.encoding.tree;

public class Node {

	private Integer data;
	private Node left;
	private Node right;
	private Integer height;
	
	public Node(int i) {
		this.data = i;
		this.height = 0;
		this.left = null;
		this.right = null;
	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
	
	
}
