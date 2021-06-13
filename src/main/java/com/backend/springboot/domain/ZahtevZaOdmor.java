package com.backend.springboot.domain;

import java.util.Date;

public class ZahtevZaOdmor {
	private StatusZahtevaZaOdmor status;
	private Date pocetak, kraj;
	//zaposleni
	
	public ZahtevZaOdmor() {
		// TODO Auto-generated constructor stub
	}

	public ZahtevZaOdmor(StatusZahtevaZaOdmor status, Date pocetak, Date kraj) {
		super();
		this.status = status;
		this.pocetak = pocetak;
		this.kraj = kraj;
	}

	public StatusZahtevaZaOdmor getStatus() {
		return status;
	}

	public void setStatus(StatusZahtevaZaOdmor status) {
		this.status = status;
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
