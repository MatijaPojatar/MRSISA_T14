package com.backend.springboot.dto;

import java.time.LocalDateTime;

import com.backend.springboot.domain.OdsustvoDermatolog;

public class OdsustvoDermatologDTO {
	
	private Integer id,dermatologId;
	private LocalDateTime pocetak,kraj;
	private boolean odobren;
	
	public OdsustvoDermatologDTO() {
		super();
	}
	
	public OdsustvoDermatologDTO(OdsustvoDermatolog o) {
		super();
		this.id=o.getId();
		this.dermatologId=o.getDermatolog().getId();
		this.pocetak=o.getPocetak();
		this.kraj=o.getKraj();
		this.odobren=o.isOdobren();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDermatologId() {
		return dermatologId;
	}

	public void setDermatologId(Integer dermatologId) {
		this.dermatologId = dermatologId;
	}

	public LocalDateTime getPocetak() {
		return pocetak;
	}

	public void setPocetak(LocalDateTime pocetak) {
		this.pocetak = pocetak;
	}

	public LocalDateTime getKraj() {
		return kraj;
	}

	public void setKraj(LocalDateTime kraj) {
		this.kraj = kraj;
	}

	public boolean isOdobren() {
		return odobren;
	}

	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}
	
	
}
