package com.backend.springboot.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.backend.springboot.domain.Pregled;

public class PregledDTO {
	private String name,izvestaj;
	private LocalDateTime start,end;
	private Integer pacijentId,apotekaId,id;
	private boolean izvrsen;
	private double cena;
	
	public PregledDTO() {
		super();
	}

	public PregledDTO(String name, String izvestaj, LocalDateTime start, LocalDateTime end, Integer pacijentId, boolean izvrsen,
			double cena) {
		super();
		this.name = name;
		this.izvestaj = izvestaj;
		this.start = start;
		this.end = end;
		this.pacijentId = pacijentId;
		this.izvrsen = izvrsen;
		this.cena = cena;
	}
	
	public PregledDTO(Pregled p) {
		this.name="Pregled "+p.getId();
		this.izvestaj=p.getIzvestaj();
		this.start=p.getPocetak();
		this.end=p.getKraj();
		this.pacijentId=p.getPacijent().getId();
		this.izvrsen=p.isIzvrsen();
		this.cena=p.getCena();
		this.apotekaId=p.getApoteka().getId();
		this.id=p.getId();
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

	public boolean isIzvrsen() {
		return izvrsen;
	}

	public void setIzvrsen(boolean izvrsen) {
		this.izvrsen = izvrsen;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
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
	
	
	
	
}
