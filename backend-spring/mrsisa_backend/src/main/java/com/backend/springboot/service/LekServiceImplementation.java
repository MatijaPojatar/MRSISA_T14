package com.backend.springboot.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Lek;
import com.backend.springboot.repository.InMemoryLekRepository;

/*
 * Service za rad sa lekovima
 */

@Service
public class LekServiceImplementation implements LekService {
	
	@Autowired
	private InMemoryLekRepository lekRep;

	@Override
	public Collection<Lek> findAll() {
		return lekRep.findAll();
	}

	@Override
	public Lek addLek(Lek lek) {
		return lekRep.addLek(lek);
	}

	@Override
	public Lek findOne(String sifra) {
		return lekRep.findOne(sifra);
	}

	@Override
	public void delete(String sifra) {
		lekRep.delete(sifra);
	}

}
