package com.backend.springboot.domain;

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
	private LocalDateTime pocetakVazenja;
	@Column(name = "kraj_vazenja", nullable = false)
	private LocalDateTime krajVazenja;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Apoteka apoteka;
	
	public AkcijaPromocija() {
	}
	
	public AkcijaPromocija(Integer id, String opis, LocalDateTime pocetakVazenja, LocalDateTime krajVazenja,
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

	public Apoteka getApoteka() {
		return apoteka;
	}

	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}

	

}
