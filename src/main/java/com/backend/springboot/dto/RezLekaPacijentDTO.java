package com.backend.springboot.dto;

import java.time.LocalDate;

import com.backend.springboot.domain.RezervacijaLeka;

public class RezLekaPacijentDTO {
	private String code, apotekaNaziv, lekNaziv;
	private LocalDate datum;
	private Double kolicina;
	private Integer apotekaId;
	
	public RezLekaPacijentDTO() {
		
	}
	
	public RezLekaPacijentDTO(RezervacijaLeka rl) {
		this.code = rl.getCode();
		this.apotekaNaziv = rl.getApoteka().getNaziv();
		this.lekNaziv = rl.getLek().getNaziv();
		this.datum = rl.getDatum();
		this.kolicina = rl.getKolicina();
		this.apotekaId = rl.getApoteka().getId();
	}

	public Integer getApotekaId() {
		return apotekaId;
	}

	public void setApotekaId(Integer apotekaId) {
		this.apotekaId = apotekaId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getApotekaNaziv() {
		return apotekaNaziv;
	}

	public void setApotekaNaziv(String apotekaNaziv) {
		this.apotekaNaziv = apotekaNaziv;
	}

	public String getLekNaziv() {
		return lekNaziv;
	}

	public void setLekNaziv(String lekNaziv) {
		this.lekNaziv = lekNaziv;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public Double getKolicina() {
		return kolicina;
	}

	public void setKolicina(Double kolicina) {
		this.kolicina = kolicina;
	}
}
