package com.backend.springboot.dto;

import java.time.LocalTime;

public class RadnoVremeDTO {
	private LocalTime pocetak,kraj;

	public RadnoVremeDTO(LocalTime pocetak, LocalTime kraj) {
		super();
		this.pocetak = pocetak;
		this.kraj = kraj;
	}

	public RadnoVremeDTO() {
		super();
	}

	public LocalTime getPocetak() {
		return pocetak;
	}

	public void setPocetak(LocalTime pocetak) {
		this.pocetak = pocetak;
	}

	public LocalTime getKraj() {
		return kraj;
	}

	public void setKraj(LocalTime kraj) {
		this.kraj = kraj;
	}
	
	
}
