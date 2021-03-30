package com.backend.springboot.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="dermatolozi")
public class Dermatolog extends Osoba implements IFarmaceutDermatolog{
	
	@Column(name = "pocetakRadnogVremena", nullable = false)
	private Date pocetakRadnogVremena;
	@Column(name = "krajRadnogVremena", nullable = false)
	private Date krajRadnogVremena;
	@ManyToMany
	@JoinTable(name = "zaposlenje_dermatolog", joinColumns = @JoinColumn(name = "dermatolog_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "apoteka_id", referencedColumnName = "id"))
	private Set<Apoteka> apoteke=new HashSet<Apoteka>();
	@OneToMany(mappedBy = "dermatolog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Pregled> pregledi=new ArrayList<Pregled>();


	public Dermatolog() {
		super();
	}

	public Date getPocetakRadnogVremena() {
		return pocetakRadnogVremena;
	}

	public void setPocetakRadnogVremena(Date pocetakRadnogVremena) {
		this.pocetakRadnogVremena = pocetakRadnogVremena;
	}

	public Date getKrajRadnogVremena() {
		return krajRadnogVremena;
	}

	public void setKrajRadnogVremena(Date krajRadnogVremena) {
		this.krajRadnogVremena = krajRadnogVremena;
	}

	public List<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(List<Pregled> pregledi) {
		this.pregledi = pregledi;
	}

	public Set<Apoteka> getApoteke() {
		return apoteke;
	}

	public void setApoteke(Set<Apoteka> apoteke) {
		this.apoteke = apoteke;
	}
	
	
	
	
	
	


}
