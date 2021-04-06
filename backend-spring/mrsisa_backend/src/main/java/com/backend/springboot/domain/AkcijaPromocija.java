package com.backend.springboot.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	private Date pocetakVazenja;
	@Column(name = "kraj_vazenja", nullable = false)
	private Date krajVazenja;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Apoteka apoteka;
	
	public AkcijaPromocija() {
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

	public Apoteka getApoteka() {
		return apoteka;
	}

	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}

	

}
