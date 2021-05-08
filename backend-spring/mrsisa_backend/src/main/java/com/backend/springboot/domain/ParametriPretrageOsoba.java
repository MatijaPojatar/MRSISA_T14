package com.backend.springboot.domain;

public class ParametriPretrageOsoba {
	private String ime, prezime, grad, drzava;
	private Pol pol;
	
	public ParametriPretrageOsoba() {
		super();
	}
	public ParametriPretrageOsoba(String ime, String prezime, String grad, String drzava, Pol pol) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.grad = grad;
		this.drzava = drzava;
		this.pol = pol;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	public Pol getPol() {
		return pol;
	}
	public void setPol(Pol pol) {
		this.pol = pol;
	}
	
}
