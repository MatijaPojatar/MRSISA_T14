package com.backend.springboot.dto;

import com.backend.springboot.domain.Apoteka;

public class ApotekaMainInfoDTO {
private Integer id;
	
	private String naziv;
	private String adresa;
	private String grad;
	private String drzava;
	private String opis;
	
	private Double ocena;
	
	public ApotekaMainInfoDTO() {
	}
	
	public ApotekaMainInfoDTO(Apoteka a) {
		this.id = a.getId();
		this.naziv = a.getNaziv();
		this.adresa = a.getAdresa();
		this.grad = a.getGrad();
		this.drzava = a.getDrzava();
		this.opis = a.getOpis();
		this.ocena = a.getOcena();
	}

	public Double getOcena() {
		return ocena;
	}

	public void setOcena(Double ocena) {
		this.ocena = ocena;
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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	
}
