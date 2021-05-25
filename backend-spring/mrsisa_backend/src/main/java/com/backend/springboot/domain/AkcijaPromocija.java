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
@Table(name="akcije")
public class AkcijaPromocija {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "opis", nullable = false)
	private String opis;
	@Column(name = "pocetak_vazenja", nullable = false)
	private LocalDate pocetakVazenja;
	@Column(name = "kraj_vazenja", nullable = false)
	private LocalDate krajVazenja;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Apoteka apoteka;
	
	public AkcijaPromocija() {
	}
	
	public AkcijaPromocija(Integer id, String opis, LocalDate pocetakVazenja, LocalDate krajVazenja,
			Apoteka apoteka) {
		super();
		this.id = id;
		this.opis = opis;
		this.pocetakVazenja = pocetakVazenja;
		this.krajVazenja = krajVazenja;
		this.apoteka = apoteka;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public LocalDate getPocetakVazenja() {
		return pocetakVazenja;
	}

	public void setPocetakVazenja(LocalDate pocetakVazenja) {
		this.pocetakVazenja = pocetakVazenja;
	}

	public LocalDate getKrajVazenja() {
		return krajVazenja;
	}

	public void setKrajVazenja(LocalDate krajVazenja) {
		this.krajVazenja = krajVazenja;
	}

	public Apoteka getApoteka() {
		return apoteka;
	}

	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}

	

}
