package com.backend.springboot.dto;

import java.time.LocalDate;

import com.backend.springboot.domain.AdministratorSistema;
import com.backend.springboot.domain.Pol;

public class AdministratorSistemaDTO {
	private Integer id;
	private String ime,prezime, mail, adresa,grad,drzava,  brojTelefona, potpis;
	private Pol pol;
	private LocalDate datumRodjenja;
	private Boolean promenjenaLozinka, enabled;
	
	public AdministratorSistemaDTO(AdministratorSistema a) {
		super();
		this.id = a.getId();
		this.ime = a.getIme();
		this.prezime = a.getPrezime();
		this.mail = a.getMail();
		this.adresa = a.getAdresa();
		this.grad = a.getGrad();
		this.drzava = a.getDrzava();
		this.brojTelefona = a.getBrojTelefona();
		this.pol = a.getPol();
		this.datumRodjenja = a.getDatumRodjenja();
		this.potpis = a.getPotpis();
		this.promenjenaLozinka = a.isPromenjenaLozinka();
	    this.enabled = a.isEnabled();
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

	public String getPotpis() {
		return potpis;
	}

	public void setPotpis(String potpis) {
		this.potpis = potpis;
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
