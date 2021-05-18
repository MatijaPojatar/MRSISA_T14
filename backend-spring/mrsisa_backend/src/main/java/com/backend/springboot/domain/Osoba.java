package com.backend.springboot.domain;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * 
 * Osoba kasnije moze da se nasledi kao ostali tipovi, ovo je probna verzija
 */
@Entity
@Table(name="osobe")
@Inheritance(strategy=InheritanceType.JOINED)
@SQLDelete(sql
	    = "UPDATE osobe "
	    + "SET obrisan = true "
	    + "WHERE id = ?")
@Where(clause = "obrisan = false")
public class Osoba implements UserDetails{
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
	private LocalDate datumRodjenja;
	@Column(name = "promenjenaLozinka", nullable = false)
	private boolean promenjenaLozinka;
	@Column(name="enabled")  //todo napraviti non nullable, modifikovati konstruktor i default podatke u bazi
	private boolean enabled;
	
	@Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;   //mozda i nepotrebno, ali stavljeno zbog tokena
	
	@Column(name = "obrisan", nullable = false)
	private boolean obrisan;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "osoba_role",
			joinColumns = @JoinColumn(name = "osoba_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles;
	
	
	public Osoba() {
		super();
	}

	public Osoba(Integer id, String ime, String prezime, String mail, String password, String adresa, String grad,
			String drzava, String brojTelefona, Pol pol, LocalDate datumRodjenja, boolean promenjenaLozinka, boolean enabled) {
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
		this.obrisan = false;
		this.enabled = enabled; 
	}

	public Timestamp getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
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
		Timestamp now = new Timestamp(new Date().getTime());
		this.setLastPasswordResetDate(now);
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

	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public boolean isPromenjenaLozinka() {
		return promenjenaLozinka;
	}

	public void setPromenjenaLozinka(boolean promenjenaLozinka) {
		this.promenjenaLozinka = promenjenaLozinka;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return mail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
