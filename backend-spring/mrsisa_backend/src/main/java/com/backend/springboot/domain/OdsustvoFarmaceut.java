package com.backend.springboot.domain;

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
@Table(name="odsustva_farmaceut")
public class OdsustvoFarmaceut {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "pocetak", nullable = false)
	private LocalDateTime pocetak;
	@Column(name = "kraj", nullable = false)
	private LocalDateTime kraj;
	@Column(name = "odobren", nullable = false)
	private boolean odobren;
	@ManyToOne(fetch = FetchType.EAGER)
	private Farmaceut farmaceut;
	@ManyToOne(fetch = FetchType.EAGER)
	private Apoteka apoteka;
	
	public OdsustvoFarmaceut() {
		super();
	}

	public OdsustvoFarmaceut(Integer id, LocalDateTime pocetak, LocalDateTime kraj, boolean odobren,
			Farmaceut farmaceut, Apoteka apoteka) {
		super();
		this.id = id;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.odobren = odobren;
		this.farmaceut = farmaceut;
		this.apoteka = apoteka;
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

	public boolean isOdobren() {
		return odobren;
	}

	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}

	public Farmaceut getFarmaceut() {
		return farmaceut;
	}

	public void setFarmaceut(Farmaceut farmaceut) {
		this.farmaceut = farmaceut;
	}

	public Apoteka getApoteka() {
		return apoteka;
	}

	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
	}

	
	
	

}
