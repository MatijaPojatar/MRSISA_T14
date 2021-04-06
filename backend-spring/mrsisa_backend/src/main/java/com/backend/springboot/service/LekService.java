package com.backend.springboot.service;

import java.util.Collection;

import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.OblikLeka;
import com.backend.springboot.domain.RezervacijaLeka;
import com.backend.springboot.domain.RezimIzdavanja;
import com.backend.springboot.domain.VrstaLeka;

public interface LekService {
	
	Collection<Lek> findAll();
	
	Lek addLek(Lek lek);
	
	Lek findOneById(Integer id);
	
	
	Collection<Lek> findWithParams(int sifraLeka,String naziv,OblikLeka oblik,VrstaLeka vrsta,RezimIzdavanja rezim,int sifraApoteke, String proizvodjac);
}
