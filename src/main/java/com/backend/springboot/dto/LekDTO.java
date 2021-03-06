package com.backend.springboot.dto;


import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.OblikLeka;
import com.backend.springboot.domain.RezimIzdavanja;
import com.backend.springboot.domain.VrstaLeka;


public class LekDTO {
	private Integer id;
	private String naziv;
	private String sastav;
	private String proizvodjac;
	private String napomena;
	private RezimIzdavanja rezimIzdavanja;
	private OblikLeka oblikLeka;
	private VrstaLeka vrstaLeka;
	
	public LekDTO() {
		
	}
	
	public LekDTO(Lek l) {
		this.naziv = l.getNaziv();
		this.sastav = l.getSastav();
		this.proizvodjac = l.getProizvodjac();
		this.napomena = l.getNapomena();
		this.rezimIzdavanja = l.getRezimIzdavanja();
		this.oblikLeka = l.getOblikLeka();
		this.vrstaLeka = l.getVrstaLeka();
		this.id = l.getId();
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
}
