package com.backend.springboot.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.backend.springboot.domain.Ponuda;
import com.backend.springboot.domain.StatusPonude;

public class PonudaDTO {
	private Integer id, cena,dobavljacId, narudzbenicaId;
	private LocalTime rokVreme;
	private LocalDate rokDatum;
	private LocalDateTime rokIsporuke;
	private StatusPonude status;
	private String rokStr, nazivDobavljaca;
	
	

	public PonudaDTO(Integer id, Integer cena, Integer dobavljacId, Integer narudzbenicaId, LocalTime rokVreme,
			LocalDate rokDatum, StatusPonude status, String rokStr, String nazivDobavljaca) {
		super();
		this.id = id;
		this.cena = cena;
		this.dobavljacId = dobavljacId;
		this.narudzbenicaId = narudzbenicaId;
		this.rokVreme = rokVreme;
		this.rokDatum = rokDatum;
		this.rokIsporuke = LocalDateTime.of(rokDatum, rokVreme);
		this.status = status;
		this.rokStr = rokStr;
		this.nazivDobavljaca = nazivDobavljaca;
	}

	public PonudaDTO() {
		super();
	}
	
	public PonudaDTO(Ponuda p) {
		this.id = p.getId();
		this.cena = p.getCena();
		this.dobavljacId = p.getDobavljac().getId();
		this.narudzbenicaId = p.getNarudzbenica().getId();
		
		
		this.rokIsporuke = p.getRokIsporuke();
		
		
		this.status = p.getStatus();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		this.rokStr = this.rokIsporuke.format(dtf);
		
		this.nazivDobavljaca = p.getDobavljac().getNazivPreduzeca();
	}

	public LocalTime getRokVreme() {
		return rokVreme;
	}

	public void setRokVreme(LocalTime rokVreme) {
		this.rokVreme = rokVreme;
	}

	public LocalDate getRokDatum() {
		return rokDatum;
	}

	public void setRokDatum(LocalDate rokDatum) {
		this.rokDatum = rokDatum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCena() {
		return cena;
	}

	public void setCena(Integer cena) {
		this.cena = cena;
	}

	public Integer getDobavljacId() {
		return dobavljacId;
	}

	public void setDobavljacId(Integer dobavljacId) {
		this.dobavljacId = dobavljacId;
	}

	public Integer getNarudzbenicaId() {
		return narudzbenicaId;
	}

	public void setNarudzbenicaId(Integer narudzbenicaId) {
		this.narudzbenicaId = narudzbenicaId;
	}

	public LocalDateTime getRokIsporuke() {
		return rokIsporuke;
	}

	public void setRokIsporuke(LocalDateTime rokIsporuke) {
		this.rokIsporuke = rokIsporuke;
	}

	public StatusPonude getStatus() {
		return status;
	}

	public void setStatus(StatusPonude status) {
		this.status = status;
	}


	public String getRokStr() {
		return rokStr;
	}


	public void setRokStr(String rokStr) {
		this.rokStr = rokStr;
	}


	public String getNazivDobavljaca() {
		return nazivDobavljaca;
	}


	public void setNazivDobavljaca(String nazivDobavljaca) {
		this.nazivDobavljaca = nazivDobavljaca;
	}
	
	
}
