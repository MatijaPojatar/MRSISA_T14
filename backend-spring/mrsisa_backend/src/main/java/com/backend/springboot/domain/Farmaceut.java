package com.backend.springboot.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="farmaceuti")
@SQLDelete(sql
	    = "UPDATE farmaceuti "
	    + "SET obrisan = true "
	    + "WHERE id = ?")
@Where(clause = "obrisan = false")
public class Farmaceut extends Osoba implements IFarmaceutDermatolog {
	
	@Column(name = "pocetakRadnogVremena", nullable = false)
	private LocalTime pocetakRadnogVremena;
	@Column(name = "krajRadnogVremena", nullable = false)
	private LocalTime krajRadnogVremena;
	@ManyToOne(fetch = FetchType.EAGER)
	private Apoteka apoteka;
	@OneToMany(mappedBy = "farmaceut", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Savetovanje> savetovanja=new ArrayList<Savetovanje>();
	@Column(name = "obrisan", nullable = false)
	private boolean obrisan;

	public Farmaceut() {
		super();
	}

	

	public Farmaceut(Integer id, String ime, String prezime, String mail, String password, String adresa, String grad,
			String drzava, String brojTelefona, Pol pol, LocalDate datumRodjenja, boolean promenjenaLozinka
			,Apoteka apoteka,LocalTime pocetakRadnogVremena,LocalTime krajRadnogVremena) {
		super(id, ime, prezime, mail, password, adresa, grad, drzava, brojTelefona, pol, datumRodjenja, promenjenaLozinka);
		this.pocetakRadnogVremena=pocetakRadnogVremena;
		this.krajRadnogVremena=krajRadnogVremena;
		this.apoteka=apoteka;
	}

	
	
	public List<Savetovanje> getSavetovanja() {
		return savetovanja;
	}



	public void setSavetovanja(List<Savetovanje> savetovanja) {
		this.savetovanja = savetovanja;
	}



	public boolean isObrisan() {
		return obrisan;
	}



	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}



	public Apoteka getApoteka() {
		return apoteka;
	}



	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
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



	
	
	
	

	
	
}
