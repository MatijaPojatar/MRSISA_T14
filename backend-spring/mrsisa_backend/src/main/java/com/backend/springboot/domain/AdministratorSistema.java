package com.backend.springboot.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="administratori_sistema")
public class AdministratorSistema extends Osoba {
	
	@Column(name="potpis", nullable = true)
	private String potpis;

	public AdministratorSistema() {
		super();
	}
	
	public AdministratorSistema(int id, String ime, String prezime, String mail, String password, String adresa, String grad,
			String drzava, String brojTelefona, Pol pol, LocalDate datumRodjenja, boolean promenjenaLozinka, String potpis) {
		
		super(id, ime, prezime, mail, password, adresa, grad, drzava, brojTelefona, pol, datumRodjenja, promenjenaLozinka);
		this.potpis = potpis;
	}

	public String getPotpis() {
		return potpis;
	}

	public void setPotpis(String potpis) {
		this.potpis = potpis;
	}
	
	
}
