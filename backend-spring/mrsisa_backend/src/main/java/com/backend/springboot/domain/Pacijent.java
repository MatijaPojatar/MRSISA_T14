package com.backend.springboot.domain;

import java.util.Date;

public class Pacijent extends Osoba {
	private int brojPoena, penali;
	
	public Pacijent() {
		super();
	}

	public Pacijent(int id, String ime, String prezime, String mail, String password, String adresa, String grad,
			String drzava, String brojTelefona, Pol pol, Date datumRodjenja, boolean promenjenaLozinka, int brojPoena, int penali) {
		super(id, ime, prezime, mail, password, adresa, grad, drzava, brojTelefona, pol, datumRodjenja, promenjenaLozinka);
		this.brojPoena = brojPoena;
		this.penali = penali;
	}

	public int getBrojPoena() {
		return brojPoena;
	}

	public void setBrojPoena(int brojPoena) {
		this.brojPoena = brojPoena;
	}

	public int getPenali() {
		return penali;
	}

	public void setPenali(int penali) {
		this.penali = penali;
	}
}
