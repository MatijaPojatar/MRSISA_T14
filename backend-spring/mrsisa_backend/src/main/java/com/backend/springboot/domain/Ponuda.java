package com.backend.springboot.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ponude")
public class Ponuda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	//NEZAVRSENO
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Dobavljac dobavljac;


	public Ponuda(Integer id, Dobavljac dobavljac) {
		super();
		this.id = id;
		this.dobavljac = dobavljac;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Dobavljac getDobavljac() {
		return dobavljac;
	}


	public void setDobavljac(Dobavljac dobavljac) {
		this.dobavljac = dobavljac;
	}

}
