package com.backend.springboot.dto;

import java.time.LocalDate;

import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.Pol;

public class PacijentDTO {
	private Integer id, brojPoena, penali;
	private String ime, prezime, mail, password, adresa, grad, drzava, brojTelefona;
	private Pol pol;
	private LocalDate datumRodjenja;
	
	public PacijentDTO() {
		
	}
	
	public PacijentDTO(Pacijent p) {
		this.id = p.getId();
		this.brojPoena = p.getBrojPoena();
		this.penali = p.getPenali();
		this.ime = p.getIme();
		this.prezime = p.getPrezime();
		this.mail = p.getMail();
		this.password = p.getPassword();
		this.adresa = p.getAdresa();
		this.grad = p.getGrad();
		this.drzava = p.getDrzava();
		this.brojTelefona = p.getBrojTelefona();
		this.pol = p.getPol();
		this.datumRodjenja = p.getDatumRodjenja();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBrojPoena() {
		return brojPoena;
	}


	public void setBrojPoena(Integer brojPoena) {
		this.brojPoena = brojPoena;
	}


	public Integer getPenali() {
		return penali;
	}


	public void setPenali(Integer penali) {
		this.penali = penali;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
