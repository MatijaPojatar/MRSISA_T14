package com.backend.springboot.dto;

public class ApotekaCenaDTO {
	public String nazivApoteke;
	public Integer idApoteke;
	public Double cenaLekova;
	
	
	public ApotekaCenaDTO() {
		super();
	}
	public ApotekaCenaDTO(String nazivApoteke, Integer idApoteke, Double cenaLekova) {
		super();
		this.nazivApoteke = nazivApoteke;
		this.idApoteke = idApoteke;
		this.cenaLekova = cenaLekova;
	}
	public String getNazivApoteke() {
		return nazivApoteke;
	}
	public void setNazivApoteke(String nazivApoteke) {
		this.nazivApoteke = nazivApoteke;
	}
	public Integer getIdApoteke() {
		return idApoteke;
	}
	public void setIdApoteke(Integer idApoteke) {
		this.idApoteke = idApoteke;
	}
	public Double getCenaLekova() {
		return cenaLekova;
	}
	public void setCenaLekova(Double cenaLekova) {
		this.cenaLekova = cenaLekova;
	}
	
	
}
