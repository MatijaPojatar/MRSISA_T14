package com.backend.springboot.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.backend.springboot.domain.Savetovanje;

public class SavetovanjeDTO {
	private String name, izvestaj;
	private LocalDateTime start, end;
	private Integer pacijentId, id, apotekaId, farmaceutId;
	private boolean izvrsen;

	public SavetovanjeDTO() {
		super();
	}

	public SavetovanjeDTO(String name, String izvestaj, LocalDateTime start, LocalDateTime end, Integer pacijentId,
			boolean izvrsen, Integer apotekaId, Integer farmaceutId) {
		super();
		this.name = name;
		this.izvestaj = izvestaj;
		this.start = start;
		this.end = end;
		this.pacijentId = pacijentId;
		this.izvrsen = izvrsen;
		this.apotekaId = apotekaId;
		this.farmaceutId = farmaceutId;
	}

	public SavetovanjeDTO(Savetovanje s) {
		this.name = "Savetovanje " + s.getId();
		this.izvestaj = s.getIzvestaj();
		this.start = s.getPocetak();
		this.end = s.getKraj();
		if (s.getPacijent() == null) {
			this.pacijentId = null;
		} else {
			this.pacijentId = s.getPacijent().getId();
		}
		this.izvrsen = s.isIzvrsen();
		this.id = s.getId();
		this.apotekaId = s.getApoteka().getId();
		this.farmaceutId = s.getFarmaceut().getId();
	}

	public Integer getFarmaceutId() {
		return farmaceutId;
	}

	public void setFarmaceutId(Integer farmaceutId) {
		this.farmaceutId = farmaceutId;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApotekaId() {
		return apotekaId;
	}

	public void setApotekaId(Integer apotekaId) {
		this.apotekaId = apotekaId;
	}

}
