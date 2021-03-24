package com.backend.springboot.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Osoba;
import com.backend.springboot.repository.InMemoryOsobaRepository;

@Service
public class OsobaServiceImplementation implements OsobaService {

	@Autowired
	private InMemoryOsobaRepository osobaRep;

	@Override
	public Collection<Osoba> findAll() {
		return osobaRep.findAll();
	}

	@Override
	public Osoba addOsoba(Osoba osoba) {
		return osobaRep.addOsoba(osoba);
	}

	@Override
	public Osoba findOne(Integer id) {
		return osobaRep.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		osobaRep.delete(id);
	}

	@Override
	public Boolean promeniLozinku(int id, String staraLozinka, String novaLozinka) {
		Osoba o = findOne(id);
		String trenutnaLozinka = o.getPassword();
		if (trenutnaLozinka.equals(staraLozinka)) {
			o.setPassword(novaLozinka);
			return true;
		}
		return false;
	}

}
