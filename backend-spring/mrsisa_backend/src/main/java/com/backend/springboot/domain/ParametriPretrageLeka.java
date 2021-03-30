package com.backend.springboot.domain;

public class ParametriPretrageLeka {
	private String naziv, proizvodjac;
	private int sifra;
	private RezimIzdavanja rezim;
	private OblikLeka oblik;
	private VrstaLeka vrsta;
	
	public ParametriPretrageLeka() {
		super();
	}
	public ParametriPretrageLeka(int sifra, String naziv, RezimIzdavanja rezim, OblikLeka oblik,
			VrstaLeka vrsta, String proizvodjac) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.rezim = rezim;
		this.oblik = oblik;
		this.vrsta = vrsta;
		this.proizvodjac = proizvodjac;
	}
	public String getProizvodjac() {
		return proizvodjac;
	}
	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
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
