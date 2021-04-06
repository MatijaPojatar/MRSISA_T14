package com.backend.springboot.service;

import java.util.Collection;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Lek;

public interface ApotekaService {
	/*
	 * Collection<Apoteka> findAll();
	 * 
	 * Apoteka addApoteka(Apoteka apoteka);
	 * 
	 * Apoteka findOne(String sifra);
	 * 
	 * void delete(String sifra);
	 */
	
	Collection <Lek> preuzmiLekoveApoteke(String sifra);
	
	Collection<Apoteka> pronadjiApoteke(String naziv, String grad);
}
