package com.backend.springboot.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lek_narudzbenica")
public class LekIzNarudzbenice {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "kolicina", nullable = false)
	private Double kolicina;
	@ManyToOne(fetch = FetchType.LAZY)
	private Lek lek;
	@ManyToOne(fetch = FetchType.LAZY)
	private Narudzbenica narudzbenica;

	public Narudzbenica getNarudzbenica() {
		return narudzbenica;
	}

	public void setNarudzbenica(Narudzbenica narudzbenica) {
		this.narudzbenica = narudzbenica;
	}

	public LekIzNarudzbenice() {
	}

	public LekIzNarudzbenice(Integer id, Double kolicina, Lek lek) {
		super();
		this.id = id;
		this.kolicina = kolicina;
		this.lek = lek;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getKolicina() {
		return kolicina;
	}

	public void setKolicina(Double kolicina) {
		this.kolicina = kolicina;
	}

	public Lek getLek() {
		return lek;
	}

	public void setLek(Lek lek) {
		this.lek = lek;
	}

	

}
