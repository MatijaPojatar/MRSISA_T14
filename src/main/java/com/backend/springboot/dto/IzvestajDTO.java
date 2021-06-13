package com.backend.springboot.dto;

import java.util.ArrayList;
import java.util.List;

public class IzvestajDTO {
	String text;
	List<LekUIzvestajuDTO> lekovi=new ArrayList<LekUIzvestajuDTO>();

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

	public List<LekUIzvestajuDTO> getLekovi() {
		return lekovi;
	}

	public void setLekovi(List<LekUIzvestajuDTO> lekovi) {
		this.lekovi = lekovi;
	}
	
	
	
	
}
