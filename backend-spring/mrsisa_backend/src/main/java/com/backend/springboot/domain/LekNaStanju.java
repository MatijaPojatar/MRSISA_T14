package com.backend.springboot.domain;

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
@Table(name="lekovi_na_stanju")
public class LekNaStanju {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "kolicina", nullable = false)
	private int kolicina;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Dobavljac dobavljacLekova;
	
	@ManyToOne
	@JoinColumn(name = "lek_id",nullable = false)
	private Lek lek;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public Dobavljac getDobavljacLekova() {
		return dobavljacLekova;
	}

	public void setDobavljacLekova(Dobavljac dobavljacLekova) {
		this.dobavljacLekova = dobavljacLekova;
	}

	public Lek getLek() {
		return lek;
	}

	public void setLek(Lek lek) {
		this.lek = lek;
	}
	
	
}
