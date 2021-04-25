package com.backend.springboot.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.backend.springboot.dto.PacijentDTO;

@Entity
@Table(name="pacijenti")
public class Pacijent extends Osoba {
	@Column(name = "brojPoena", nullable = false)
	private Integer brojPoena;
	@Column(name = "penali", nullable = false)
	private Integer penali;
	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Termin> termini=new ArrayList<Termin>();
	
	@OneToMany(mappedBy = "pacijent", fetch = FetchType.LAZY, cascade = CascadeType.ALL) //proveriti
	private List<ZalbaNaApoteku> zalbeNaApoteke = new ArrayList<ZalbaNaApoteku>();
	
	@ManyToMany
	@JoinTable(name = "pacijent_alergije", joinColumns = @JoinColumn(name = "pacijent_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "lek_id", referencedColumnName = "id"))
	private List<Lek> alergije = new ArrayList<Lek>();
	
	public Pacijent() {
		super();
	}

	public Pacijent(Integer id, String ime, String prezime, String mail, String password, String adresa, String grad,
			String drzava, String brojTelefona, Pol pol, LocalDate datumRodjenja, boolean promenjenaLozinka, Integer brojPoena, Integer penali) {
		super(id, ime, prezime, mail, password, adresa, grad, drzava, brojTelefona, pol, datumRodjenja, promenjenaLozinka);
		this.brojPoena = brojPoena;
		this.penali = penali;
	}
	

	public Pacijent(PacijentDTO p) {
		this.setId(p.getId());
		this.setIme(p.getIme());
		this.setPrezime(p.getPrezime());
		this.setMail(p.getMail());
		this.setPassword(p.getPassword());
		this.setAdresa(p.getAdresa());
		this.setGrad(p.getGrad());
		this.setDrzava(p.getDrzava());
		this.setBrojTelefona(p.getBrojTelefona());
		this.setPol(p.getPol());
		this.setDatumRodjenja(p.getDatumRodjenja());
		this.setBrojPoena(p.getBrojPoena());
		this.setPenali(p.getPenali());
	}

	public List<ZalbaNaApoteku> getZalbeNaApoteke() {
		return zalbeNaApoteke;
	}

	public void setZalbeNaApoteke(List<ZalbaNaApoteku> zalbeNaApoteke) {
		this.zalbeNaApoteke = zalbeNaApoteke;
	}

	public void addZalbaNaApoteku(ZalbaNaApoteku zalba) {
		this.zalbeNaApoteke.add(zalba);
	}
	
	public void removeZalbaNaApoteku(ZalbaNaApoteku zalba) {
		this.zalbeNaApoteke.remove(zalba);
	}
	
	public Integer getBrojPoena() {
		return brojPoena;
	}

	public void setBrojPoena(Integer brojPoena) {
		this.brojPoena = brojPoena;
	}

	public Integer getPenali() {
		return penali;
	}

	public void setPenali(Integer penali) {
		this.penali = penali;
	}

	public List<Termin> getTermini() {
		return termini;
	}

	public void setTermini(List<Termin> termini) {
		this.termini = termini;
	}

	public List<Lek> getAlergije() {
		return alergije;
	}

	public void setAlergije(List<Lek> alergije) {
		this.alergije = alergije;
	}
}
