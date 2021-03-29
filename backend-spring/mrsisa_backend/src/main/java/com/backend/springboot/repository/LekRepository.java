package com.backend.springboot.repository;

import java.util.Collection;

import com.backend.springboot.domain.Lek;


public interface LekRepository {
	
	Collection<Lek> findAll();
	
	Lek addLek(Lek lek);
	
	Lek findOne(int sifra);
	
	void delete(int sifra);
}
