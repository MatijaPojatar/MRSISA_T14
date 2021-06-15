package com.backend.springboot.domain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
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

import org.apache.tomcat.util.buf.UriUtil;
import org.springframework.web.util.UriUtils;

import com.backend.springboot.dto.ApotekaCreateDTO;
import com.backend.springboot.dto.ApotekaDTO;
import com.google.gson.Gson;

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
	@ManyToMany(mappedBy = "snabdeveneApoteke", fetch = FetchType.LAZY)
	private List<Lek> lekovi=new ArrayList<Lek>();
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Farmaceut> farmaceuti = new HashSet<Farmaceut>();
	@ManyToMany(mappedBy = "apoteke")
	private Set<Dermatolog> dermatolozi = new HashSet<Dermatolog>();
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Termin> termini=new ArrayList<Termin>();
	@OneToOne(mappedBy = "apoteka", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Magacin magacin;
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<AkcijaPromocija> akcije = new ArrayList<AkcijaPromocija>();
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<AdministratorApoteke> administratori = new HashSet<AdministratorApoteke>();
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ZalbaNaApoteku> zalbe = new ArrayList<ZalbaNaApoteku>();
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OdsustvoFarmaceut> odsustva=new ArrayList<OdsustvoFarmaceut>();
	
	@OneToMany(mappedBy= "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ERecept> erecepti = new ArrayList<ERecept>();
	
	
	
	@ManyToMany(mappedBy = "pretplaceneApoteke")
	private List<Pacijent> pretplaceniPacijenti = new ArrayList<Pacijent>();
	
	@OneToMany(mappedBy = "apoteka")
	private List<DermatologApoteka> zaposlenja=new ArrayList<DermatologApoteka>();
	
	@OneToMany(mappedBy = "apoteka", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RezervacijaLeka> rezervacije = new HashSet<RezervacijaLeka>();
	
	@Column(name = "ocena", nullable = false)
	Double ocena;
	
	@Column(name = "latitude", nullable = true)
	Double latitude;
	
	@Column(name = "longitude", nullable = true)
	Double longitude;
	
	
	

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
	
	public Apoteka(ApotekaCreateDTO a) {
		this.id = a.getId();
		this.naziv = a.getNaziv();
		this.adresa = a.getAdresa();
		this.grad = a.getGrad();
		this.drzava = a.getDrzava();
		this.opis = a.getOpis();
		this.ocena = 0.0;
		this.lekovi = new ArrayList<Lek>();
		this.farmaceuti = new HashSet<Farmaceut>();
		this.dermatolozi =new HashSet<Dermatolog>();
		this.termini = new ArrayList<Termin>();
		this.akcije = new ArrayList<AkcijaPromocija>();
		this.administratori =new HashSet<AdministratorApoteke>();
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

	public List<ERecept> getErecepti() {
		return erecepti;
	}

	public void setErecepti(List<ERecept> erecepti) {
		this.erecepti = erecepti;
	}

	public List<ZalbaNaApoteku> getZalbe() {
		return zalbe;
	}

	public Double getOcena() {
		return ocena;
	}

	public void setOcena(Double ocena) {
		this.ocena = ocena;
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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public void getGeoPointFromAddress(String locationAddress) {
		try {
	        URL url = new URL(
	                "http://maps.googleapis.com/maps/api/geocode/json?address="
	                        + UriUtils.encodeQuery((locationAddress + "&sensor=true"), Charset.defaultCharset()));
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");

	        if (conn.getResponseCode() != 200) {
	            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
	        }
	        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

	        String output = "", full = "";
	        while ((output = br.readLine()) != null) {
	            System.out.println(output);
	            full += output;
	        }

	        
	        conn.disconnect();
	    } catch (MalformedURLException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
	
	
	
	
	
	
}
