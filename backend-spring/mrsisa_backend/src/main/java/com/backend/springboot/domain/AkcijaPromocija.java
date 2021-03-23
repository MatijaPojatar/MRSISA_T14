package com.backend.springboot.domain;

import java.util.Date;

public class AkcijaPromocija {
	private String id, opis;
	private Date pocetak, kraj;
	
	public AkcijaPromocija() {
	}

	public AkcijaPromocija(String id, String opis, Date pocetak, Date kraj) {
		super();
		this.id = id;
		this.opis = opis;
		this.pocetak = pocetak;
		this.kraj = kraj;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Date getPocetak() {
		return pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	public Date getKraj() {
		return kraj;
	}

	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}

}
