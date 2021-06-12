package com.backend.springboot.dto;

import java.time.LocalDate;

import com.backend.springboot.domain.Dobavljac;
import com.backend.springboot.domain.Pol;

public class DobavljacDTO {
	private Integer id;
	private String ime,prezime, mail, adresa,grad,drzava,  brojTelefona, nazivPreduzeca;
	private Pol pol;
	private LocalDate datumRodjenja;
	private Boolean promenjenaLozinka, enabled;
	
	public DobavljacDTO() {
		super();
	}
	
	
	public DobavljacDTO(Integer id, String ime, String prezime, String mail, String adresa, String grad, String drzava,
			String brojTelefona, String nazivPreduzeca, Pol pol, LocalDate datumRodjenja, Boolean promenjenaLozinka,
			Boolean enabled) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.mail = mail;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.brojTelefona = brojTelefona;
		this.nazivPreduzeca = nazivPreduzeca;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;
		this.promenjenaLozinka = promenjenaLozinka;
		this.enabled = enabled;
	}


	public DobavljacDTO(Dobavljac d) {
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
		
		this.nazivPreduzeca = d.getNazivPreduzeca();
		
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

	public String getNazivPreduzeca() {
		return nazivPreduzeca;
	}

	public void setNazivPreduzeca(String nazivPreduzeca) {
		this.nazivPreduzeca = nazivPreduzeca;
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
