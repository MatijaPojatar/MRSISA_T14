package com.backend.springboot.dto;

import java.time.LocalDate;

import com.backend.springboot.domain.ERecept;
import com.backend.springboot.domain.StatusNarudzbenice;

public class EReceptDTO {
	
	private Integer id, pacijentId;
	private LocalDate datumIzdavanja;
	
	public EReceptDTO() {
		super();
	}
	public EReceptDTO(ERecept e) {
		super();
		this.id = e.getId();
		this.pacijentId = e.getPacijent().getId();
		this.datumIzdavanja = e.getDatumIzdavanja();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPacijentId() {
		return pacijentId;
	}
	public void setPacijentId(Integer pacijentId) {
		this.pacijentId = pacijentId;
	}
	public LocalDate getDatumIzdavanja() {
		return datumIzdavanja;
	}
	public void setDatumIzdavanja(LocalDate datumIzdavanja) {
		this.datumIzdavanja = datumIzdavanja;
	}
	
	
}
