package com.backend.springboot.dto;

import com.backend.springboot.domain.ZalbaNaDermatologa;

public class ZalbaNaDermatologaDTO {
	private Integer id, pacijentId, dermatologId;
	private String tekst, odgovor;
	private Boolean obradjena;


	public ZalbaNaDermatologaDTO() {
		super();
	}

	public ZalbaNaDermatologaDTO(Integer id, Integer pacijentId, Integer dermatologId, String tekst, String odgovor,
			Boolean obradjena) {
		super();
		this.id = id;
		this.pacijentId = pacijentId;
		this.dermatologId = dermatologId;
		this.tekst = tekst;
		this.odgovor = odgovor;
		this.obradjena = obradjena;
	}
	
	public ZalbaNaDermatologaDTO(ZalbaNaDermatologa z) {
		super();
		this.id = z.getId();
		this.tekst = z.getTekst();
		this.obradjena = z.getObradjena();
		this.pacijentId = z.getPacijent().getId();
		this.dermatologId = z.getDermatolog().getId();
		this.odgovor = z.getOdgovor();
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
