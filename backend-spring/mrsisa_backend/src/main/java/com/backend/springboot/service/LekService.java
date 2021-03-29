package com.backend.springboot.service;

import java.util.Collection;

import com.backend.springboot.domain.Lek;

public interface LekService {
	
	Collection<Lek> findAll();
	
	Lek addLek(Lek lek);
	
	Lek findOne(int sifra);
	
	void delete(int sifra);
}
