package com.backend.springboot.domain;

public class LekIzNarudzbenice {
	String idLeka;
	Double kolicina;

	public LekIzNarudzbenice() {
	}

	public LekIzNarudzbenice(String idLeka, Double kolicina) {
		super();
		this.idLeka = idLeka;
		this.kolicina = kolicina;
	}

	public String getIdLeka() {
		return idLeka;
	}

	public void setIdLeka(String idLeka) {
		this.idLeka = idLeka;
	}

	public Double getKolicina() {
		return kolicina;
	}

	public void setKolicina(Double kolicina) {
		this.kolicina = kolicina;
	}

}
