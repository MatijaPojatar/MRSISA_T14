package com.backend.springboot.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cenovnik")
public class StavkaCenovnika {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	private LekUMagacinu lekUMagacinu;
	@Column(name = "cena", nullable = false)
	private Double cena;
	@Column(name = "pocetak_vazenja", nullable = false)
	private LocalDateTime pocetakVazenja;
	@Column(name = "kraj_vazenja", nullable = true)
	private LocalDateTime krajVazenja;
	
	
	public StavkaCenovnika() {
		super();
	}
	public StavkaCenovnika(LekUMagacinu lek, double cena) {
		super();
		this.lekUMagacinu = lek;
		this.cena = cena;
	}
	public StavkaCenovnika(Integer id, LekUMagacinu lek, double cena) {
		super();
		this.id = id;
		this.lekUMagacinu = lek;
		this.cena = cena;
	}
	public LocalDateTime getPocetakVazenja() {
		return pocetakVazenja;
	}
	public void setPocetakVazenja(LocalDateTime pocetakVazenja) {
		this.pocetakVazenja = pocetakVazenja;
	}
	public LocalDateTime getKrajVazenja() {
		return krajVazenja;
	}
	public void setKrajVazenja(LocalDateTime krajVazenja) {
		this.krajVazenja = krajVazenja;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LekUMagacinu getLekUMagacinu() {
		return lekUMagacinu;
	}
	public void setLekUMagacinu(LekUMagacinu lek) {
		this.lekUMagacinu = lek;
	}
	public Double getCena() {
		return cena;
	}
	
	
}
