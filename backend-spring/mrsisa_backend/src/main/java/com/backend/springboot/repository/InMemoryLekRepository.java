package com.backend.springboot.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.OblikLeka;
import com.backend.springboot.domain.RezimIzdavanja;
import com.backend.springboot.domain.VrstaLeka;

/*
 * In memory Repository za dummy lekove
 */

@Repository
public class InMemoryLekRepository implements LekRepository {
	
	private final ConcurrentMap<Integer, Lek> lekovi = new ConcurrentHashMap<Integer, Lek>();

	@Override
	public Collection<Lek> findAll() {
		
		Lek lek1=new Lek(1,"Lek1", "dsadasda", "Proizvodjac1", "napomena",
				RezimIzdavanja.NA_RECEPT, OblikLeka.MAST, VrstaLeka.ANTIBIOTIK);
		
		Lek lek2=new Lek(2,"Lek2", "dsadaD223sddsaa", "Proizvodjac2", "napomena",
				RezimIzdavanja.NA_RECEPT, OblikLeka.GEL, VrstaLeka.ANTIBIOTIK);
		
		Lek lek3=new Lek(3,"Lek3", "dsadadasdasdsda", "Proizvodjac1", "napomena",
				RezimIzdavanja.BEZ_RECEPTA, OblikLeka.KAPSULA, VrstaLeka.ANTIBIOTIK);
		
		Lek lek4=new Lek(4,"Lek4", "dsadadsa2ewqsda", "Proizvodjac1", "napomena",
				RezimIzdavanja.NA_RECEPT, OblikLeka.PRASAK, VrstaLeka.ANTIMIKOTIK);
		
		Lek lek5=new Lek(5,"Lek5", "dsadasdFfaDASDda", "Proizvodjac3", "napomena",
				RezimIzdavanja.BEZ_RECEPTA, OblikLeka.TABLETA, VrstaLeka.ANTIMIKOTIK);
		
		lekovi.put(lek1.getId(), lek1);
		lekovi.put(lek2.getId(), lek2);
		lekovi.put(lek3.getId(), lek3);
		lekovi.put(lek4.getId(), lek4);
		lekovi.put(lek5.getId(), lek5);
		
		return lekovi.values();
	}

	@Override
	public Lek addLek(Lek lek) {
		lekovi.put(lek.getId(), lek);
		return lek;
	}

	@Override
	public Lek findOne(int sifra) {
		return lekovi.get(sifra);
	}

	@Override
	public void delete(int sifra) {
		lekovi.remove(sifra);
		
	}

}
