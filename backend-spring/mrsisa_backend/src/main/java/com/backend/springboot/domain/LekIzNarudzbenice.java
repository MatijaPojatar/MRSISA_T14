package com.backend.springboot.domain;

public class LekIzNarudzbenice {
	int idLeka;
	Double kolicina;

	public LekIzNarudzbenice() {
	}

	public LekIzNarudzbenice(int idLeka, Double kolicina) {
		super();
		this.idLeka = idLeka;
		this.kolicina = kolicina;
	}

	public int getIdLeka() {
		return idLeka;
	}

	public void setIdLeka(int idLeka) {
		this.idLeka = idLeka;
	}

	public Double getKolicina() {
		return kolicina;
	}

	public void setKolicina(Double kolicina) {
		this.kolicina = kolicina;
	}

}
