package com.backend.springboot.dto;

import java.time.LocalDateTime;

import com.backend.springboot.domain.OdsustvoFarmaceut;

public class OdsustvoFarmaceutDTO {
	
	private Integer id,farmaceutId,apotekaId;
	private LocalDateTime pocetak,kraj;
	private boolean odobren;
	
	
	
	public OdsustvoFarmaceutDTO() {
		super();
	}



	public OdsustvoFarmaceutDTO(OdsustvoFarmaceut o) {
		super();
		this.id=o.getId();
		this.farmaceutId=o.getFarmaceut().getId();
		this.apotekaId=o.getApoteka().getId();
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



	public Integer getFarmaceutId() {
		return farmaceutId;
	}



	public void setFarmaceutId(Integer farmaceutId) {
		this.farmaceutId = farmaceutId;
	}



	public Integer getApotekaId() {
		return apotekaId;
	}



	public void setApotekaId(Integer apotekaId) {
		this.apotekaId = apotekaId;
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
