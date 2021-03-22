package com.backend.springboot.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.RezervacijaLeka;
import com.backend.springboot.repository.InMemoryRezervacijaRepository;

/*
 * Service za rad sa rezervacijama
 */

@Service
public class RezervacijaServiceImplementation implements RezervacijaService{

	@Autowired
	private InMemoryRezervacijaRepository rezRep;

	@Override
	public RezervacijaLeka addRezervacija(RezervacijaLeka rezervacija) {
		return rezRep.addRezervacija(rezervacija);
	}

	@Override
	public RezervacijaLeka findOne(String id) {
		return rezRep.findOne(id);
	}

	@Override
	public Collection<RezervacijaLeka> findAll() {
		return rezRep.findAll();
	}

	@Override
	public void delete(String id) {
		rezRep.delete(id);
		
	} 

}
