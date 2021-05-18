package com.backend.springboot.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NovaNarudzbenicaDTO {
	private List<LekIzNarudzbeniceDTO> lekovi=new ArrayList<LekIzNarudzbeniceDTO>();
	private LocalDate rok;
	private Integer adminId;
	
	public NovaNarudzbenicaDTO() {
		super();
	}
	public List<LekIzNarudzbeniceDTO> getLekovi() {
		return lekovi;
	}
	public void setLekovi(List<LekIzNarudzbeniceDTO> lekovi) {
		this.lekovi = lekovi;
	}
	public LocalDate getRok() {
		return rok;
	}
	public void setRok(LocalDate rok) {
		this.rok = rok;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	
	
}
