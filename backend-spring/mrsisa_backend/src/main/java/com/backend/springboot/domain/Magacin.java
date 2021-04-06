package com.backend.springboot.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
@Table(name="magacini")
public class Magacin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Apoteka apoteka;
	
	@OneToMany(mappedBy = "magacin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<LekUMagacinu> lekovi = new ArrayList<LekUMagacinu>();
	
	public Magacin() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Apoteka getApoteka() {
		return apoteka;
	}

	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}

	public List<LekUMagacinu> getLekovi() {
		return lekovi;
	}

	public void setLekovi(List<LekUMagacinu> lekovi) {
		this.lekovi = lekovi;
	}

	
	
	
}
