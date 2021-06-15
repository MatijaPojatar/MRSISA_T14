package com.backend.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.springboot.dto.StavkaBodovanjaDTO;

@Entity
@Table(name="stavke_bodovanja")
public class StavkaBodovanja {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="brojPoena")
	private Integer brojPoena;
	
	@Column(name="id_leka", nullable= true, unique= true)
	private Integer idLeka;
	
	@Column(name="tip")
	private TipStavkeBodovanja tip;
	
	public StavkaBodovanja(StavkaBodovanjaDTO s) {
		super();
		this.id = s.getId();
		this.brojPoena = s.getBrojPoena();
		this.idLeka = s.getIdLeka();
		this.tip = s.getTip();
		
	}

	public StavkaBodovanja() {
		super();
	}

	public StavkaBodovanja(Integer id, Integer brojPoena, Integer idLeka, TipStavkeBodovanja tip) {
		super();
		this.id = id;
		this.brojPoena = brojPoena;
		this.idLeka = idLeka;
		this.tip = tip;
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
