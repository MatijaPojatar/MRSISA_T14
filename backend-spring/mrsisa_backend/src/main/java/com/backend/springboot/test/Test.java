package com.backend.springboot.test;

public class Test {
	
	private int id;
	private String text;
	
	public Test() {
		super();
	}
	public Test(int id, String text) {
		super();
		this.id = id;
		this.text = text;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	

}