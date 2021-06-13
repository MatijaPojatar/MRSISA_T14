package com.backend.springboot.domain;

public class ParametriPretrageApoteke {
	private String naziv, grad;
	
	public ParametriPretrageApoteke() {

	}

	public ParametriPretrageApoteke(String naziv, String grad) {
		this.naziv = naziv;
		this.grad = grad;
	}
	
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}
}
