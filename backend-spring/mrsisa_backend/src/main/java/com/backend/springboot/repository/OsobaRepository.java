package com.backend.springboot.repository;

import java.util.Collection;

import com.backend.springboot.domain.Osoba;


public interface OsobaRepository {
	Collection<Osoba> findAll();
	
	Osoba addOsoba(Osoba osoba);
	
	Osoba findOne(int id);
	
	void delete(int id);
	
	void promeniLozinku(int id,String novaLozinka);
}
