package com.backend.springboot.domain;

import java.util.Date;

/*
 * 
 * Osoba kasnije moze da se nasledi kao ostali tipovi, ovo je probna verzija
 */

public class Osoba {
	private int id;
	private String ime, prezime, mail, password, adresa, grad, drzava, brojTelefona;
	private Pol pol;
	private Date datumRodjenja;
	private boolean promenjenaLozinka;
	
	public Osoba() {
		super();
	}

	public Osoba(int id, String ime, String prezime, String mail, String password, String adresa, String grad,
			String drzava, String brojTelefona, Pol pol, Date datumRodjenja, boolean promenjenaLozinka) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.mail = mail;
		this.password = password;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.brojTelefona = brojTelefona;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;
		this.promenjenaLozinka = promenjenaLozinka;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
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

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public boolean isPromenjenaLozinka() {
		return promenjenaLozinka;
	}

	public void setPromenjenaLozinka(boolean promenjenaLozinka) {
		this.promenjenaLozinka = promenjenaLozinka;
	}
}
