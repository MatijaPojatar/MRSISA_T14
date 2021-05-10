package com.backend.springboot.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.DermatologApoteka;
import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.Pol;

public class DermatologDTO {
	private Integer id;
	private String ime,prezime, mail, adresa,grad,drzava,  brojTelefona, pocetakRadnogVremenaStr, krajRadnogVremenaStr;
	private Pol pol;
	private LocalDate datumRodjenja;
	private LocalTime pocetakRadnogVremena, krajRadnogVremena;
	
	public DermatologDTO() {
		
	}
	
	public DermatologDTO(Dermatolog d) {
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
		this.pocetakRadnogVremena = d.getPocetakRadnogVremena();
		this.krajRadnogVremena = d.getKrajRadnogVremena();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		this.pocetakRadnogVremenaStr = this.pocetakRadnogVremena.format(dtf);
		this.krajRadnogVremenaStr = this.krajRadnogVremena.format(dtf);
	}
	
	public DermatologDTO(Dermatolog d, Integer apotekaId) {
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
		for (DermatologApoteka z : d.getZaposlenja()) {
			if (z.getApoteka().getId() == apotekaId) {
				this.pocetakRadnogVremena = z.getPocetakRadnogVremena();
				this.krajRadnogVremena = z.getKrajRadnogVremena();
			}
		}
		
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		this.pocetakRadnogVremenaStr = this.pocetakRadnogVremena.format(dtf);
		this.krajRadnogVremenaStr = this.krajRadnogVremena.format(dtf);
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
	
	
}
