package com.backend.springboot.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.OblikLeka;
import com.backend.springboot.domain.RezimIzdavanja;
import com.backend.springboot.domain.VrstaLeka;

/*
 * In memory Repository za dummy lekove
 */

@Repository
public class InMemoryLekRepository {
	
	private final ConcurrentMap<Integer, Lek> lekovi = new ConcurrentHashMap<Integer, Lek>();

	 
	public List<Lek> findAll() {
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
		lek1.getSnabdeveneApoteke().add(ap1);
		lek1.getSnabdeveneApoteke().add(ap2);
		lek2.getSnabdeveneApoteke().add(ap1);
		
		lek3.getSnabdeveneApoteke().add(ap1);
		lek4.getSnabdeveneApoteke().add(ap1);
		lek5.getSnabdeveneApoteke().add(ap1);
		
		lek3.getSnabdeveneApoteke().add(ap2);
		lek4.getSnabdeveneApoteke().add(ap2);
		lek5.getSnabdeveneApoteke().add(ap2);
		
		lekovi.put(lek1.getId(), lek1);
		lekovi.put(lek2.getId(), lek2);
		lekovi.put(lek3.getId(), lek3);
		lekovi.put(lek4.getId(), lek4);
		lekovi.put(lek5.getId(), lek5);
		
		return (List<Lek>) lekovi.values();
	}

	 
	public Lek addLek(Lek lek) {
		lekovi.put(lek.getId(), lek);
		return lek;
	}

	 
	public Lek findOne(int sifra) {
		return lekovi.get(sifra);
	}

	 
	public void delete(int sifra) {
		lekovi.remove(sifra);
		
	}

	 
	public List<Lek> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public List<Lek> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public <S extends Lek> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	 
	public <S extends Lek> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public void deleteInBatch(Iterable<Lek> entities) {
		// TODO Auto-generated method stub
		
	}

	 
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	 
	public Lek getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public <S extends Lek> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public <S extends Lek> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public Page<Lek> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public <S extends Lek> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public Optional<Lek> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	 
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	 
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	 
	public void delete(Lek entity) {
		// TODO Auto-generated method stub
		
	}

	 
	public void deleteAll(Iterable<? extends Lek> entities) {
		// TODO Auto-generated method stub
		
	}

	 
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	 
	public <S extends Lek> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public <S extends Lek> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public <S extends Lek> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	 
	public <S extends Lek> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	 
	public List<Lek> findAllByLekId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
