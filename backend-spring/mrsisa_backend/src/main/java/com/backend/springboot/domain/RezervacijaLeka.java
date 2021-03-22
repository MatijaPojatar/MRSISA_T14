package com.backend.springboot.domain;

import java.sql.Date;

public class RezervacijaLeka {
	private String id;
	private Date datum;
	private Lek lek;
	private String apotekaId;
	
	public RezervacijaLeka() {
		super();
	}

	public RezervacijaLeka(String id, Date datum, Lek lek,String apotekaId) {
		super();
		this.id = id;
		this.datum = datum;
		this.lek = lek;
		this.apotekaId=apotekaId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Lek getLek() {
		return lek;
	}

	public void setLek(Lek lek) {
		this.lek = lek;
	}

	public String getApotekaId() {
		return apotekaId;
	}

	public void setApotekaId(String apotekaId) {
		this.apotekaId = apotekaId;
	}

	
	
	
	
	
}
