package com.backend.springboot.dto;

import java.util.List;
import java.util.Set;

import com.backend.springboot.domain.AdministratorApoteke;
import com.backend.springboot.domain.AkcijaPromocija;
import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.Magacin;
import com.backend.springboot.domain.Narudzbenica;
import com.backend.springboot.domain.Termin;

public class ApotekaDTO {
	private Integer id;
	
	private String naziv;
	private String adresa;
	private String grad;
	private String drzava;
	private String opis;
	
	private List<Lek> lekovi;
	private Set<Farmaceut> farmaceuti;
	private Set<Dermatolog> dermatolozi;
	private List<Termin> termini;
	private Magacin magacin;
	private List<AkcijaPromocija> akcije;
	private Set<AdministratorApoteke> administratori;
	
	
	public ApotekaDTO() {
		super();
	}
	public ApotekaDTO(Apoteka a) {
		this.id = a.getId();
		this.naziv = a.getNaziv();
		this.adresa = a.getAdresa();
		this.grad = a.getGrad();
		this.drzava = a.getDrzava();
		this.opis = a.getOpis();
		this.lekovi = a.getLekovi();
		this.farmaceuti = a.getFarmaceuti();
		this.dermatolozi = a.getDermatolozi();
		this.termini = a.getTermini();
		this.magacin = a.getMagacin();
		this.akcije = a.getAkcije();
		this.administratori = getAdministratori();
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
	public List<Lek> getLekovi() {
		return lekovi;
	}
	public void setLekovi(List<Lek> lekovi) {
		this.lekovi = lekovi;
	}
	public Set<Farmaceut> getFarmaceuti() {
		return farmaceuti;
	}
	public void setFarmaceuti(Set<Farmaceut> farmaceuti) {
		this.farmaceuti = farmaceuti;
	}
	public Set<Dermatolog> getDermatolozi() {
		return dermatolozi;
	}
	public void setDermatolozi(Set<Dermatolog> dermatolozi) {
		this.dermatolozi = dermatolozi;
	}
	public List<Termin> getTermini() {
		return termini;
	}
	public void setTermini(List<Termin> termini) {
		this.termini = termini;
	}
	public Magacin getMagacin() {
		return magacin;
	}
	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
	}
	public List<AkcijaPromocija> getAkcije() {
		return akcije;
	}
	public void setAkcije(List<AkcijaPromocija> akcije) {
		this.akcije = akcije;
	}
	public Set<AdministratorApoteke> getAdministratori() {
		return administratori;
	}
	public void setAdministratori(Set<AdministratorApoteke> administratori) {
		this.administratori = administratori;
	}
	
	
	
	
}
