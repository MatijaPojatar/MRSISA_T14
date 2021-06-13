package com.backend.springboot.dto;

import java.time.LocalDate;

import com.backend.springboot.domain.AdministratorApoteke;
import com.backend.springboot.domain.Pol;

public class AdministratorApotekeDTO {
	private Integer id, apotekaId;
	private String ime,prezime, mail, adresa,grad,drzava,  brojTelefona;
	private Pol pol;
	private LocalDate datumRodjenja;
	private Boolean promenjenaLozinka, enabled;
	

	
	
	public AdministratorApotekeDTO() {
		super();
	}


	public AdministratorApotekeDTO(Integer id, Integer apotekaId, String ime, String prezime, String mail,
			String adresa, String grad, String drzava, String brojTelefona, Pol pol, LocalDate datumRodjenja,
			Boolean promenjenaLozinka, Boolean enabled) {
		super();
		this.id = id;
		this.apotekaId = apotekaId;
		this.ime = ime;
		this.prezime = prezime;
		this.mail = mail;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.brojTelefona = brojTelefona;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;
		this.promenjenaLozinka = promenjenaLozinka;
		this.enabled = enabled;
	}


	public AdministratorApotekeDTO(AdministratorApoteke d) {
		this.id=d.getId();
		this.ime=d.getIme();
		this.prezime=d.getPrezime();
		this.mail=d.getMail();
		this.adresa=d.getAdresa();
		this.grad=d.getGrad();
		this.drzava=d.getDrzava();
		this.brojTelefona=d.getBrojTelefona();
		this.pol=d.getPol();
		this.datumRodjenja=d.getDatumRodjenja();
		this.apotekaId = d.getApoteka().getId();
		this.promenjenaLozinka = d.isPromenjenaLozinka();
		this.enabled = d.isEnabled();
	}

	public Boolean getPromenjenaLozinka() {
		return promenjenaLozinka;
	}

	public void setPromenjenaLozinka(Boolean promenjenaLozinka) {
		this.promenjenaLozinka = promenjenaLozinka;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApotekaId() {
		return apotekaId;
	}

	public void setApotekaId(Integer apotekaId) {
		this.apotekaId = apotekaId;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
}
