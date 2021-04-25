package com.backend.springboot.dto;

import java.time.LocalDateTime;

import com.backend.springboot.domain.Pacijent;

public class PacijentTerminDTO extends PacijentDTO {

	LocalDateTime poslednjiPregled;
	
	public PacijentTerminDTO() {
		super();
	}

	public PacijentTerminDTO(Pacijent p,LocalDateTime poslednjiPregled) {
		super(p);
		this.poslednjiPregled=poslednjiPregled;
	}

	public LocalDateTime getPoslednjiPregled() {
		return poslednjiPregled;
	}

	public void setPoslednjiPregled(LocalDateTime poslednjiPregled) {
		this.poslednjiPregled = poslednjiPregled;
	}
	
	
	
	
}
