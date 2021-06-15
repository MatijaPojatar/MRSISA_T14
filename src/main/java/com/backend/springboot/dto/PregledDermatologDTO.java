package com.backend.springboot.dto;

import java.time.LocalDateTime;

import com.backend.springboot.domain.Pregled;

public class PregledDermatologDTO {
	private String name, izvestaj, dermatolog;
	private LocalDateTime start, end;
	private Integer dermatologId, pacijentId, apotekaId, id;
	private boolean izvrsen;
	private double ocena, cena;
	
	public PregledDermatologDTO() {
		super();
	}

	public PregledDermatologDTO(String name, String izvestaj, String dermatolog, LocalDateTime start, LocalDateTime end,
			Integer dermatologId, Integer pacijentId, Integer apotekaId, Integer id, boolean izvrsen, double ocena,
			double cena) {
		super();
		this.name = name;
		this.izvestaj = izvestaj;
		this.dermatolog = dermatolog;
		this.start = start;
		this.end = end;
		this.dermatologId = dermatologId;
		this.pacijentId = pacijentId;
		this.apotekaId = apotekaId;
		this.id = id;
		this.izvrsen = izvrsen;
		this.ocena = ocena;
		this.cena = cena;
	}

	public PregledDermatologDTO(Pregled p) {
		this.name="Pregled "+p.getId();
		this.izvestaj=p.getIzvestaj();
		this.start=p.getPocetak();
		this.end=p.getKraj();
		if(p.getPacijent()==null) {
			this.pacijentId=null;
		}else {
			this.pacijentId=p.getPacijent().getId();
		}
		this.izvrsen=p.isIzvrsen();
		this.cena=p.getCena();
		this.apotekaId=p.getApoteka().getId();
		this.id=p.getId();
		this.dermatologId = p.getDermatolog().getId();
		this.dermatolog = p.getDermatolog().getIme() + " " + p.getDermatolog().getPrezime();
		this.ocena = p.getDermatolog().getOcena();
	}
	
	public Integer getDermatologId() {
		return dermatologId;
	}

	public void setDermatologId(Integer dermatologId) {
		this.dermatologId = dermatologId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIzvestaj() {
		return izvestaj;
	}

	public void setIzvestaj(String izvestaj) {
		this.izvestaj = izvestaj;
	}

	public String getDermatolog() {
		return dermatolog;
	}

	public void setDermatolog(String dermatolog) {
		this.dermatolog = dermatolog;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isIzvrsen() {
		return izvrsen;
	}

	public void setIzvrsen(boolean izvrsen) {
		this.izvrsen = izvrsen;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
}
