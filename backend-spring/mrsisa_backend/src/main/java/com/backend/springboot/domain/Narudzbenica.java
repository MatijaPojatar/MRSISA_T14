package com.backend.springboot.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="narudzbenice")
public class Narudzbenica {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "rok", nullable = false)
	private Date rok;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Apoteka apoteka;
	
	@OneToMany(mappedBy = "narudzbenica", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<LekIzNarudzbenice> lekovi = new ArrayList<LekIzNarudzbenice>();

	@OneToMany(mappedBy = "narudzbenica", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Ponuda> ponude = new ArrayList<Ponuda>();
	
	public Narudzbenica() {
	}

	public Narudzbenica(Integer id, Date rok, Apoteka apoteka, List<LekIzNarudzbenice> lekovi, List<Ponuda> ponude) {
		super();
		this.id = id;
		this.rok = rok;
		this.apoteka = apoteka;
		this.lekovi = lekovi;
		this.ponude = ponude;
	}

	public List<Ponuda> getPonude() {
		return ponude;
	}



	public void setPonude(List<Ponuda> ponude) {
		this.ponude = ponude;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getRok() {
		return rok;
	}

	public void setRok(Date rok) {
		this.rok = rok;
	}

	public Apoteka getApoteka() {
		return apoteka;
	}

	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}

	public List<LekIzNarudzbenice> getLekovi() {
		return lekovi;
	}

	public void setLekovi(List<LekIzNarudzbenice> lekovi) {
		this.lekovi = lekovi;
	}

	
	
	

}
