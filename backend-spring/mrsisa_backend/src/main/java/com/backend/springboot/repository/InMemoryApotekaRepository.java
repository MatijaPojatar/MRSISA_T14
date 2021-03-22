package com.backend.springboot.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import com.backend.springboot.domain.Apoteka;

/*
 * In memory Repository za dummy apoteke
 */

@Repository
public class InMemoryApotekaRepository implements ApotekaRepository {
	
	private final ConcurrentMap<String, Apoteka> apoteke = new ConcurrentHashMap<String, Apoteka>();

	@Override
	public Collection<Apoteka> findAll() {
		Apoteka ap1=new Apoteka("Apoteka1","ap1","adresa1","grad1","drazava1","opis");
		Apoteka ap2=new Apoteka("Apoteka2","ap2","adresa2","grad2","drazava2","opis");
		
		apoteke.put(ap1.getSifra(), ap1);
		apoteke.put(ap2.getSifra(), ap2);
		
		return apoteke.values();
	}

	@Override
	public Apoteka addApoteka(Apoteka apoteka) {
		
		apoteke.put(apoteka.getSifra(), apoteka);
		
		return apoteka;
	}

	@Override
	public Apoteka findOne(String sifra) {
		return apoteke.get(sifra);
	}

	@Override
	public void delete(String sifra) {
		apoteke.remove(sifra);
		
	}

}
