package com.backend.springboot.domain;

import java.util.Date;

public class Pregled extends Termin {
	private double cena;

	public Pregled() {
		super();
	}

	public Pregled(Date pocetak, Date kraj,double cena) {
		super(pocetak, kraj);
		this.cena=cena;
	}
	
	
}
