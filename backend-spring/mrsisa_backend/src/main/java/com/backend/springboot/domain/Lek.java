package com.backend.springboot.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

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
	@Column(name = "naRecept", nullable = false)
	private Boolean naRecept;
	
	
	@ManyToMany
	@JoinTable(name = "apoteke_lekovi", joinColumns = @JoinColumn(name = "lek_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "apoteka_id", referencedColumnName = "id"))
	private List<Apoteka> snabdeveneApoteke=new ArrayList<Apoteka>();
	
	public Lek() {
		super();
	}

	public Lek(Integer id, String naziv, String sastav, String proizvodjac, String napomena,
			RezimIzdavanja rezimIzdavanja, OblikLeka oblikLeka, VrstaLeka vrstaLeka, Boolean naRecept) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.sastav = sastav;
		this.proizvodjac = proizvodjac;
		this.napomena = napomena;
		this.rezimIzdavanja = rezimIzdavanja;
		this.oblikLeka = oblikLeka;
		this.vrstaLeka = vrstaLeka;
		this.naRecept = naRecept;
	}

	public Boolean getNaRecept() {
		return naRecept;
	}

	public void setNaRecept(Boolean naRecept) {
		this.naRecept = naRecept;
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
	
	
}
