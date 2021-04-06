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
	private int id;
	@Column(name = "rok", nullable = false)
	private Date rok;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Apoteka apoteka;
	
	@OneToMany(mappedBy = "narudzbenica", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<LekIzNarudzbenice> lekovi = new ArrayList<LekIzNarudzbenice>();
	
	//DODATI LISTU PONUDA
	
	public Narudzbenica() {
	}

	public Narudzbenica(int id, Date rok, Apoteka apoteka, List<LekIzNarudzbenice> lekovi) {
		super();
		this.id = id;
		this.rok = rok;
		this.apoteka = apoteka;
		this.lekovi = lekovi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
