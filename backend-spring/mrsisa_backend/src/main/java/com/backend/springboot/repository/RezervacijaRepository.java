package com.backend.springboot.repository;

import java.util.Collection;

import com.backend.springboot.domain.RezervacijaLeka;

public interface RezervacijaRepository {
	
	Collection<RezervacijaLeka> findAll();
	
	RezervacijaLeka addRezervacija(RezervacijaLeka rezervacija);
	
	RezervacijaLeka findOne(String id);
	
	void delete(String id);
}
