package com.backend.springboot.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="savetovanja")
public class Savetovanje extends Termin {
	@ManyToOne(fetch = FetchType.EAGER)
	private Farmaceut farmaceut;

	public Savetovanje() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Savetovanje(Date pocetak, Date kraj) {
		super(pocetak, kraj);
		// TODO Auto-generated constructor stub
	}

	public Farmaceut getFarmaceut() {
		return farmaceut;
	}

	public void setFarmaceut(Farmaceut farmaceut) {
		this.farmaceut = farmaceut;
	}
	
	
	
	

}
