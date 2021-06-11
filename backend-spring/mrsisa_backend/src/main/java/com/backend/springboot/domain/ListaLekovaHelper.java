package com.backend.springboot.domain;

import java.util.List;

import com.backend.springboot.dto.LekEReceptaDTO;

public class ListaLekovaHelper {
	private Integer id;
	
	private List<LekEReceptaDTO> lekovi;
	

	public ListaLekovaHelper() {
		super();
	}




	public ListaLekovaHelper(Integer id, List<LekEReceptaDTO> lekovi) {
		super();
		this.id = id;
		this.lekovi = lekovi;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public List<LekEReceptaDTO> getLekovi() {
		return lekovi;
	}


	public void setLekovi(List<LekEReceptaDTO> lekovi) {
		this.lekovi = lekovi;
	}	

}
