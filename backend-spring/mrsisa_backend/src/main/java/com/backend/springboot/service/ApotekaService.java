package com.backend.springboot.service;

import java.util.Collection;

import com.backend.springboot.domain.Apoteka;

public interface ApotekaService {
	Collection<Apoteka> findAll();
	
	Apoteka addApoteka(Apoteka apoteka);
	
	Apoteka findOne(String sifra);
	
	void delete(String sifra);
}
