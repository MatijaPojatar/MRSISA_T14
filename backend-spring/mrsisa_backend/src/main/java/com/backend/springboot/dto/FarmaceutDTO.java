package com.backend.springboot.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.Pol;

public class FarmaceutDTO {
	private Integer id;
	private String ime,prezime, mail, adresa,grad,drzava,  brojTelefona;
	private Pol pol;
	private LocalDate datumRodjenja;
	private LocalTime pocetakRadnogVremena, krajRadnogVremena;
	
	public FarmaceutDTO() {
		
	}
	
	public FarmaceutDTO(Farmaceut f) {
		this.id=f.getId();
		this.ime=f.getIme();
		this.prezime=f.getPrezime();
		this.mail=f.getMail();
		this.adresa=f.getAdresa();
		this.grad=f.getGrad();
		this.drzava=f.getDrzava();
		this.brojTelefona=f.getBrojTelefona();
		this.pol=f.getPol();
		this.datumRodjenja=f.getDatumRodjenja();
		this.pocetakRadnogVremena = f.getPocetakRadnogVremena();
		this.krajRadnogVremena = f.getKrajRadnogVremena();
	}

	public LocalTime getPocetakRadnogVremena() {
		return pocetakRadnogVremena;
	}

	public void setPocetakRadnogVremena(LocalTime pocetakRadnogVremena) {
		this.pocetakRadnogVremena = pocetakRadnogVremena;
	}

	public LocalTime getKrajRadnogVremena() {
		return krajRadnogVremena;
	}

	public void setKrajRadnogVremena(LocalTime krajRadnogVremena) {
		this.krajRadnogVremena = krajRadnogVremena;
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
