package com.backend.springboot.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Pacijent;

@Repository
public class InMemoryPacijentRepository implements PacijentRepository{

	private final ConcurrentMap<Integer, Pacijent> pacijenti = new ConcurrentHashMap<Integer, Pacijent>();
	
	@Override
	public Pacijent registrujPacijenta(Pacijent pacijent) {
		pacijenti.put(pacijent.getId(), pacijent);
		return null;
	}

}
