package com.backend.springboot.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.backend.springboot.domain.LekIzNarudzbenice;
import com.backend.springboot.domain.Narudzbenica;

public class NarudzbenicaPrikazDTO {
	private Integer id;
	private String nazivApoteke;
	private LocalDate rok;
	private List<LekIzNarudzbeniceDTO> lekovi;
	
	
	
	public NarudzbenicaPrikazDTO() {
		super();
	}
	
	public NarudzbenicaPrikazDTO(Narudzbenica n) {
		super();
		this.id = n.getId();
		this.nazivApoteke = n.getMagacin().getApoteka().getNaziv();
		this.rok = n.getRok();
		
		List<LekIzNarudzbeniceDTO> lista = new ArrayList<LekIzNarudzbeniceDTO>();
		for (LekIzNarudzbenice lek : n.getLekovi()) {
			lista.add(new LekIzNarudzbeniceDTO(lek));
		}
		this.lekovi = lista;
	}
	
	public NarudzbenicaPrikazDTO(Integer id, String nazivApoteke, LocalDate rok, List<LekIzNarudzbeniceDTO> lekovi) {
		super();
		this.id = id;
		this.nazivApoteke = nazivApoteke;
		this.rok = rok;
		this.lekovi = lekovi;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNazivApoteke() {
		return nazivApoteke;
	}
	public void setNazivApoteke(String nazivApoteke) {
		this.nazivApoteke = nazivApoteke;
	}
	public LocalDate getRok() {
		return rok;
	}
	public void setRok(LocalDate rok) {
		this.rok = rok;
	}
	public List<LekIzNarudzbeniceDTO> getLekovi() {
		return lekovi;
	}
	public void setLekovi(List<LekIzNarudzbeniceDTO> lekovi) {
		this.lekovi = lekovi;
	}
	
	
}
