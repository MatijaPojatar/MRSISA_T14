package com.backend.springboot.domain;

import java.util.Date;

public class AdministratorApoteke extends Osoba {

	public AdministratorApoteke(String ime, String prezime, String mail, String username, String password,
			String adresa, String grad, String drzava, String brojTelefona, Pol pol, Date datumRodjenja,
			boolean promenjenaLozinka) {
		super(ime, prezime, mail, username, password, adresa, grad, drzava, brojTelefona, pol, datumRodjenja, promenjenaLozinka);
		
	}

	

}
