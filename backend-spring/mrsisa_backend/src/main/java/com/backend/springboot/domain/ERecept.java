package com.backend.springboot.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="erecepti")
public class ERecept {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "datum_izdavanja")
	private LocalDate datumIzdavanja;
	
	@Column(name = "status")
	private StatusErecepta status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Pacijent pacijent;
	
	@OneToMany(mappedBy = "erecept", fetch = FetchType.LAZY)
	private List<LekERecepta> lekovi = new ArrayList<LekERecepta>();

	public ERecept() {
		super();
	}
	

	public ERecept(Integer id, LocalDate datumIzdavanja, StatusErecepta status, Pacijent pacijent,
			List<LekERecepta> lekovi) {
		super();
		this.id = id;
		this.datumIzdavanja = datumIzdavanja;
		this.status = status;
		this.pacijent = pacijent;
		this.lekovi = lekovi;
	}


	public ERecept(Integer id, LocalDate datumIzdavanja,StatusErecepta status, Pacijent pacijent) {
		super();
		this.id = id;
		this.datumIzdavanja = datumIzdavanja;
		this.pacijent = pacijent;
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

	public LocalDate getDatumIzdavanja() {
		return datumIzdavanja;
	}

	public void setDatumIzdavanja(LocalDate datumIzdavanja) {
		this.datumIzdavanja = datumIzdavanja;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public List<LekERecepta> getLekovi() {
		return lekovi;
	}

	public void setLekovi(List<LekERecepta> lekovi) {
		this.lekovi = lekovi;
	}
	
	
}