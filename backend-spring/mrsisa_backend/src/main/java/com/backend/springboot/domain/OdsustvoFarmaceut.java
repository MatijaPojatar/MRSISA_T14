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
	@Column(name = "status", nullable = false)
	private StatusZahtevaZaOdmor status;
	@ManyToOne(fetch = FetchType.EAGER)
	private Farmaceut farmaceut;
	@ManyToOne(fetch = FetchType.EAGER)
	private Apoteka apoteka;
	@Column(name = "razlog_odbijanja", nullable = true)
	private String razlog;
	
	public OdsustvoFarmaceut() {
		super();
	}

	

	public OdsustvoFarmaceut(Integer id, LocalDateTime pocetak, LocalDateTime kraj, StatusZahtevaZaOdmor status,
			Farmaceut farmaceut, Apoteka apoteka) {
		super();
		this.id = id;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.status = status;
		this.farmaceut = farmaceut;
		this.apoteka = apoteka;
	}



	public String getRazlog() {
		return razlog;
	}



	public void setRazlog(String razlog) {
		this.razlog = razlog;
	}



	public StatusZahtevaZaOdmor getStatus() {
		return status;
	}



	public void setStatus(StatusZahtevaZaOdmor status) {
		this.status = status;
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
