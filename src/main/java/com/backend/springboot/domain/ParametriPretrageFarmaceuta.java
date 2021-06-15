package com.backend.springboot.domain;

public class ParametriPretrageFarmaceuta {
	private String datum, vreme1, vreme2;
	
	public ParametriPretrageFarmaceuta() {

	}
	
	public ParametriPretrageFarmaceuta(String datum, String vreme1, String vreme2) {
		super();
		this.datum = datum;
		this.vreme1 = vreme1;
		this.vreme2 = vreme2;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getVreme1() {
		return vreme1;
	}

	public void setVreme1(String vreme1) {
		this.vreme1 = vreme1;
	}

	public String getVreme2() {
		return vreme2;
	}

	public void setVreme2(String vreme2) {
		this.vreme2 = vreme2;
	}
}
