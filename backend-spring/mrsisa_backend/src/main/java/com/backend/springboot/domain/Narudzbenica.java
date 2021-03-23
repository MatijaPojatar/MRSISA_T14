package com.backend.springboot.domain;

import java.util.Date;

public class Narudzbenica {
	private Date rok;
	private LekIzNarudzbenice lek;
	public Narudzbenica() {
	}
	public Narudzbenica(Date rok, LekIzNarudzbenice lek) {
		super();
		this.rok = rok;
		this.lek = lek;
	}
	public Date getRok() {
		return rok;
	}
	public void setRok(Date rok) {
		this.rok = rok;
	}
	public LekIzNarudzbenice getLek() {
		return lek;
	}
	public void setLek(LekIzNarudzbenice lek) {
		this.lek = lek;
	}

}
