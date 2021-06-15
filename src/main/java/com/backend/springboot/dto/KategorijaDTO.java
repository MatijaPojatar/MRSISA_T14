package com.backend.springboot.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.backend.springboot.domain.Kategorija;
import com.backend.springboot.domain.Pacijent;

public class KategorijaDTO {
	private Integer id;
	private Integer brojPoenaStart;
	private String naziv;
	private Double procenat;
	public KategorijaDTO(Integer id, Integer brojPoenaStart, String naziv, Double procenat) {
		super();
		this.id = id;
		this.brojPoenaStart = brojPoenaStart;
		this.naziv = naziv;
		this.procenat = procenat;
	}
	public KategorijaDTO(Kategorija k) {
		this.id = k.getId();
		this.brojPoenaStart = k.getBrojPoenaStart();
		this.naziv = k.getNaziv();
		this.procenat = k.getProcenat();
	}
	public KategorijaDTO() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBrojPoenaStart() {
		return brojPoenaStart;
	}
	public void setBrojPoenaStart(Integer brojPoenaStart) {
		this.brojPoenaStart = brojPoenaStart;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Double getProcenat() {
		return procenat;
	}
	public void setProcenat(Double procenat) {
		this.procenat = procenat;
	}

	
}
