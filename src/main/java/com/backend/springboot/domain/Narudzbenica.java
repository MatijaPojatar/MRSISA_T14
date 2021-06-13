package com.backend.springboot.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="narudzbenice")
public class Narudzbenica {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "rok", nullable = false)
	private LocalDate rok;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Magacin magacin;
	
	@OneToMany(mappedBy = "narudzbenica", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<LekIzNarudzbenice> lekovi = new ArrayList<LekIzNarudzbenice>();

	@OneToMany(mappedBy = "narudzbenica", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Ponuda> ponude = new ArrayList<Ponuda>();
	
	@Column(name = "status", nullable = false)
	private StatusNarudzbenice status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private AdministratorApoteke admin;
	
	public Narudzbenica() {
	}

	

	public AdministratorApoteke getAdmin() {
		return admin;
	}



	public void setAdmin(AdministratorApoteke admin) {
		this.admin = admin;
	}



	public StatusNarudzbenice getStatus() {
		return status;
	}

	public void setStatus(StatusNarudzbenice status) {
		this.status = status;
	}

	public List<Ponuda> getPonude() {
		return ponude;
	}



	public void setPonude(List<Ponuda> ponude) {
		this.ponude = ponude;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getRok() {
		return rok;
	}

	public void setRok(LocalDate rok) {
		this.rok = rok;
	}

	

	public Magacin getMagacin() {
		return magacin;
	}



	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
	}



	public List<LekIzNarudzbenice> getLekovi() {
		return lekovi;
	}

	public void setLekovi(List<LekIzNarudzbenice> lekovi) {
		this.lekovi = lekovi;
	}

	
	
	

}
