package com.backend.springboot.dto;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.ZalbaNaApoteku;

public class ZalbaNaApotekuDTO {
	private Integer id, pacijentId, apotekaId;
	private String tekst;
	private Boolean obradjena;
	
	public ZalbaNaApotekuDTO(ZalbaNaApoteku z) {
		super();
		this.id = z.getId();
		this.tekst = z.getTekst();
		this.obradjena = z.getObradjena();
		this.pacijentId = z.getPacijent().getId();
		this.apotekaId = z.getApoteka().getId();
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
