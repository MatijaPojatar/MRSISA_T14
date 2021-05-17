package com.backend.springboot.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.backend.springboot.domain.Ponuda;
import com.backend.springboot.domain.StatusPonude;

public class PonudaDTO {
	private Integer id, cena,dobavljacId, narudzbenicaId;
	private LocalDateTime rokIsporuke;
	private StatusPonude status;
	private String rokStr, nazivDobavljaca;
	
	public PonudaDTO(Integer id, Integer cena, Integer dobavljacId, Integer narudzbenicaId, LocalDateTime rokIsporuke,
			StatusPonude status) {
		super();
		this.id = id;
		this.cena = cena;
		this.dobavljacId = dobavljacId;
		this.narudzbenicaId = narudzbenicaId;
		this.rokIsporuke = rokIsporuke;
		this.status = status;
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
