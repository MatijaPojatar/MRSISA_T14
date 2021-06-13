package com.backend.springboot.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.backend.springboot.domain.AkcijaPromocija;

public class AkcijaPromocijaDTO {	
	private Integer id, apotekaId;
	private String apotekaNaziv, opis;
	private LocalDate pocetak, kraj;
	
	public AkcijaPromocijaDTO() {
		super();
	}
	
	public AkcijaPromocijaDTO(AkcijaPromocija ap) {
		super();
		this.id = ap.getId();
		this.apotekaId = ap.getApoteka().getId();
		this.apotekaNaziv = ap.getApoteka().getNaziv();
		this.opis = ap.getOpis();
		this.pocetak = ap.getPocetakVazenja();
		this.kraj = ap.getKrajVazenja();
	}

	public AkcijaPromocijaDTO(Integer id, Integer apotekaId, String apotekaNaziv, String opis, LocalDate pocetak, LocalDate kraj) {
		super();
		this.id = id;
		this.apotekaId = apotekaId;
		this.apotekaNaziv = apotekaNaziv;
		this.opis = opis;
		this.pocetak = pocetak;
		this.kraj = kraj;
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
	
	public String getApotekaNaziv() {
		return apotekaNaziv;
	}

	public void setApotekaNaziv(String apotekaNaziv) {
		this.apotekaNaziv = apotekaNaziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public LocalDate getPocetak() {
		return pocetak;
	}

	public void setPocetak(LocalDate pocetak) {
		this.pocetak = pocetak;
	}

	public LocalDate getKraj() {
		return kraj;
	}

	public void setKraj(LocalDate kraj) {
		this.kraj = kraj;
	}
}
