package com.backend.springboot.dto;

import java.time.LocalDateTime;

import com.backend.springboot.domain.AkcijaPromocija;

public class AkcijaPromocijaDTO {	
	private Integer id, apotekaId;
	private String opis;
	private LocalDateTime pocetak, kraj;
	
	public AkcijaPromocijaDTO() {
		super();
	}
	
	public AkcijaPromocijaDTO(AkcijaPromocija ap) {
		super();
		this.id = ap.getId();
		this.apotekaId = ap.getApoteka().getId();
		this.opis = ap.getOpis();
		this.pocetak = ap.getPocetakVazenja();
		this.kraj = ap.getKrajVazenja();
	}

	public AkcijaPromocijaDTO(Integer id, Integer apotekaId, String opis, LocalDateTime pocetak, LocalDateTime kraj) {
		super();
		this.id = id;
		this.apotekaId = apotekaId;
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public LocalDateTime getPocetak() {
		return pocetak;
	}

	public void setPocetak(LocalDateTime pocetak) {
		this.pocetak = pocetak;
	}

	public LocalDateTime getKraj() {
		return kraj;
	}

	public void setKraj(LocalDateTime kraj) {
		this.kraj = kraj;
	}
}
