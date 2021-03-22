package com.backend.springboot.repository;

import java.util.Collection;

import com.backend.springboot.domain.Apoteka;


public interface ApotekaRepository {
	Collection<Apoteka> findAll();
	
	Apoteka addApoteka(Apoteka apoteka);
	
	Apoteka findOne(String sifra);
	
	void delete(String sifra);
}
