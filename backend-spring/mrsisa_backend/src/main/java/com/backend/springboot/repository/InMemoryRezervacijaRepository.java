package com.backend.springboot.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.OblikLeka;
import com.backend.springboot.domain.RezervacijaLeka;
import com.backend.springboot.domain.RezimIzdavanja;
import com.backend.springboot.domain.VrstaLeka;

/*
 * In memory Repository za dummy rezervacije
 */

@Repository
public class InMemoryRezervacijaRepository implements RezervacijaRepository {

	private static AtomicLong counter = new AtomicLong();
	private final ConcurrentMap<String, RezervacijaLeka> rezervacije = new ConcurrentHashMap<String, RezervacijaLeka>();
	
	@Override
	public Collection<RezervacijaLeka> findAll() {
			
		return this.rezervacije.values();
	}

	@Override
	public RezervacijaLeka addRezervacija(RezervacijaLeka rezervacija) {
		String id=rezervacija.getId();
		
		if(id==null) {
			id=Long.toString(counter.getAndIncrement());
			rezervacija.setId(id);
		}
		
		rezervacije.put(id, rezervacija);
		
		return rezervacija;
	}

	@Override
	public RezervacijaLeka findOne(String id) {
		return rezervacije.get(id);
	}

	@Override
	public void delete(String id) {
		rezervacije.remove(id);

	}

}
