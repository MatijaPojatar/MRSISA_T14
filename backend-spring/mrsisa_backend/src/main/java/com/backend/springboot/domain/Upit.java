package com.backend.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="upiti")
@SQLDelete(sql
	    = "UPDATE upiti "
	    + "SET obrisan = true "
	    + "WHERE id = ?")
@Where(clause = "obrisan = false")
public class Upit {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "kolicina", nullable = false)
	Double kolicina;
	@ManyToOne(fetch = FetchType.LAZY)
	private Lek lek;
	@ManyToOne(fetch = FetchType.LAZY)
	Magacin magacin;
	
	@Column(name = "obrisan", nullable = false)
	private boolean obrisan;

	public Upit() {
		// TODO Auto-generated constructor stub
	}

	public Upit(Integer id, Double kolicina, Lek lek, Magacin magacin) {
		super();
		this.id = id;
		this.kolicina = kolicina;
		this.lek = lek;
		this.magacin = magacin;
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

	public Magacin getMagacin() {
		return magacin;
	}

	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	

}
