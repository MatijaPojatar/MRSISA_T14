package com.backend.springboot.domain;

import java.util.Date;

public class Farmaceut extends FarmaceutDermatologAbstract{

	public Farmaceut() {
		super();
	}

	public Farmaceut(int id, String ime, String prezime, String mail, String username, String password, String adresa,
			String grad, String drzava, String brojTelefona, Pol pol, Date datumRodjenja, boolean promenjenaLozinka,
			Uloga uloga, Date pocetak, Date kraj) {
		super(id, ime, prezime, mail, username, password, adresa, grad, drzava, brojTelefona, pol, datumRodjenja,
				promenjenaLozinka, uloga, pocetak, kraj);
	}

	
	
}
