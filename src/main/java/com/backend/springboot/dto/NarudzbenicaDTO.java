package com.backend.springboot.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.backend.springboot.domain.Narudzbenica;
import com.backend.springboot.domain.StatusNarudzbenice;

public class NarudzbenicaDTO {
	private Integer id, magacinId, adminId;
	private LocalDate rok; 
	private StatusNarudzbenice status;
	private String statusStr, rokStr, adminMail;
	private Boolean bezPonuda;
	
	public NarudzbenicaDTO(Narudzbenica n) {
		this.id = n.getId();
		this.magacinId = n.getMagacin().getId();
		this.status = n.getStatus();
		if (this.status == StatusNarudzbenice.CEKA_PONUDE) {
			this.statusStr = "ČEKA PONUDE";
		}
		else {
			this.statusStr = "OBRAĐENA";
		}
		
		this.rok = n.getRok();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		this.rokStr = this.rok.format(dtf);
		
		this.adminId = n.getAdmin().getId();
		this.adminMail = n.getAdmin().getMail();
		this.bezPonuda= n.getPonude().isEmpty();
	}
	
	public Boolean getBezPonuda() {
		return bezPonuda;
	}

	public void setBezPonuda(Boolean bezPonuda) {
		this.bezPonuda = bezPonuda;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminMail() {
		return adminMail;
	}

	public void setAdminMail(String adminMail) {
		this.adminMail = adminMail;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMagacinId() {
		return magacinId;
	}
	public void setMagacinId(Integer magacinId) {
		this.magacinId = magacinId;
	}
	public LocalDate getRok() {
		return rok;
	}
	public void setRok(LocalDate rok) {
		this.rok = rok;
	}
	public StatusNarudzbenice getStatus() {
		return status;
	}
	public void setStatus(StatusNarudzbenice status) {
		this.status = status;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public String getRokStr() {
		return rokStr;
	}
	public void setRokStr(String rokStr) {
		this.rokStr = rokStr;
	}
	
	
}
