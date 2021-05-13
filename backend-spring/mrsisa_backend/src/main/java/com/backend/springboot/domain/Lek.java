package com.backend.springboot.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="lekovi")
public class Lek {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "naziv", nullable = false)
	private String naziv;
	@Column(name = "sastav", nullable = false)
	private String sastav;
	@Column(name = "proizvodjac", nullable = false)
	private String proizvodjac;
	@Column(name = "napomena", nullable = false)
	private String napomena;
	@Column(name = "rezimIzdavanja", nullable = false)
	private RezimIzdavanja rezimIzdavanja;
	@Column(name = "oblikLeka", nullable = false)
	private OblikLeka oblikLeka;
	@Column(name = "vrstaLeka", nullable = false)
	private VrstaLeka vrstaLeka;
	
	
	@ManyToMany
	@JoinTable(name = "apoteke_lekovi", joinColumns = @JoinColumn(name = "lek_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "apoteka_id", referencedColumnName = "id"))
	private List<Apoteka> snabdeveneApoteke=new ArrayList<Apoteka>();
	
	@ManyToMany
	@JoinTable(name = "zamenski_lekovi", 
	joinColumns = @JoinColumn(name = "lek_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "zamenski_id", referencedColumnName = "id"))
	private List<Lek> zamenskiLekovi = new ArrayList<Lek>();
	
	@ManyToMany(mappedBy = "zamenskiLekovi")
	private List<Lek> zamenskiZaLekove = new ArrayList<Lek>();
	
	@OneToMany(mappedBy = "lek")
	private Set<LekNaStanju> lekoviNaStanju;
	
	@ManyToMany(mappedBy = "alergije")
	private List<Pacijent> alergicniPacijenti = new ArrayList<Pacijent>();
	
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RezervacijaLeka> rezervacije = new HashSet<RezervacijaLeka>();
	
	public Lek() {
		super();
	}

	public Lek(Integer id, String naziv, String sastav, String proizvodjac, String napomena,
			RezimIzdavanja rezimIzdavanja, OblikLeka oblikLeka, VrstaLeka vrstaLeka) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.sastav = sastav;
		this.proizvodjac = proizvodjac;
		this.napomena = napomena;
		this.rezimIzdavanja = rezimIzdavanja;
		this.oblikLeka = oblikLeka;
		this.vrstaLeka = vrstaLeka;
	}

	public void addZamenskiLek(Lek lek) {
		this.zamenskiLekovi.add(lek);
	}
	
	public void removeZamenskiLek(Lek lek) {
		this.zamenskiLekovi.remove(lek);
	}
	
	public void addZamenskiZaLek(Lek lek) {
		this.zamenskiZaLekove.add(lek);
	}
	
	public void removeZamenskiZaLek(Lek lek) {
		this.zamenskiZaLekove.remove(lek);
	}
	
	public List<Lek> getZamenskiLekovi() {
		return zamenskiLekovi;
	}

	public void setZamenskiLekovi(List<Lek> zamenskiLekovi) {
		this.zamenskiLekovi = zamenskiLekovi;
	}

	public List<Lek> getZamenskiZaLekove() {
		return zamenskiZaLekove;
	}

	public void setZamenskiZaLekove(List<Lek> zamenskiZaLekove) {
		this.zamenskiZaLekove = zamenskiZaLekove;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Apoteka> getSnabdeveneApoteke() {
		return snabdeveneApoteke;
	}

	public void setSnabdeveneApoteke(List<Apoteka> snadbeveneApoteke) {
		this.snabdeveneApoteke = snadbeveneApoteke;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSastav() {
		return sastav;
	}

	public void setSastav(String sastav) {
		this.sastav = sastav;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public RezimIzdavanja getRezimIzdavanja() {
		return rezimIzdavanja;
	}

	public void setRezimIzdavanja(RezimIzdavanja rezimIzdavanja) {
		this.rezimIzdavanja = rezimIzdavanja;
	}

	public OblikLeka getOblikLeka() {
		return oblikLeka;
	}

	public void setOblikLeka(OblikLeka oblikLeka) {
		this.oblikLeka = oblikLeka;
	}

	public VrstaLeka getVrstaLeka() {
		return vrstaLeka;
	}

	public void setVrstaLeka(VrstaLeka vrstaLeka) {
		this.vrstaLeka = vrstaLeka;
	}

	public Set<LekNaStanju> getLekoviNaStanju() {
		return lekoviNaStanju;
	}

	public void setLekoviNaStanju(Set<LekNaStanju> lekoviNaStanju) {
		this.lekoviNaStanju = lekoviNaStanju;
	}

	public List<Pacijent> getAlergicniPacijenti() {
		return alergicniPacijenti;
	}

	public void setAlergicniPacijenti(List<Pacijent> alergicniPacijenti) {
		this.alergicniPacijenti = alergicniPacijenti;
	}

	public Set<RezervacijaLeka> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(Set<RezervacijaLeka> rezervacije) {
		this.rezervacije = rezervacije;
	}
	
	
}
