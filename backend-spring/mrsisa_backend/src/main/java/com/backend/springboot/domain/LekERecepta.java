package com.backend.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.springboot.dto.LekEReceptaDTO;

@Entity
@Table(name="lekovi_erecepta")
public class LekERecepta {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="kolicina", nullable = false)
	private Integer kolicina;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Lek lek;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ERecept erecept;

	public LekERecepta() {
		super();
	}
	
	public LekERecepta(LekEReceptaDTO dto) {
		this.id = dto.getId();
		this.kolicina = dto.getKolicina();
	}
	
	public LekERecepta(Integer id, Integer kolicina, Lek lek, ERecept erecept) {
		super();
		this.id = id;
		this.kolicina = kolicina;
		this.lek = lek;
		this.erecept = erecept;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getKolicina() {
		return kolicina;
	}

	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}

	public Lek getLek() {
		return lek;
	}

	public void setLek(Lek lek) {
		this.lek = lek;
	}

	public ERecept getErecept() {
		return erecept;
	}

	public void setErecept(ERecept erecept) {
		this.erecept = erecept;
	}
	
	
}
