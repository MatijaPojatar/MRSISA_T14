package com.backend.springboot.dto;

import com.backend.springboot.domain.Apoteka;

public class MinimalApotekaDTO {
	private Integer id;
	private String naziv;
	
	
	public MinimalApotekaDTO(Integer id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	public MinimalApotekaDTO(Apoteka a) {
		this.id = a.getId();
		this.naziv = a.getNaziv();
	}

	public MinimalApotekaDTO() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	
}

