package com.backend.springboot.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.backend.springboot.domain.OdsustvoDermatolog;

public class OdsustvoDermatologDTO {
	
	private Integer id,dermatologId;
	private LocalDateTime pocetak,kraj;
	private boolean odobren;
	private String mail, pocetakStr, krajStr;
	
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
		this.mail = o.getDermatolog().getMail();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		this.pocetakStr= this.pocetak.format(dtf);
		this.krajStr= this.kraj.format(dtf);
	}

	public String getPocetakStr() {
		return pocetakStr;
	}

	public void setPocetakStr(String pocetakStr) {
		this.pocetakStr = pocetakStr;
	}

	public String getKrajStr() {
		return krajStr;
	}

	public void setKrajStr(String krajStr) {
		this.krajStr = krajStr;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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
