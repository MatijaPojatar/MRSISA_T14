package com.backend.springboot.dto;

import java.time.LocalDate;

import com.backend.springboot.domain.RezervacijaLeka;

public class RezervacijaLekaDTO {
	private LocalDate datum;
	private Integer lekId, pacijentId, apotekaId;
	private double kolicina;
	
	public RezervacijaLekaDTO() {
		
	}
	
	public RezervacijaLekaDTO(RezervacijaLeka r) {
		this.datum=r.getDatum();
		this.lekId=r.getLek().getId();
		this.kolicina=r.getKolicina();
		this.pacijentId = r.getPacijent().getId();
		this.apotekaId = r.getApoteka().getId();
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public Integer getLekId() {
		return lekId;
	}

	public void setLekId(Integer lekId) {
		this.lekId = lekId;
	}

	public double getKolicina() {
		return kolicina;
	}

	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
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
	
	
}
