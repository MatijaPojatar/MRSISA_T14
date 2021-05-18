package com.backend.springboot.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.Pol;

public class FarmaceutDTO {
	private Integer id,apotekaId;
	private String ime,prezime, mail, adresa,grad,drzava,  brojTelefona, pocetakRadnogVremenaStr, krajRadnogVremenaStr;
	private Pol pol;
	private LocalDate datumRodjenja;
	private LocalTime pocetakRadnogVremena, krajRadnogVremena;
	private Boolean promenjenaLozinka, enabled;
	
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
		this.apotekaId=f.getApoteka().getId();
		this.promenjenaLozinka = f.isPromenjenaLozinka();
		this.enabled = f.isEnabled();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		this.pocetakRadnogVremenaStr = this.pocetakRadnogVremena.format(dtf);
		this.krajRadnogVremenaStr = this.krajRadnogVremena.format(dtf);
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

	public String getPocetakRadnogVremenaStr() {
		return pocetakRadnogVremenaStr;
	}

	public void setPocetakRadnogVremenaStr(String pocetakRadnogVremenaStr) {
		this.pocetakRadnogVremenaStr = pocetakRadnogVremenaStr;
	}

	public String getKrajRadnogVremenaStr() {
		return krajRadnogVremenaStr;
	}

	public void setKrajRadnogVremenaStr(String krajRadnogVremenaStr) {
		this.krajRadnogVremenaStr = krajRadnogVremenaStr;
	}

	public Integer getApotekaId() {
		return apotekaId;
	}

	public void setApotekaId(Integer apotekaId) {
		this.apotekaId = apotekaId;
	}
	
	
}
