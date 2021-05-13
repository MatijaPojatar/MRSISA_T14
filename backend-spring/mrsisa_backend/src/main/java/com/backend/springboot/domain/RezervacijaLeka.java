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
@Table(name="rezervacije")
public class RezervacijaLeka {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "code", nullable = false,unique=true)
	private String code;
	@Column(name = "datum", nullable = false)
	private LocalDate datum;
	@Column(name = "kolicina", nullable = false)
	private double kolicina;
	@Column(name = "status", nullable = false)
	private StatusRezervacije status;
	@ManyToOne(fetch = FetchType.LAZY)
	private Apoteka apoteka;
	@ManyToOne(fetch = FetchType.LAZY)
	private Pacijent pacijent;
	@ManyToOne(fetch = FetchType.LAZY)
	private Lek lek;
	
	public RezervacijaLeka() {
		super();
	}

	public RezervacijaLeka(Integer id, String code, LocalDate datum, double kolicina, Apoteka apoteka,
			Pacijent pacijent, Lek lek) {
		super();
		this.id = id;
		this.code = code;
		this.datum = datum;
		this.kolicina = kolicina;
		this.apoteka = apoteka;
		this.pacijent = pacijent;
		this.lek = lek;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public double getKolicina() {
		return kolicina;
	}

	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}

	public Apoteka getApoteka() {
		return apoteka;
	}

	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public Lek getLek() {
		return lek;
	}

	public void setLek(Lek lek) {
		this.lek = lek;
	}

	public StatusRezervacije getStatus() {
		return status;
	}

	public void setStatus(StatusRezervacije status) {
		this.status = status;
	}
	
	
	
	
	
	
	

	
	
	
	
	
}
