package com.backend.springboot.domain;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="zaposlenja")
public class DermatologApoteka {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "dermatolog_id") 
	private Dermatolog dermatolog;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "apoteka_id") 
	private Apoteka apoteka;
	
	@Column(name = "pocetakRadnogVremena", nullable = false)
	private LocalTime pocetakRadnogVremena;
	@Column(name = "krajRadnogVremena", nullable = false)
	private LocalTime krajRadnogVremena;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Dermatolog getDermatolog() {
		return dermatolog;
	}
	public void setDermatolog(Dermatolog dermatolog) {
		this.dermatolog = dermatolog;
	}
	public Apoteka getApoteka() {
		return apoteka;
	}
	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}
	public LocalTime getPocetakRadnogVremena() {
		return pocetakRadnogVremena;
	}
	public void setPocetakRadnogVremena(LocalTime pocetakRadnogVremena) {
		this.pocetakRadnogVremena = pocetakRadnogVremena;
	}
	public LocalTime getKrajRadnogVremena() {
		return krajRadnogVremena;
	}
	public void setKrajRadnogVremena(LocalTime krajRadnogVremena) {
		this.krajRadnogVremena = krajRadnogVremena;
	}
	
	
}
