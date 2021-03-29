package com.backend.springboot.domain;

public class Lek {
	private String naziv,sastav,proizvodjac,napomena;
	private int sifra;
	private RezimIzdavanja rezimIzdavanja;
	private OblikLeka oblikLeka;
	private VrstaLeka vrstaLeka;
	
	public Lek() {
		super();
	}

	public Lek(int sifra, String naziv, String sastav, String proizvodjac, String napomena,
			RezimIzdavanja rezimIzdavanja, OblikLeka oblikLeka, VrstaLeka vrstaLeka) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.sastav = sastav;
		this.proizvodjac = proizvodjac;
		this.napomena = napomena;
		this.rezimIzdavanja = rezimIzdavanja;
		this.oblikLeka = oblikLeka;
		this.vrstaLeka = vrstaLeka;
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

	public String getSastav() {
		return sastav;
	}

	public void setSastav(String sastav) {
		this.sastav = sastav;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public RezimIzdavanja getRezimIzdavanja() {
		return rezimIzdavanja;
	}

	public void setRezimIzdavanja(RezimIzdavanja rezimIzdavanja) {
		this.rezimIzdavanja = rezimIzdavanja;
	}

	public OblikLeka getOblikLeka() {
		return oblikLeka;
	}

	public void setOblikLeka(OblikLeka oblikLeka) {
		this.oblikLeka = oblikLeka;
	}

	public VrstaLeka getVrstaLeka() {
		return vrstaLeka;
	}

	public void setVrstaLeka(VrstaLeka vrstaLeka) {
		this.vrstaLeka = vrstaLeka;
	}
	
	
}
