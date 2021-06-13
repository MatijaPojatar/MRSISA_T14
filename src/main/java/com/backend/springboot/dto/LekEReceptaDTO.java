package com.backend.springboot.dto;

import com.backend.springboot.domain.LekERecepta;

public class LekEReceptaDTO {
	private Integer id, lekId, kolicina;
	private String nazivLeka;
	
	public LekEReceptaDTO(LekERecepta l) {
		super();
		this.id = l.getId();
		this.lekId = l.getLek().getId();
		this.kolicina = l.getKolicina();
		this.nazivLeka = l.getLek().getNaziv();
	}
	
	public LekEReceptaDTO() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLekId() {
		return lekId;
	}
	public void setLekId(Integer lekId) {
		this.lekId = lekId;
	}
	public Integer getKolicina() {
		return kolicina;
	}
	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}
	public String getNazivLeka() {
		return nazivLeka;
	}
	public void setNazivLeka(String nazivLeka) {
		this.nazivLeka = nazivLeka;
	}
	
	
}
