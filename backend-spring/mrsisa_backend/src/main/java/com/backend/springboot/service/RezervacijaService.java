package com.backend.springboot.service;

import java.util.Collection;

import com.backend.springboot.domain.RezervacijaLeka;

public interface RezervacijaService {
	Collection<RezervacijaLeka> findAll();
	
	RezervacijaLeka addRezervacija(RezervacijaLeka rezervacija);
	
	RezervacijaLeka findOne(String id);
	
	void delete(String id);
}
