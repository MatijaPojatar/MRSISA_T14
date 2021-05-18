package com.backend.springboot.dto;

public class LekIzNarudzbeniceDTO {
	private String naziv;
	private Integer lekId;
	private Double kolicina;
	public LekIzNarudzbeniceDTO() {
		super();
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Integer getLekId() {
		return lekId;
	}
	public void setLekId(Integer lekId) {
		this.lekId = lekId;
	}
	public Double getKolicina() {
		return kolicina;
	}
	public void setKolicina(Double kolicina) {
		this.kolicina = kolicina;
	}
	
}
