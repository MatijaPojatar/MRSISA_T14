package com.backend.springboot.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.OblikLeka;
import com.backend.springboot.domain.RezimIzdavanja;
import com.backend.springboot.domain.VrstaLeka;

/*
 * In memory Repository za dummy apoteke
 */

@Repository
public class InMemoryApotekaRepository implements ApotekaRepository {
	
	private final ConcurrentMap<String, Apoteka> apoteke = new ConcurrentHashMap<String, Apoteka>();

	@Override
	public Collection<Apoteka> findAll() {
		Apoteka ap1=new Apoteka(1,"Apoteka1","ap1","adresa1","grad1","drazava1","opis");
		Apoteka ap2=new Apoteka(2,"Apoteka2","ap2","adresa2","grad2","drazava2","opis");
		
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
		
		ArrayList<Lek> lekovi1 = new ArrayList<Lek>();
		lekovi1.add(lek1);
		lekovi1.add(lek2);
		lekovi1.add(lek3);
		ArrayList<Lek> lekovi2 = new ArrayList<Lek>();
		lekovi2.add(lek3);
		lekovi2.add(lek4);
		lekovi2.add(lek5);
		
		ap1.setLekovi(lekovi1);
		ap2.setLekovi(lekovi2);
		
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
