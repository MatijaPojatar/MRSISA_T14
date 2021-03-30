package com.backend.springboot.domain;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="termini")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Termin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "pocetak", nullable = false)
	private LocalDateTime pocetak;
	@Column(name = "kraj", nullable = false)
	private LocalDateTime kraj;
	@Column(name = "izvestaj", nullable = false)
	private String izvestaj;
	@Column(name = "izvrsen", nullable = false)
	private boolean izvrsen;
	@ManyToOne(fetch = FetchType.EAGER)
	private Pacijent pacijent;
	@ManyToOne(fetch = FetchType.EAGER)
	private Apoteka apoteka;
	
	
	
	public Termin() {
		super();
		this.izvrsen=false;
	}



	public Termin(LocalDateTime pocetak,LocalDateTime kraj) {
		super();
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.izvrsen=false;
	}

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	public LocalDateTime getPocetak() {
		return pocetak;
	}



	public void setPocetak(LocalDateTime pocetak) {
		this.pocetak = pocetak;
	}



	public LocalDateTime getKraj() {
		return kraj;
	}



	public void setKraj(LocalDateTime kraj) {
		this.kraj = kraj;
	}



	public String getIzvestaj() {
		return izvestaj;
	}



	public void setIzvestaj(String izvestaj) {
		this.izvestaj = izvestaj;
	}



	public Pacijent getPacijent() {
		return pacijent;
	}



	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}



	public Apoteka getApoteka() {
		return apoteka;
	}



	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}



	public boolean isIzvrsen() {
		return izvrsen;
	}



	public void setIzvrsen(boolean izvrsen) {
		this.izvrsen = izvrsen;
	}
	
	
	
	
	
	
}
