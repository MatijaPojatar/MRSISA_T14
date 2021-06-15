package com.backend.springboot.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="odsustva_dermatolog")
public class OdsustvoDermatolog {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "pocetak", nullable = false)
	private LocalDateTime pocetak;
	@Column(name = "kraj", nullable = false)
	private LocalDateTime kraj;
	@Column(name = "status", nullable = false)
	private StatusZahtevaZaOdmor status;
	@ManyToOne(fetch = FetchType.EAGER)
	private Dermatolog dermatolog;	
	@Column(name = "razlog_odbijanja", nullable = true)
	private String razlog;
	
	@Version
	private Long version;
	

	public OdsustvoDermatolog(Integer id, LocalDateTime pocetak, LocalDateTime kraj, StatusZahtevaZaOdmor status,
			Dermatolog dermatolog) {
		super();
		this.id = id;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.status = status;
		this.dermatolog = dermatolog;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getRazlog() {
		return razlog;
	}

	public void setRazlog(String razlog) {
		this.razlog = razlog;
	}

	public OdsustvoDermatolog() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getPocetak() {
		return pocetak;
	}
	public void setPocetak(LocalDateTime pocetak) {
		this.pocetak = pocetak;
	}
	public LocalDateTime getKraj() {
		return kraj;
	}
	public void setKraj(LocalDateTime kraj) {
		this.kraj = kraj;
	}
	
	public StatusZahtevaZaOdmor getStatus() {
		return status;
	}

	public void setStatus(StatusZahtevaZaOdmor status) {
		this.status = status;
	}

	public Dermatolog getDermatolog() {
		return dermatolog;
	}
	public void setDermatolog(Dermatolog dermatolog) {
		this.dermatolog = dermatolog;
	}
	
	

}
