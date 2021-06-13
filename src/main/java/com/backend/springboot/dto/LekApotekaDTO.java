package com.backend.springboot.dto;

import com.backend.springboot.domain.OblikLeka;

public class LekApotekaDTO {
	private Integer id, apotekaId;
	private String naziv, nazivApoteke;
	private OblikLeka oblikLeka;
	//private Double ocena;
	private Double cena;
	
	public LekApotekaDTO() {
		
	}

	public LekApotekaDTO(Integer id, Integer apotekaId, String naziv, String nazivApoteke, OblikLeka oblikLeka,
			Double cena) {
		super();
		this.id = id;
		this.apotekaId = apotekaId;
		this.naziv = naziv;
		this.nazivApoteke = nazivApoteke;
		this.oblikLeka = oblikLeka;
		this.cena = cena;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApotekaId() {
		return apotekaId;
	}

	public void setApotekaId(Integer apotekaId) {
		this.apotekaId = apotekaId;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getNazivApoteke() {
		return nazivApoteke;
	}

	public void setNazivApoteke(String nazivApoteke) {
		this.nazivApoteke = nazivApoteke;
	}

	public OblikLeka getOblikLeka() {
		return oblikLeka;
	}

	public void setOblikLeka(OblikLeka oblikLeka) {
		this.oblikLeka = oblikLeka;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}
}
