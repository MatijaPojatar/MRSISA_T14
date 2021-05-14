package com.backend.springboot.dto;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.ZalbaNaApoteku;

public class ZalbaNaApotekuDTO {
	private Integer id, pacijentId, apotekaId, administratorId;
	private String tekst, odgovor, apotekaNaziv, pacijentIP, administratorIP;
	private Boolean obradjena;
	
	
	public ZalbaNaApotekuDTO() {
		super();
	}

	public ZalbaNaApotekuDTO(Integer id, Integer pacijentId, Integer apotekaId, Integer administratorId, String tekst, String odgovor,
			Boolean obradjena) {
		super();
		this.id = id;
		this.pacijentId = pacijentId;
		this.apotekaId = apotekaId;
		this.administratorId = administratorId;
		this.tekst = tekst;
		this.odgovor = odgovor;
		this.obradjena = obradjena;
		this.apotekaNaziv = "";
		this.pacijentIP = "";
		this.administratorIP = "";
	}

	public ZalbaNaApotekuDTO(ZalbaNaApoteku z) {
		super();
		this.id = z.getId();
		this.tekst = z.getTekst();
		this.obradjena = z.getObradjena();
		this.pacijentId = z.getPacijent().getId();
		this.apotekaId = z.getApoteka().getId();
		this.administratorId = z.getAdministrator().getId();
		this.odgovor = z.getOdgovor();
		this.apotekaNaziv = z.getApoteka().getNaziv();
		this.pacijentIP = z.getPacijent().getIme() + " " + z.getPacijent().getPrezime();
		this.administratorIP = z.getAdministrator().getIme() + " " + z.getAdministrator().getPrezime();
	}
	

	public Integer getAdministratorId() {
		return administratorId;
	}

	public void setAdministratorId(Integer administratorId) {
		this.administratorId = administratorId;
	}

	public String getAdministratorIP() {
		return administratorIP;
	}

	public void setAdministratorIP(String administratorIP) {
		this.administratorIP = administratorIP;
	}

	public String getApotekaNaziv() {
		return apotekaNaziv;
	}

	public void setApotekaNaziv(String apotekaNaziv) {
		this.apotekaNaziv = apotekaNaziv;
	}

	public String getPacijentIP() {
		return pacijentIP;
	}

	public void setPacijentIP(String pacijentIP) {
		this.pacijentIP = pacijentIP;
	}

	public String getOdgovor() {
		return odgovor;
	}

	public void setOdgovor(String odgovor) {
		this.odgovor = odgovor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPacijentId() {
		return pacijentId;
	}

	public void setPacijentId(Integer pacijentId) {
		this.pacijentId = pacijentId;
	}

	public Integer getApotekaId() {
		return apotekaId;
	}

	public void setApotekaId(Integer apotekaId) {
		this.apotekaId = apotekaId;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Boolean getObradjena() {
		return obradjena;
	}

	public void setObradjena(Boolean obradjena) {
		this.obradjena = obradjena;
	}
	
}
