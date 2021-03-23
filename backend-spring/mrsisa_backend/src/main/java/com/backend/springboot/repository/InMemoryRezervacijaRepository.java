package com.backend.springboot.repository;

import java.sql.Date;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.backend.springboot.domain.Apoteka;
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
	private static ConcurrentMap<String, RezervacijaLeka> rezervacije = new ConcurrentHashMap<String, RezervacijaLeka>();
	
	static {
	    rezervacije.put("rez1", new RezervacijaLeka("rez1",new Date(0),new Lek("1","Lek1", "dsadasda", "Proizvodjac1", "napomena",
				RezimIzdavanja.NA_RECEPT, OblikLeka.MAST, VrstaLeka.ANTIBIOTIK),"ap1"));
	    rezervacije.put("rez2", new RezervacijaLeka("rez2",new Date(0),new Lek("2","Lek2", "dsadaD223sddsaa", "Proizvodjac2", "napomena",
				RezimIzdavanja.NA_RECEPT, OblikLeka.GEL, VrstaLeka.ANTIBIOTIK),"ap1"));
	    rezervacije.put("rez3", new RezervacijaLeka("rez3",new Date(0),new Lek("3","Lek3", "dsadadasdasdsda", "Proizvodjac1", "napomena",
				RezimIzdavanja.BEZ_RECEPTA, OblikLeka.KAPSULA, VrstaLeka.ANTIBIOTIK),"ap1"));
	    rezervacije.put("rez4", new RezervacijaLeka("rez4",new Date(0),new Lek("2","Lek2", "dsadaD223sddsaa", "Proizvodjac2", "napomena",
				RezimIzdavanja.NA_RECEPT, OblikLeka.GEL, VrstaLeka.ANTIBIOTIK),"ap1"));
	    rezervacije.put("rez5", new RezervacijaLeka("rez5",new Date(0),new Lek("4","Lek4", "dsadadsa2ewqsda", "Proizvodjac1", "napomena",
				RezimIzdavanja.NA_RECEPT, OblikLeka.PRASAK, VrstaLeka.ANTIMIKOTIK),"ap1"));
	    rezervacije.put("rez6", new RezervacijaLeka("rez6",new Date(0),new Lek("1","Lek1", "dsadasda", "Proizvodjac1", "napomena",
				RezimIzdavanja.NA_RECEPT, OblikLeka.MAST, VrstaLeka.ANTIBIOTIK),"ap1"));
	    rezervacije.put("rez7", new RezervacijaLeka("rez7",new Date(0),new Lek("5","Lek5", "dsadasdFfaDASDda", "Proizvodjac3", "napomena",
				RezimIzdavanja.BEZ_RECEPTA, OblikLeka.TABLETA, VrstaLeka.ANTIMIKOTIK),"ap1"));
	    rezervacije.put("rez8", new RezervacijaLeka("rez8",new Date(0),new Lek("3","Lek3", "dsadadasdasdsda", "Proizvodjac1", "napomena",
				RezimIzdavanja.BEZ_RECEPTA, OblikLeka.KAPSULA, VrstaLeka.ANTIBIOTIK),"ap1"));
	    rezervacije.put("rez9", new RezervacijaLeka("rez9",new Date(0),new Lek("1","Lek1", "dsadasda", "Proizvodjac1", "napomena",
				RezimIzdavanja.NA_RECEPT, OblikLeka.MAST, VrstaLeka.ANTIBIOTIK),"ap2"));
	    rezervacije.put("rez10", new RezervacijaLeka("rez10",new Date(0),new Lek("2","Lek2", "dsadaD223sddsaa", "Proizvodjac2", "napomena",
				RezimIzdavanja.NA_RECEPT, OblikLeka.GEL, VrstaLeka.ANTIBIOTIK),"ap2"));
	  }
	
	@Override
	public Collection<RezervacijaLeka> findAll() {
			
		return rezervacije.values();
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
