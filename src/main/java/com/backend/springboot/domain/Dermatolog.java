package com.backend.springboot.domain;

import java.time.LocalDate;
import java.time.LocalTime;
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

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.backend.springboot.dto.DermatologDTO;
import com.backend.springboot.dto.DobavljacDTO;

@Entity
@Table(name="dermatolozi")
@SQLDelete(sql
	    = "UPDATE dermatolozi "
	    + "SET obrisan = true "
	    + "WHERE id = ?")
@Where(clause = "obrisan = false")
public class Dermatolog extends Osoba implements IFarmaceutDermatolog{
	
	private static final long serialVersionUID = 1L;
	@Column(name = "pocetakRadnogVremena", nullable = false)
	private LocalTime pocetakRadnogVremena;
	@Column(name = "krajRadnogVremena", nullable = false)
	private LocalTime krajRadnogVremena;
	@ManyToMany
	@JoinTable(name = "zaposlenje_dermatolog", joinColumns = @JoinColumn(name = "dermatolog_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "apoteka_id", referencedColumnName = "id"))
	private Set<Apoteka> apoteke=new HashSet<Apoteka>();
	@OneToMany(mappedBy = "dermatolog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Pregled> pregledi=new ArrayList<Pregled>();
	@OneToMany(mappedBy = "dermatolog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OdsustvoDermatolog> odsustva=new ArrayList<OdsustvoDermatolog>();

	@OneToMany(mappedBy = "dermatolog", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ZalbaNaDermatologa> zalbe = new ArrayList<ZalbaNaDermatologa>();
	
	@OneToMany(mappedBy = "dermatolog")
	private List<DermatologApoteka> zaposlenja=new ArrayList<DermatologApoteka>();
	
	@Column(name = "ocena", nullable = false)
	Double ocena;

	public Dermatolog(int id, String ime, String prezime, String mail, String password, String adresa, String grad,
			String drzava, String brojTelefona, Pol pol, LocalDate datumRodjenja, boolean promenjenaLozinka, boolean enabled, LocalTime pocetakRadnogVremena, LocalTime krajRadnogVremena) {
		super(id, ime, prezime, mail, password, adresa, grad, drzava, brojTelefona, pol, datumRodjenja, promenjenaLozinka, enabled);
		this.pocetakRadnogVremena = pocetakRadnogVremena;
		this.krajRadnogVremena = krajRadnogVremena;
	}


	public Dermatolog() {
		super();
	}

	public Dermatolog(DermatologDTO d) {
		this.setId(d.getId());
		this.setIme(d.getIme());
		this.setPrezime(d.getPrezime());
		this.setMail(d.getMail());
		this.setAdresa(d.getAdresa());
		this.setGrad(d.getGrad());
		this.setDrzava(d.getDrzava());
		this.setBrojTelefona(d.getBrojTelefona());
		this.setPol(d.getPol());
		this.setDatumRodjenja(d.getDatumRodjenja());
		this.setPocetakRadnogVremena(d.getPocetakRadnogVremena());
		this.setKrajRadnogVremena(d.getKrajRadnogVremena());
		this.setOcena(d.getOcena());
	}

	public Double getOcena() {
		return ocena;
	}


	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}


	public List<ZalbaNaDermatologa> getZalbe() {
		return zalbe;
	}


	public void setZalbe(List<ZalbaNaDermatologa> zalbe) {
		this.zalbe = zalbe;
	}


	public LocalTime getPocetakRadnogVremena() {
		return pocetakRadnogVremena;
	}

	public void setPocetakRadnogVremena(LocalTime pocetakRadnogVremena) {
		this.pocetakRadnogVremena = pocetakRadnogVremena;
	}

	public LocalTime getKrajRadnogVremena() {
		return krajRadnogVremena;
	}

	public void setKrajRadnogVremena(LocalTime krajRadnogVremena) {
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


	public List<OdsustvoDermatolog> getOdsustva() {
		return odsustva;
	}


	public void setOdsustva(List<OdsustvoDermatolog> odsustva) {
		this.odsustva = odsustva;
	}

	public List<DermatologApoteka> getZaposlenja() {
		return zaposlenja;
	}


	public void setZaposlenja(List<DermatologApoteka> zaposlenja) {
		this.zaposlenja = zaposlenja;
	}
	
	public Set<Apoteka> getApotekeV2() {
		Set<Apoteka> apoteke=new HashSet<Apoteka>();
		for(DermatologApoteka da:this.zaposlenja) {
			apoteke.add(da.getApoteka());
		}
		return apoteke;
	}
	
	
	
	
	
	


}
