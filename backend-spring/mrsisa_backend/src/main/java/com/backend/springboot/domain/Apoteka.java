package com.backend.springboot.domain;

import java.util.Collection;

public class Apoteka {
	private String naziv,sifra,adresa,grad,drzava,opis;
	private Collection<Lek> lekovi;

	public Apoteka() {
		super();
	}

	public Apoteka(String naziv, String sifra, String adresa, String grad, String drzava, String opis) {
		super();
		this.naziv = naziv;
		this.sifra = sifra;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.opis = opis;
	}
	
	public Apoteka(String naziv, String sifra, String adresa, String grad, String drzava, String opis, Collection<Lek> lekovi) {
		super();
		this.naziv = naziv;
		this.sifra = sifra;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.opis = opis;
		this.lekovi = lekovi;
	}

	public Collection<Lek> getLekovi() {
		return lekovi;
	}

	public void setLekovi(Collection<Lek> lekovi) {
		this.lekovi = lekovi;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	
}
