package com.backend.springboot.domain;

import java.util.List;

import com.backend.springboot.dto.LekEReceptaDTO;

public class ListaLekovaHelper {
	private List<LekEReceptaDTO> lekovi;
	

	public ListaLekovaHelper() {
		super();
	}


	public ListaLekovaHelper(List<LekEReceptaDTO> lekovi) {
		super();
		this.lekovi = lekovi;
	}


	public List<LekEReceptaDTO> getLekovi() {
		return lekovi;
	}


	public void setLekovi(List<LekEReceptaDTO> lekovi) {
		this.lekovi = lekovi;
	}

	
	
	

}
