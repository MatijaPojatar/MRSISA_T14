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
@Table(name="lek_magacin")
public class LekUMagacinu {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "pocetakVazenja", nullable = false)
	private Date pocetakVazenja;
	@Column(name = "krajVazenja", nullable = true)
	private Date krajVazenja;
	@Column(name = "cena", nullable = false)
	private Double cena;
	@Column(name = "kolicina", nullable = false)
	private Double kolicina;
	@ManyToOne(fetch = FetchType.LAZY)
	private Lek lek;
	@ManyToOne(fetch = FetchType.LAZY)
	private Magacin magacin;
	
	public LekUMagacinu() {
		// TODO Auto-generated constructor stub
	}
	
	

	public LekUMagacinu(int id, Date pocetakVazenja, Double cena, Double kolicina, Lek lek, Magacin magacin) {
		super();
		this.id = id;
		this.pocetakVazenja = pocetakVazenja;
		this.krajVazenja = null;
		this.cena = cena;
		this.kolicina = kolicina;
		this.lek = lek;
		this.magacin = magacin;
	}



	public LekUMagacinu(int id, Date pocetakVazenja, Date krajVazenja, Double cena, Double kolicina, Lek lek,
			Magacin magacin) {
		super();
		this.id = id;
		this.pocetakVazenja = pocetakVazenja;
		this.krajVazenja = krajVazenja;
		this.cena = cena;
		this.kolicina = kolicina;
		this.lek = lek;
		this.magacin = magacin;
	}



	public Date getPocetakVazenja() {
		return pocetakVazenja;
	}
	public void setPocetakVazenja(Date pocetakVazenja) {
		this.pocetakVazenja = pocetakVazenja;
	}
	public Date getKrajVazenja() {
		return krajVazenja;
	}
	public void setKrajVazenja(Date krajVazenja) {
		this.krajVazenja = krajVazenja;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Lek getLek() {
		return lek;
	}
	public void setLek(Lek lek) {
		this.lek = lek;
	}

	public Double getKolicina() {
		return kolicina;
	}

	public void setKolicina(Double kolicina) {
		this.kolicina = kolicina;
	}
}
