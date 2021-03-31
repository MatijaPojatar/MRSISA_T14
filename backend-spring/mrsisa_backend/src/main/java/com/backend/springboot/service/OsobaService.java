package com.backend.springboot.service;

import java.util.Collection;

import com.backend.springboot.domain.Osoba;
import com.backend.springboot.domain.Pacijent;

public interface OsobaService {

	Collection<Osoba> findAll();
	
	Osoba addOsoba(Osoba osobaInfo);
	
	Osoba findOne(Integer id);
	
	void delete(Integer id);

	Boolean promeniLozinku(int id, String staraLozinka, String novaLozinka);

}
