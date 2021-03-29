package com.backend.springboot.domain;

public class Upit {
	int id;
	int idLeka;
	Double kolicina;

	public Upit() {
		// TODO Auto-generated constructor stub
	}

	public Upit(int id, int idLeka, Double kolicina) {
		super();
		this.id = id;
		this.idLeka = idLeka;
		this.kolicina = kolicina;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
