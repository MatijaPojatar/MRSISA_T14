package com.backend.springboot.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.backend.springboot.dto.KategorijaDTO;

@Entity
@Table(name="kategorije")
public class Kategorija {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "broj_poena_start", nullable = false)
	private Integer brojPoenaStart;
	
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@Column(name = "procenat")
	private Double procenat;
	
	@OneToMany(mappedBy = "kategorija", fetch = FetchType.EAGER, cascade = CascadeType.ALL) 
	private List<Pacijent> pacijenti = new ArrayList<Pacijent>();



	public Kategorija(Integer id, Integer brojPoenaStart, String naziv, Double procenat, List<Pacijent> pacijenti) {
		super();
		this.id = id;
		this.brojPoenaStart = brojPoenaStart;
		this.naziv = naziv;
		this.procenat = procenat;
		this.pacijenti = pacijenti;
	}

	public Kategorija() {
		super();
	}

	public Kategorija(KategorijaDTO k) {
		this.id = k.getId();
		this.brojPoenaStart = k.getBrojPoenaStart();
		this.naziv = k.getNaziv();
		this.procenat = k.getProcenat();
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBrojPoenaStart() {
		return brojPoenaStart;
	}

	public void setBrojPoenaStart(Integer brojPoenaStart) {
		this.brojPoenaStart = brojPoenaStart;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Double getProcenat() {
		return procenat;
	}

	public void setProcenat(Double procenat) {
		this.procenat = procenat;
	}

	public List<Pacijent> getPacijenti() {
		return pacijenti;
	}

	public void setPacijenti(List<Pacijent> pacijenti) {
		this.pacijenti = pacijenti;
	}
	
	
	
}
