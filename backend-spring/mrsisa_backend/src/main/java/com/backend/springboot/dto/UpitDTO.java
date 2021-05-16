package com.backend.springboot.dto;

import com.backend.springboot.domain.Upit;

public class UpitDTO {
	private Integer id, lekId, magacinId;
	private Double kolicina;
	private String nazivLeka;
	public UpitDTO(Upit upit) {
		super();
		this.id = upit.getId();
		this.kolicina = upit.getKolicina();
		this.lekId = upit.getLek().getId();
		this.magacinId = upit.getMagacin().getId();
		this.nazivLeka = upit.getLek().getNaziv();
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
	public Integer getMagacinId() {
		return magacinId;
	}
	public void setMagacinId(Integer magacinId) {
		this.magacinId = magacinId;
	}
	public Double getKolicina() {
		return kolicina;
	}
	public void setKolicina(Double kolicina) {
		this.kolicina = kolicina;
	}
	public String getNazivLeka() {
		return nazivLeka;
	}
	public void setNazivLeka(String nazivLeka) {
		this.nazivLeka = nazivLeka;
	}
	
}
