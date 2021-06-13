package com.backend.springboot.dto;

import java.util.ArrayList;
import java.util.List;

import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.OblikLeka;
import com.backend.springboot.domain.RezimIzdavanja;
import com.backend.springboot.domain.VrstaLeka;

public class LekPrikazDTO {
	
	private List<String> naziviZamenskih;
	
	private Integer id;
	private String naziv;
	private String sastav;
	private String proizvodjac;
	private String napomena;
	private RezimIzdavanja rezimIzdavanja;
	private OblikLeka oblikLeka;
	private VrstaLeka vrstaLeka;
	private Double ocenaLeka;
	
	
	public LekPrikazDTO(Lek lek) {
		this.id = lek.getId();
		this.naziv = lek.getNaziv();
		this.sastav = lek.getSastav();
		this.proizvodjac = lek.getProizvodjac();
		this.napomena = lek.getNapomena();
		this.rezimIzdavanja = lek.getRezimIzdavanja();
		this.oblikLeka = lek.getOblikLeka();
		this.vrstaLeka = lek.getVrstaLeka();
		
		this.naziviZamenskih = new ArrayList<String>();
		for(Lek l : lek.getZamenskiLekovi()) {
			this.naziviZamenskih.add(l.getNaziv());
		}
		
		this.ocenaLeka = 4.5; //popraviti
	}
	
	public LekPrikazDTO() {
		super();
	}
	

	public Double getOcenaLeka() {
		return ocenaLeka;
	}

	public void setOcenaLeka(Double ocenaLeka) {
		this.ocenaLeka = ocenaLeka;
	}

	public List<String> getNaziviZamenskih() {
		return naziviZamenskih;
	}

	public void setNaziviZamenskih(List<String> naziviZamenskih) {
		this.naziviZamenskih = naziviZamenskih;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getSastav() {
		return sastav;
	}
	public void setSastav(String sastav) {
		this.sastav = sastav;
	}
	public String getProizvodjac() {
		return proizvodjac;
	}
	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}
	public String getNapomena() {
		return napomena;
	}
	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}
	public RezimIzdavanja getRezimIzdavanja() {
		return rezimIzdavanja;
	}
	public void setRezimIzdavanja(RezimIzdavanja rezimIzdavanja) {
		this.rezimIzdavanja = rezimIzdavanja;
	}
	public OblikLeka getOblikLeka() {
		return oblikLeka;
	}
	public void setOblikLeka(OblikLeka oblikLeka) {
		this.oblikLeka = oblikLeka;
	}
	public VrstaLeka getVrstaLeka() {
		return vrstaLeka;
	}
	public void setVrstaLeka(VrstaLeka vrstaLeka) {
		this.vrstaLeka = vrstaLeka;
	}
	
	
}
