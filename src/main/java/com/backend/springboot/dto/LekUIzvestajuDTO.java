package com.backend.springboot.dto;

import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.LekUIzvestaju;

public class LekUIzvestajuDTO {
	private Integer id, lekId;
	private String terapija,nazivLeka;
	
	public LekUIzvestajuDTO() {
	}
	
	public LekUIzvestajuDTO(LekUIzvestaju lek) {
		Lek l=lek.getLek();
		this.id=lek.getId();
		this.lekId=l.getId();
		this.terapija=lek.getTerapija();
		this.nazivLeka=lek.getLek().getNaziv();
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

	
	public String getTerapija() {
		return terapija;
	}

	public void setTerapija(String terapija) {
		this.terapija = terapija;
	}

	public String getNazivLeka() {
		return nazivLeka;
	}

	public void setNazivLeka(String nazivLeka) {
		this.nazivLeka = nazivLeka;
	}
	
	
	
	
	
	
}
