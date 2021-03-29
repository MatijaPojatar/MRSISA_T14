package com.backend.springboot.domain;

public class ParametriPretrageRezervacije {
	private String naziv;
	private int sifra;
	private RezimIzdavanja rezim;
	private OblikLeka oblik;
	private VrstaLeka vrsta;
	
	public ParametriPretrageRezervacije() {
		super();
	}
	public ParametriPretrageRezervacije(int sifra, String naziv, RezimIzdavanja rezim, OblikLeka oblik,
			VrstaLeka vrsta) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.rezim = rezim;
		this.oblik = oblik;
		this.vrsta = vrsta;
	}
	public int getSifra() {
		return sifra;
	}
	public void setSifra(int sifra) {
		this.sifra = sifra;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public RezimIzdavanja getRezim() {
		return rezim;
	}
	public void setRezim(RezimIzdavanja rezim) {
		this.rezim = rezim;
	}
	public OblikLeka getOblik() {
		return oblik;
	}
	public void setOblik(OblikLeka oblik) {
		this.oblik = oblik;
	}
	public VrstaLeka getVrsta() {
		return vrsta;
	}
	public void setVrsta(VrstaLeka vrsta) {
		this.vrsta = vrsta;
	}
	
	
	
	
}
