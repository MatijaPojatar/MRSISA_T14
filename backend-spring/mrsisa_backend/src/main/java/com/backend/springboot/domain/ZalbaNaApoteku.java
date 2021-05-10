package com.backend.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.springboot.dto.ZalbaNaApotekuDTO;

@Entity
@Table(name = "zalba_apoteka")
public class ZalbaNaApoteku {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "tekst", nullable = false)
	private String tekst;
	
	@Column(name = "odgovor")
	private String odgovor;
	
	@Column(name = "obradjena", nullable = false)
	private Boolean obradjena;
	
	//jedan pac vise zalbi
	@ManyToOne(fetch = FetchType.EAGER)
	private Pacijent pacijent;
	
	//apoteka
	@ManyToOne(fetch = FetchType.EAGER)
	private Apoteka apoteka;


	public ZalbaNaApoteku() {
		super();
	}


	public ZalbaNaApoteku(Integer id, String tekst, Boolean obradjena, Pacijent pacijent, Apoteka apoteka, String odgovor) {
		super();
		this.id = id;
		this.tekst = tekst;
		this.obradjena = obradjena;
		this.pacijent = pacijent;
		this.apoteka = apoteka;
		this.odgovor = odgovor;
	}

	public ZalbaNaApoteku(ZalbaNaApotekuDTO dto) {
		super();
		this.id = dto.getId();
		this.tekst = dto.getTekst();
		this.obradjena = dto.getObradjena();
		this.odgovor = dto.getOdgovor();
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public Apoteka getApoteka() {
		return apoteka;
	}

	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}
	
	
	
}
