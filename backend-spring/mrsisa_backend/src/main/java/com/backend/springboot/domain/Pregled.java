package com.backend.springboot.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pregledi")
public class Pregled extends Termin {
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Dermatolog dermatolog;
	@Column(name = "cena", nullable = false)
	private double cena;

	public Pregled() {
		super();
	}

	public Pregled(Date pocetak, Date kraj,double cena) {
		super(pocetak, kraj);
		this.cena=cena;
	}

	public Dermatolog getDermatolog() {
		return dermatolog;
	}

	public void setDermatolog(Dermatolog dermatolog) {
		this.dermatolog = dermatolog;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
	
	
	
	
}
