package com.backend.springboot.domain;

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

@Entity
@Table(name="farmaceuti")
public class Farmaceut extends Osoba implements IFarmaceutDermatolog {
	
	@Column(name = "pocetakRadnogVremena", nullable = false)
	private Date pocetakRadnogVremena;
	@Column(name = "krajRadnogVremena", nullable = false)
	private Date krajRadnogVremena;
	@ManyToOne(fetch = FetchType.EAGER)
	private Apoteka apoteka;
	@OneToMany(mappedBy = "farmaceut", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Savetovanje> savetovanja=new ArrayList<Savetovanje>();

	public Farmaceut() {
		super();
	}

	

	public Farmaceut(Integer id, String ime, String prezime, String mail, String password, String adresa, String grad,
			String drzava, String brojTelefona, Pol pol, Date datumRodjenja, boolean promenjenaLozinka
			,Apoteka apoteka,Date pocetakRadnogVremena,Date krajRadnogVremena) {
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



	public Apoteka getApoteka() {
		return apoteka;
	}



	public void setApoteka(Apoteka apoteka) {
		this.apoteka = apoteka;
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
	
	
	
	

	
	
}
