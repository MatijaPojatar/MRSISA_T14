package com.backend.springboot.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.backend.springboot.domain.Osoba;
import com.backend.springboot.domain.Pol;


@Repository
public class InMemoryOsobaRepository implements OsobaRepository{
	
	private final ConcurrentHashMap<Integer, Osoba> osobe = new ConcurrentHashMap<Integer, Osoba>();

	@Override
	public Collection<Osoba> findAll() {
		Osoba o1 = new Osoba(1, "Pera", "Peric", "pera@gmail.com", "12341234", "Bulevar", "NS", 
				"Srbija", "063",Pol.MUSKI, LocalDate.now(), true); //admin sistema
		Osoba o2 = new Osoba(2, "Mika", "Mikic", "mika@gmail.com", "12341234", "Bulevar", "NS",
				"Srbija", "063" , Pol.MUSKI, LocalDate.now(), true); //pacijent
		
		osobe.put(o1.getId(), o1);
		osobe.put(o2.getId(), o2);
		
		return osobe.values();
	}

	@Override
	public Osoba addOsoba(Osoba osoba) {
		osobe.put(osoba.getId(), osoba);
		return osoba;
	}

	@Override
	public Osoba findOne(int id) {
		return osobe.get(id);
	}

	@Override
	public void delete(int id) {
		osobe.remove(id);
	}

	@Override
	public void promeniLozinku(int id, String novaLozinka) {
		Osoba osoba = findOne(id);
		osoba.setPassword(novaLozinka);
		return;
	}
	
}
