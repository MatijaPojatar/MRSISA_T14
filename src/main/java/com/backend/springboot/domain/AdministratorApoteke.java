package com.backend.springboot.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.springboot.dto.AdministratorApotekeDTO;

@Entity
@Table(name="administratori_apoteke")
public class AdministratorApoteke extends Osoba {

	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch = FetchType.EAGER)
	private Apoteka apoteka;

	public Apoteka getApoteka() {
		return apoteka;
	}

	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}

	public AdministratorApoteke(Apoteka apoteka) {
		super();
		this.apoteka = apoteka;
	}

	public AdministratorApoteke() {
		super();
	}

	public AdministratorApoteke(Integer id, String ime, String prezime, String mail, String password, String adresa,
			String grad, String drzava, String brojTelefona, Pol pol, LocalDate datumRodjenja,
			boolean promenjenaLozinka, boolean enabled) {
		super(id, ime, prezime, mail, password, adresa, grad, drzava, brojTelefona, pol, datumRodjenja, promenjenaLozinka, enabled);
	}

	public AdministratorApoteke(AdministratorApotekeDTO a) {
		this.setId(a.getId());
		this.setIme(a.getIme());
		this.setPrezime(a.getPrezime());
		this.setMail(a.getMail());
		this.setAdresa(a.getAdresa());
		this.setGrad(a.getGrad());
		this.setDrzava(a.getDrzava());
		this.setBrojTelefona(a.getBrojTelefona());
		this.setPol(a.getPol());
		this.setDatumRodjenja(a.getDatumRodjenja());
		this.setPromenjenaLozinka(a.getPromenjenaLozinka());
		this.setEnabled(a.getEnabled());
		
	}
	
	
}
