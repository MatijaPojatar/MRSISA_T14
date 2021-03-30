package com.backend.springboot.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/*
 * 
 * Osoba kasnije moze da se nasledi kao ostali tipovi, ovo je probna verzija
 */
@Entity
@Table(name="osobe")
@Inheritance(strategy=InheritanceType.JOINED)
public class Osoba {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "ime", nullable = false)
	private String ime;
	@Column(name = "prezime", nullable = false)
	private String prezime;
	@Column(name = "mail", nullable = false,unique=true)
	private String mail; 
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "adresa", nullable = false)
	private String adresa;
	@Column(name = "grad", nullable = false)
	private String grad;
	@Column(name = "drzava", nullable = false)
	private String drzava;
	@Column(name = "brojTelefona", nullable = false)
	private String brojTelefona;
	@Column(name = "pol", nullable = false)
	private Pol pol;
	@Column(name = "datumRodjenja", nullable = false)
	private Date datumRodjenja;
	@Column(name = "promenjenaLozinka", nullable = false)
	private boolean promenjenaLozinka;
	
	public Osoba() {
		super();
	}

	public Osoba(Integer id, String ime, String prezime, String mail, String password, String adresa, String grad,
			String drzava, String brojTelefona, Pol pol, Date datumRodjenja, boolean promenjenaLozinka) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.mail = mail;
		this.password = password;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.brojTelefona = brojTelefona;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;
		this.promenjenaLozinka = promenjenaLozinka;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public boolean isPromenjenaLozinka() {
		return promenjenaLozinka;
	}

	public void setPromenjenaLozinka(boolean promenjenaLozinka) {
		this.promenjenaLozinka = promenjenaLozinka;
	}
}
