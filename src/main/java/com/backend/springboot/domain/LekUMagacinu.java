package com.backend.springboot.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="lek_magacin")

@SQLDelete(sql
	    = "UPDATE lek_magacin "
	    + "SET obrisan = true "
	    + "WHERE id = ?")
@Where(clause = "obrisan = false")
public class LekUMagacinu {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "pocetak_vazenja", nullable = false)
	private LocalDateTime pocetakVazenja;
	@Column(name = "kraj_vazenja", nullable = true)
	private LocalDateTime krajVazenja;
	@Column(name = "cena", nullable = false)
	private Double cena;
	@Column(name = "kolicina", nullable = false)
	private Double kolicina;
	@ManyToOne(fetch = FetchType.LAZY)
	private Lek lek;
	@ManyToOne(fetch = FetchType.LAZY)
	private Magacin magacin;
	
	@OneToMany(mappedBy = "lekUMagacinu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<StavkaCenovnika> sveCene = new ArrayList<StavkaCenovnika>();
	
	@Column(name = "obrisan", nullable = false)
	private boolean obrisan;
	
	@Version
	private Long version;
	
	public LekUMagacinu() {
		// TODO Auto-generated constructor stub
	}
	
	

	public LekUMagacinu(Integer id, LocalDateTime pocetakVazenja, Double cena, Double kolicina, Lek lek, Magacin magacin) {
		super();
		this.id = id;
		this.pocetakVazenja = pocetakVazenja;
		this.krajVazenja = null;
		this.cena = cena;
		this.kolicina = kolicina;
		this.lek = lek;
		this.magacin = magacin;
		this.obrisan = false;
	}



	public LekUMagacinu(Integer id, LocalDateTime pocetakVazenja, LocalDateTime krajVazenja, Double cena, Double kolicina, Lek lek,
			Magacin magacin) {
		super();
		this.id = id;
		this.pocetakVazenja = pocetakVazenja;
		this.krajVazenja = krajVazenja;
		this.cena = cena;
		this.kolicina = kolicina;
		this.lek = lek;
		this.magacin = magacin;
		this.obrisan = false;
	}



	public Long getVersion() {
		return version;
	}



	public void setVersion(Long version) {
		this.version = version;
	}



	public List<StavkaCenovnika> getSveCene() {
		return sveCene;
	}



	public void setSveCene(List<StavkaCenovnika> sveCene) {
		this.sveCene = sveCene;
	}



	public LocalDateTime getPocetakVazenja() {
		return pocetakVazenja;
	}
	public void setPocetakVazenja(LocalDateTime sada) {
		this.pocetakVazenja = sada;
	}
	public LocalDateTime getKrajVazenja() {
		return krajVazenja;
	}
	public void setKrajVazenja(LocalDateTime krajVazenja) {
		this.krajVazenja = krajVazenja;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
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

	public Double getKolicina() {
		return kolicina;
	}

	public void setKolicina(Double kolicina) {
		this.kolicina = kolicina;
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
