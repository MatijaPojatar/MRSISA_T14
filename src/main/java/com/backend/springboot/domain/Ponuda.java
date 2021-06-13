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

import com.backend.springboot.dto.PonudaDTO;

@Entity
@Table(name="ponude")
public class Ponuda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "cena", nullable = false)
	private Integer cena;
	
	@Column(name = "status", nullable = false)
	private StatusPonude status;
	
	@Column(name = "rok_isporuke", nullable = false)
	private LocalDateTime rokIsporuke;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Dobavljac dobavljac;

	@ManyToOne(fetch = FetchType.EAGER)
	private Narudzbenica narudzbenica;

	public Ponuda() {
		super();
	}

	public Ponuda(Integer id, Integer cena, StatusPonude status, LocalDateTime rokIsporuke, Dobavljac dobavljac,
			Narudzbenica narudzbenica) {
		super();
		this.id = id;
		this.cena = cena;
		this.status = status;
		this.rokIsporuke = rokIsporuke;
		this.dobavljac = dobavljac;
		this.narudzbenica = narudzbenica;
	}
	
	public Ponuda(PonudaDTO dto) {
		
//		LocalDateTime fromDateAndTime = LocalDateTime.of(currentDate,
//                currentTime);
		
		this.id = dto.getId();
		this.cena = dto.getCena();
		this.rokIsporuke = LocalDateTime.of(dto.getRokDatum(), dto.getRokVreme());
		this.status = dto.getStatus();
	}

	public LocalDateTime getRokIsporuke() {
		return rokIsporuke;
	}

	public void setRokIsporuke(LocalDateTime rokIsporuke) {
		this.rokIsporuke = rokIsporuke;
	}

	public Integer getCena() {
		return cena;
	}


	public void setCena(Integer cena) {
		this.cena = cena;
	}


	public StatusPonude getStatus() {
		return status;
	}


	public void setStatus(StatusPonude status) {
		this.status = status;
	}


	public Narudzbenica getNarudzbenica() {
		return narudzbenica;
	}


	public void setNarudzbenica(Narudzbenica narudzbenica) {
		this.narudzbenica = narudzbenica;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Dobavljac getDobavljac() {
		return dobavljac;
	}


	public void setDobavljac(Dobavljac dobavljac) {
		this.dobavljac = dobavljac;
	}

}
