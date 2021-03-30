package com.backend.springboot.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.repository.InMemoryApotekaRepository;

/*
 * Service za rad sa apotekama
 */

@Service
public class ApotekaServiceImplementation implements ApotekaService {

	@Autowired
	private InMemoryApotekaRepository apotekaRep;
	
	@Override
	public Collection<Apoteka> findAll() {
		return apotekaRep.findAll();
	}

	@Override
	public Apoteka addApoteka(Apoteka apoteka) {
		return apotekaRep.addApoteka(apoteka);
	}

	@Override
	public Apoteka findOne(String sifra) {
		return apotekaRep.findOne(sifra);
	}

	@Override
	public void delete(String sifra) {
		apotekaRep.delete(sifra);
	}

	@Override
	public Collection<Lek> preuzmiLekoveApoteke(String sifra) {
		Apoteka a = apotekaRep.findOne(sifra);
		
		return a.getLekovi();
		
	}

}
