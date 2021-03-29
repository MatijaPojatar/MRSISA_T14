package com.backend.springboot.domain;

import java.util.Date;

public class Cena {
	private Date pocetakVazenja, krajVazenja;
	private Double cena;
	private int idLeka;
	public Cena() {
		// TODO Auto-generated constructor stub
	}
	public Cena(Date pocetakVazenja, Date krajVazenja, Double cena, int idLeka) {
		super();
		this.pocetakVazenja = pocetakVazenja;
		this.krajVazenja = krajVazenja;
		this.cena = cena;
		this.idLeka = idLeka;
	}
	public Date getPocetakVazenja() {
		return pocetakVazenja;
	}
	public void setPocetakVazenja(Date pocetakVazenja) {
		this.pocetakVazenja = pocetakVazenja;
	}
	public Date getKrajVazenja() {
		return krajVazenja;
	}
	public void setKrajVazenja(Date krajVazenja) {
		this.krajVazenja = krajVazenja;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	public int getIdLeka() {
		return this.idLeka;
	}
	public void setIdLeka(int idLeka) {
		this.idLeka = idLeka;
	}

}
