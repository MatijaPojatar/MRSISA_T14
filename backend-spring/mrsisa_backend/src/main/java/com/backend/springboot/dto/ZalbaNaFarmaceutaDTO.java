package com.backend.springboot.dto;

import com.backend.springboot.domain.ZalbaNaFarmaceuta;

public class ZalbaNaFarmaceutaDTO {
	private Integer id, pacijentId, farmaceutId, administratorId;
	private String tekst, odgovor, pacijentIP, farmaceutIP, administratorIP;
	private Boolean obradjena;
	
	public ZalbaNaFarmaceutaDTO() {
		super();
	}

	public ZalbaNaFarmaceutaDTO(Integer id, Integer pacijentId, Integer farmaceutId, Integer administratorId, String tekst, String odgovor,
			Boolean obradjena) {
		super();
		this.id = id;
		this.pacijentId = pacijentId;
		this.farmaceutId = farmaceutId;
		this.administratorId = administratorId;
		this.tekst = tekst;
		this.odgovor = odgovor;
		this.obradjena = obradjena;
		this.pacijentIP = "";
		this.farmaceutIP = "";
		this.administratorIP = "";
	}
	
	
	public ZalbaNaFarmaceutaDTO(ZalbaNaFarmaceuta z) {
		super();
		this.id = z.getId();
		this.tekst = z.getTekst();
		this.obradjena = z.getObradjena();
		this.pacijentId = z.getPacijent().getId();
		this.farmaceutId = z.getFarmaceut().getId();
		if(z.getAdministrator() == null) {
			this.administratorId = -1;
			this.administratorIP = "";
		}else {
			this.administratorId = z.getAdministrator().getId();
			this.administratorIP = z.getAdministrator().getIme() + " " + z.getAdministrator().getPrezime();
		}
		this.odgovor = z.getOdgovor();
		this.pacijentIP = z.getPacijent().getIme() + " " + z.getPacijent().getPrezime();
		this.farmaceutIP = z.getFarmaceut().getIme() + " " + z.getFarmaceut().getPrezime();
		
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

	public String getPacijentIP() {
		return pacijentIP;
	}


	public void setPacijentIP(String pacijentIP) {
		this.pacijentIP = pacijentIP;
	}


	public String getFarmaceutIP() {
		return farmaceutIP;
	}


	public void setFarmaceutIP(String farmaceutIP) {
		this.farmaceutIP = farmaceutIP;
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
	public Integer getFarmaceutId() {
		return farmaceutId;
	}
	public void setFarmaceutId(Integer farmaceutId) {
		this.farmaceutId = farmaceutId;
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
