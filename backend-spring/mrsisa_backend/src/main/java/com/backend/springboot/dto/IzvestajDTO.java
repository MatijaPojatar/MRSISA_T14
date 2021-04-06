package com.backend.springboot.dto;

public class IzvestajDTO {
	String text;

	public IzvestajDTO() {
		super();
	}

	public IzvestajDTO(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
