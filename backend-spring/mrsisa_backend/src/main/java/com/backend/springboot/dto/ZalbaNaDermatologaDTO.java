package com.backend.springboot.dto;

import com.backend.springboot.domain.ZalbaNaDermatologa;

public class ZalbaNaDermatologaDTO {
	private Integer id, pacijentId, dermatologId, administratorId;
	private String tekst, odgovor, dermatologIP, pacijentIP, administratorIP;
	private Boolean obradjena;


	public ZalbaNaDermatologaDTO() {
		super();
	}

	public ZalbaNaDermatologaDTO(Integer id, Integer pacijentId, Integer dermatologId, Integer administratorId, String tekst, String odgovor,
			Boolean obradjena) {
		super();
		this.id = id;
		this.pacijentId = pacijentId;
		this.dermatologId = dermatologId;
		this.administratorId = administratorId;
		this.tekst = tekst;
		this.odgovor = odgovor;
		this.obradjena = obradjena;
		this.dermatologIP = "";
		this.pacijentIP = "";
		this.administratorIP = "";
	}
	
	public ZalbaNaDermatologaDTO(ZalbaNaDermatologa z) {
		super();
		this.id = z.getId();
		this.tekst = z.getTekst();
		this.obradjena = z.getObradjena();
		this.pacijentId = z.getPacijent().getId();
		this.dermatologId = z.getDermatolog().getId();
		this.administratorId = z.getAdministrator().getId();
		this.odgovor = z.getOdgovor();
		this.dermatologIP = z.getDermatolog().getIme() + " " + z.getDermatolog().getPrezime();
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

	public String getDermatologIP() {
		return dermatologIP;
	}

	public void setDermatologIP(String dermatologIP) {
		this.dermatologIP = dermatologIP;
	}

	public String getPacijentIP() {
		return pacijentIP;
	}

	public void setPacijentIP(String pacijentIP) {
		this.pacijentIP = pacijentIP;
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
	public Integer getDermatologId() {
		return dermatologId;
	}
	public void setDermatologId(Integer dermatologId) {
		this.dermatologId = dermatologId;
	}
	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	public String getOdgovor() {
		return odgovor;
	}
	public void setOdgovor(String odgovor) {
		this.odgovor = odgovor;
	}
	public Boolean getObradjena() {
		return obradjena;
	}
	public void setObradjena(Boolean obradjena) {
		this.obradjena = obradjena;
	}
	
	
}
