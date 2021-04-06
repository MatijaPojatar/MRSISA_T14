package com.backend.springboot.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="akcije")
public class AkcijaPromocija {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "opis", nullable = false)
	private String opis;
	@Column(name = "pocetakVazenja", nullable = false)
	private Date pocetakVazenja;
	@Column(name = "krajVazenja", nullable = true)
	private Date krajVazenja;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Apoteka apoteka;
	
	public AkcijaPromocija() {
	}

	

}
