package com.backend.springboot.domain;

public class Upit {
	String id, idLeka;
	Double kolicina;

	public Upit() {
		// TODO Auto-generated constructor stub
	}

	public Upit(String id, String idLeka, Double kolicina) {
		super();
		this.id = id;
		this.idLeka = idLeka;
		this.kolicina = kolicina;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
