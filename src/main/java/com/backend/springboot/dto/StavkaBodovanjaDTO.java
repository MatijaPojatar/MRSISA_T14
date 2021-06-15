package com.backend.springboot.dto;

import javax.persistence.Column;

import com.backend.springboot.domain.StavkaBodovanja;
import com.backend.springboot.domain.TipStavkeBodovanja;

public class StavkaBodovanjaDTO {
	private Integer id;
	
	private Integer brojPoena;
	
	private Integer idLeka;
	
	private TipStavkeBodovanja tip;
	
	
	public StavkaBodovanjaDTO(StavkaBodovanja s) {
		super();
		this.id = s.getId();
		this.brojPoena = s.getBrojPoena();
		this.idLeka = s.getIdLeka();
		this.tip = s.getTip();
	}
	

	public StavkaBodovanjaDTO(Integer id, Integer brojPoena, Integer idLeka, TipStavkeBodovanja tip) {
		super();
		this.id = id;
		this.brojPoena = brojPoena;
		this.idLeka = idLeka;
		this.tip = tip;
	}

	public StavkaBodovanjaDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBrojPoena() {
		return brojPoena;
	}

	public void setBrojPoena(Integer brojPoena) {
		this.brojPoena = brojPoena;
	}

	public Integer getIdLeka() {
		return idLeka;
	}

	public void setIdLeka(Integer idLeka) {
		this.idLeka = idLeka;
	}

	public TipStavkeBodovanja getTip() {
		return tip;
	}

	public void setTip(TipStavkeBodovanja tip) {
		this.tip = tip;
	}
	
	
}
