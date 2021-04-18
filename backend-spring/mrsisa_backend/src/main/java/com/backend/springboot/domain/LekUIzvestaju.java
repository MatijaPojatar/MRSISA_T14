package com.backend.springboot.domain;

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
@Table(name="lek_izvestaj")
public class LekUIzvestaju {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lek_id")
	private Lek lek;
	@Column(name = "terapija", nullable = false)
	private String terapija;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "termin_id")
	private Termin termin;
	
	public LekUIzvestaju() {
		super();
	}

	public LekUIzvestaju( Lek lek, String terapija,Termin termin) {
		super();
		this.lek = lek;
		this.terapija = terapija;
		this.termin=termin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Lek getLek() {
		return lek;
	}

	public void setLek(Lek lek) {
		this.lek = lek;
	}

	public String getTerapija() {
		return terapija;
	}

	public void setTerapija(String terapija) {
		this.terapija = terapija;
	}

	public Termin getTermin() {
		return termin;
	}

	public void setTermin(Termin termin) {
		this.termin = termin;
	}


	
	
	
	
	
}
