package com.backend.springboot.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="administratori_apoteke")
public class AdministratorApoteke extends Osoba {
	@ManyToOne(fetch = FetchType.EAGER)
	private Apoteka apoteka;
	@OneToMany(mappedBy = "admin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OdsustvoFarmaceut> odsustva=new ArrayList<OdsustvoFarmaceut>();

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
			boolean promenjenaLozinka) {
		super(id, ime, prezime, mail, password, adresa, grad, drzava, brojTelefona, pol, datumRodjenja, promenjenaLozinka);
	}

	public List<OdsustvoFarmaceut> getOdsustva() {
		return odsustva;
	}

	public void setOdsustva(List<OdsustvoFarmaceut> odsustva) {
		this.odsustva = odsustva;
	}
	
	
	
	
}
