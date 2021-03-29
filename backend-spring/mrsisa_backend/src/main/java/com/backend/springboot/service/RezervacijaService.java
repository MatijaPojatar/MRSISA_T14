package com.backend.springboot.service;

import java.util.Collection;

import com.backend.springboot.domain.OblikLeka;
import com.backend.springboot.domain.RezervacijaLeka;
import com.backend.springboot.domain.RezimIzdavanja;
import com.backend.springboot.domain.VrstaLeka;

public interface RezervacijaService {
	Collection<RezervacijaLeka> findAll();
	
	RezervacijaLeka addRezervacija(RezervacijaLeka rezervacija);
	
	RezervacijaLeka findOne(String id);
	
	void delete(String id);
	
	Collection<RezervacijaLeka> findWithParams(int sifraLeka,String naziv,OblikLeka oblik,VrstaLeka vrsta,RezimIzdavanja rezim,String sifraApoteke);
}
