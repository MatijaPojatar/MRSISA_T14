package com.backend.springboot.domain;

import java.util.Date;

public abstract class FarmaceutDermatologAbstract extends Osoba{
	private Date pocetakRadnogVremena,krajRadnogVremena;

	public Date getPocetakRadnogVremena() {
		return pocetakRadnogVremena;
	}

	public void setPocetakRadnogVremena(Date pocetakRadnogVremena) {
		this.pocetakRadnogVremena = pocetakRadnogVremena;
	}

	public Date getKrajRadnogVremena() {
		return krajRadnogVremena;
	}

	public void setKrajRadnogVremena(Date krajRadnogVremena) {
		this.krajRadnogVremena = krajRadnogVremena;
	}

	public FarmaceutDermatologAbstract() {
		super();
	}

	public FarmaceutDermatologAbstract(int id, String ime, String prezime, String mail, String username,
			String password, String adresa, String grad, String drzava, String brojTelefona, Pol pol,
			Date datumRodjenja, boolean promenjenaLozinka, Uloga uloga,Date pocetak,Date kraj) {
		super(id, ime, prezime, mail, username, password, adresa, grad, drzava, brojTelefona, pol, datumRodjenja,
				promenjenaLozinka, uloga);
		this.krajRadnogVremena=kraj;
		this.pocetakRadnogVremena=pocetak;
	}
	
	
	
	
}
