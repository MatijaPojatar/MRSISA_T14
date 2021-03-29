package com.backend.springboot.domain;

import java.util.Date;

public abstract class Termin {
	private Date pocetak,kraj;
	private String izvestaj;
	//Pacijent,Apoteka fale
	
	
	
	public Termin() {
		super();
	}



	public Termin(Date pocetak, Date kraj) {
		super();
		this.pocetak = pocetak;
		this.kraj = kraj;
	}



	public Date getPocetak() {
		return pocetak;
	}



	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}



	public Date getKraj() {
		return kraj;
	}



	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}



	public String getIzvestaj() {
		return izvestaj;
	}



	public void setIzvestaj(String izvestaj) {
		this.izvestaj = izvestaj;
	}
	
	
	
	
}
