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

import com.backend.springboot.dto.DobavljacDTO;

@Entity
@Table(name="dobavljaci")
public class Dobavljac extends Osoba{
	
	@Column(name = "nazivPreduzeca", nullable = false)
	private String nazivPreduzeca;
	
	@OneToMany(mappedBy = "dobavljac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Ponuda> ponude = new ArrayList<Ponuda>();
	
	@OneToMany(mappedBy = "dobavljacLekova")
	private List<LekNaStanju> lekoviNaStanju;

	public Dobavljac(int id, String ime, String prezime, String mail, String password, String adresa, String grad,
			String drzava, String brojTelefona, Pol pol, LocalDate datumRodjenja, boolean promenjenaLozinka,boolean enabled,String nazivPreduzeca) {
		super(id, ime, prezime, mail, password, adresa, grad, drzava, brojTelefona, pol, datumRodjenja, promenjenaLozinka, enabled);
		this.nazivPreduzeca = nazivPreduzeca;
	}

	public Dobavljac() {
		super();
	}

	public Dobavljac(DobavljacDTO d) {
		this.setId(d.getId());
		this.setIme(d.getIme());
		this.setPrezime(d.getPrezime());
		this.setMail(d.getMail());
		this.setPassword(d.getPassword());
		this.setAdresa(d.getAdresa());
		this.setGrad(d.getGrad());
		this.setDrzava(d.getDrzava());
		this.setBrojTelefona(d.getBrojTelefona());
		this.setPol(d.getPol());
		this.setDatumRodjenja(d.getDatumRodjenja());
		this.setNazivPreduzeca(d.getNazivPreduzeca());
	}
	
	public String getNazivPreduzeca() {
		return nazivPreduzeca;
	}

	public void setNazivPreduzeca(String nazivPreduzeca) {
		this.nazivPreduzeca = nazivPreduzeca;
	}

	public List<Ponuda> getPonude() {
		return ponude;
	}

	public void setPonude(List<Ponuda> ponude) {
		this.ponude = ponude;
	}

	public List<LekNaStanju> getLekoviNaStanju() {
		return lekoviNaStanju;
	}

	public void setLekoviNaStanju(List<LekNaStanju> lekoviNaStanju) {
		this.lekoviNaStanju = lekoviNaStanju;
	}
	
	
}
