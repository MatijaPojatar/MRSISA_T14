package com.backend.springboot.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="administratori_sistema")
public class AdministratorSistema extends Osoba {

	private static final long serialVersionUID = 1L;
	@Column(name="potpis", nullable = true)
	private String potpis;
	
	@OneToMany(mappedBy = "administrator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<ZalbaNaApoteku> zalbeNaApoteke = new ArrayList<ZalbaNaApoteku>();
	
	@OneToMany(mappedBy = "administrator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<ZalbaNaDermatologa> zalbeNaDermatologe = new ArrayList<ZalbaNaDermatologa>();
	
	@OneToMany(mappedBy = "administrator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<ZalbaNaFarmaceuta> zalbeNaFarmaceute = new ArrayList<ZalbaNaFarmaceuta>();
	
	public AdministratorSistema() {
		super();
	}
	
	public AdministratorSistema(int id, String ime, String prezime, String mail, String password, String adresa, String grad,
			String drzava, String brojTelefona, Pol pol, LocalDate datumRodjenja, boolean promenjenaLozinka,boolean enabled, String potpis) {
		
		super(id, ime, prezime, mail, password, adresa, grad, drzava, brojTelefona, pol, datumRodjenja, promenjenaLozinka, enabled);
		this.potpis = potpis;
	}

	public List<ZalbaNaApoteku> getZalbeNaApoteke() {
		return zalbeNaApoteke;
	}

	public void setZalbeNaApoteke(List<ZalbaNaApoteku> zalbeNaApoteke) {
		this.zalbeNaApoteke = zalbeNaApoteke;
	}

	public List<ZalbaNaDermatologa> getZalbeNaDermatologe() {
		return zalbeNaDermatologe;
	}

	public void setZalbeNaDermatologe(List<ZalbaNaDermatologa> zalbeNaDermatologe) {
		this.zalbeNaDermatologe = zalbeNaDermatologe;
	}

	public List<ZalbaNaFarmaceuta> getZalbeNaFarmaceute() {
		return zalbeNaFarmaceute;
	}

	public void setZalbeNaFarmaceute(List<ZalbaNaFarmaceuta> zalbeNaFarmaceute) {
		this.zalbeNaFarmaceute = zalbeNaFarmaceute;
	}

	public String getPotpis() {
		return potpis;
	}

	public void setPotpis(String potpis) {
		this.potpis = potpis;
	}
	
	
}
