package com.backend.springboot.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.backend.springboot.domain.ERecept;
import com.backend.springboot.domain.LekERecepta;
import com.backend.springboot.domain.StatusErecepta;

public class EReceptDTO {
	
	private Integer id, pacijentId;
	private LocalDate datumIzdavanja;
	private StatusErecepta status;
	private List<LekEReceptaDTO> lekoviErecepta;
	
	public EReceptDTO() {
		super();
	}
	public EReceptDTO(ERecept e) {
		super();
		this.id = e.getId();
		this.pacijentId = e.getPacijent().getId();
		this.datumIzdavanja = e.getDatumIzdavanja();
		this.status = e.getStatus();
		this.lekoviErecepta = new ArrayList<LekEReceptaDTO>();
		
//		if(e.getLekovi() != null) {
			for (LekERecepta l : e.getLekovi()) {
				this.lekoviErecepta.add(new LekEReceptaDTO(l));
			}
//		}
	}
	
	public List<LekEReceptaDTO> getLekoviErecepta() {
		return lekoviErecepta;
	}
	public void setLekoviErecepta(List<LekEReceptaDTO> lekoviErecepta) {
		this.lekoviErecepta = lekoviErecepta;
	}
	public StatusErecepta getStatus() {
		return status;
	}
	public void setStatus(StatusErecepta status) {
		this.status = status;
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
