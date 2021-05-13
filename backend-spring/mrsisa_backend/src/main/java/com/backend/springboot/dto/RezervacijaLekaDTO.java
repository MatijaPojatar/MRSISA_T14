package com.backend.springboot.dto;

import java.time.LocalDate;

import com.backend.springboot.domain.RezervacijaLeka;

public class RezervacijaLekaDTO {
	private LocalDate datum;
	private Integer lekId;
	private double kolicina;
	
	public RezervacijaLekaDTO() {
		
	}
	
	public RezervacijaLekaDTO(RezervacijaLeka r) {
		this.datum=r.getDatum();
		this.lekId=r.getLek().getId();
		this.kolicina=r.getKolicina();
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
	
	
}
