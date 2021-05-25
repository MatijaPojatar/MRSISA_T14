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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.springboot.dto.ApotekaDTO;

@Entity
@Table(name="apoteke")
public class Apoteka {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "naziv", nullable = false)
	private String naziv;
	@Column(name = "adresa", nullable = false)
	private String adresa;
	@Column(name = "grad", nullable = false)
	private String grad;
	@Column(name = "drzava", nullable = false)
	private String drzava;
	@Column(name = "opis", nullable = false)
	private String opis;
	@ManyToMany(mappedBy = "snabdeveneApoteke")
	private List<Lek> lekovi=new ArrayList<Lek>();
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Farmaceut> farmaceuti = new HashSet<Farmaceut>();
	@ManyToMany(mappedBy = "apoteke")
	private Set<Dermatolog> dermatolozi = new HashSet<Dermatolog>();
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Termin> termini=new ArrayList<Termin>();
	@OneToOne(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Magacin magacin;
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AkcijaPromocija> akcije = new ArrayList<AkcijaPromocija>();
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AdministratorApoteke> administratori = new HashSet<AdministratorApoteke>();
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ZalbaNaApoteku> zalbe = new ArrayList<ZalbaNaApoteku>();
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OdsustvoFarmaceut> odsustva=new ArrayList<OdsustvoFarmaceut>();
	
	@ManyToMany(mappedBy = "pretplaceneApoteke")
	private List<Pacijent> pretplaceniPacijenti = new ArrayList<Pacijent>();
	
	@OneToMany(mappedBy = "apoteka")
	private List<DermatologApoteka> zaposlenja=new ArrayList<DermatologApoteka>();
	
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RezervacijaLeka> rezervacije = new HashSet<RezervacijaLeka>();
	

	public Apoteka(Integer id, String naziv, String adresa, String grad, String drzava, String opis,
			List<Lek> lekovi, Set<Farmaceut> farmaceuti, Set<Dermatolog> dermatolozi, List<Termin> termini,
			Magacin magacin) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.opis = opis;
		this.lekovi = lekovi;
		this.farmaceuti = farmaceuti;
		this.dermatolozi = dermatolozi;
		this.termini = termini;
		this.magacin = magacin;
	}

	public Apoteka() {
		super();
	}

	public Apoteka(Integer id,String naziv, String adresa, String grad, String drzava, String opis) {
		super();
		this.id=id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.opis = opis;
	}
	
	public Apoteka(Integer id,String naziv, String adresa, String grad, String drzava, String opis, ArrayList<Lek> lekovi) {
		super();
		this.id=id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.opis = opis;
		this.lekovi = lekovi;
	}
	public Apoteka(Integer id, String naziv, String adresa, String grad, String drzava, String opis,
			List<Lek> lekovi, Set<Farmaceut> farmaceuti, Set<Dermatolog> dermatolozi, List<Termin> termini,
			Magacin magacin, List<AkcijaPromocija> akcije) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.opis = opis;
		this.lekovi = lekovi;
		this.farmaceuti = farmaceuti;
		this.dermatolozi = dermatolozi;
		this.termini = termini;
		this.magacin = magacin;
		this.akcije = akcije;
	}
	
	

	public Apoteka(ApotekaDTO dto) {
		super();
		this.id = dto.getId();
		this.naziv = dto.getNaziv();
		this.adresa = dto.getAdresa();
		this.grad = dto.getGrad();
		this.drzava = dto.getDrzava();
		this.opis = dto.getOpis();
		this.lekovi = dto.getLekovi();
		this.farmaceuti = dto.getFarmaceuti();
		this.dermatolozi = dto.getDermatolozi();
		this.termini = dto.getTermini();
		this.magacin = dto.getMagacin();
		this.akcije = dto.getAkcije();
		this.administratori = dto.getAdministratori();
	}

	public List<ZalbaNaApoteku> getZalbe() {
		return zalbe;
	}

	public void setZalbe(List<ZalbaNaApoteku> zalbe) {
		this.zalbe = zalbe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Lek> getLekovi() {
		return lekovi;
	}

	public void setLekovi(List<Lek> lekovi) {
		this.lekovi = lekovi;
	}

	public Set<Farmaceut> getFarmaceuti() {
		return farmaceuti;
	}

	public void setFarmaceuti(Set<Farmaceut> farmaceuti) {
		this.farmaceuti = farmaceuti;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Termin> getTermini() {
		return termini;
	}

	public void setTermini(List<Termin> termini) {
		this.termini = termini;
	}

	public Set<Dermatolog> getDermatolozi() {
		return dermatolozi;
	}

	public void setDermatolozi(Set<Dermatolog> dermatolozi) {
		this.dermatolozi = dermatolozi;
	}
	
	public Magacin getMagacin() {
		return magacin;
	}

	public void setMagacin(Magacin magacin) {
		this.magacin = magacin;
	}

	public List<AkcijaPromocija> getAkcije() {
		return akcije;
	}

	public void setAkcije(List<AkcijaPromocija> akcije) {
		this.akcije = akcije;
	}

	

	public Set<AdministratorApoteke> getAdministratori() {
		return administratori;
	}

	public void setAdministratori(Set<AdministratorApoteke> administratori) {
		this.administratori = administratori;
	}

	public List<OdsustvoFarmaceut> getOdsustva() {
		return odsustva;
	}

	public void setOdsustva(List<OdsustvoFarmaceut> odsustva) {
		this.odsustva = odsustva;
	}

	public List<DermatologApoteka> getZaposlenja() {
		return zaposlenja;
	}

	public void setZaposlenja(List<DermatologApoteka> zaposlenja) {
		this.zaposlenja = zaposlenja;
	}
	
	public Set<Dermatolog> getDermatoloziV2() {
		Set<Dermatolog> dermatolozi=new HashSet<Dermatolog>();
		for(DermatologApoteka da:this.zaposlenja) {
			dermatolozi.add(da.getDermatolog());
		}
		return dermatolozi;
	}

	public Set<RezervacijaLeka> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(Set<RezervacijaLeka> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public List<Pacijent> getPretplaceniPacijenti() {
		return pretplaceniPacijenti;
	}

	public void setPretplaceniPacijenti(List<Pacijent> pretplaceniPacijenti) {
		this.pretplaceniPacijenti = pretplaceniPacijenti;
	}
	
	
	
	
	
	
}
