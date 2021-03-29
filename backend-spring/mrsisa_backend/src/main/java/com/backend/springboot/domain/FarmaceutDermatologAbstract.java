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

	public FarmaceutDermatologAbstract(int id, String ime, String prezime, String mail,
			String password, String adresa, String grad, String drzava, String brojTelefona, Pol pol,
			Date datumRodjenja, boolean promenjenaLozinka, Date pocetak,Date kraj) {
		super(id, ime, prezime, mail, password, adresa, grad, drzava, brojTelefona, pol, datumRodjenja,
				promenjenaLozinka);
		this.krajRadnogVremena=kraj;
		this.pocetakRadnogVremena=pocetak;
	}
	
	
	
	
}
