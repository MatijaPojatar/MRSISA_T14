package com.backend.springboot.domain;

import java.util.Date;

public class Dermatolog extends FarmaceutDermatologAbstract{

	public Dermatolog() {
		super();
	}

	public Dermatolog(int id, String ime, String prezime, String mail, String password, String adresa,
			String grad, String drzava, String brojTelefona, Pol pol, Date datumRodjenja, boolean promenjenaLozinka,
			Date pocetak, Date kraj) {
		super(id, ime, prezime, mail, password, adresa, grad, drzava, brojTelefona, pol, datumRodjenja,
				promenjenaLozinka, pocetak, kraj);
	}

}
