package com.backend.springboot.repository;

import java.util.ArrayList;
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
 * In memory Repository za dummy apoteke
 */

@Repository
public class InMemoryApotekaRepository implements ApotekaRepository {
	
	private final ConcurrentMap<String, Apoteka> apoteke = new ConcurrentHashMap<String, Apoteka>();

	@Override
	public List<Apoteka> findAll() {
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
		
		return (List<Apoteka>) apoteke.values();
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

	@Override
	public List<Apoteka> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Apoteka> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Apoteka> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Apoteka> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Apoteka> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Apoteka getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Apoteka> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Apoteka> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Apoteka> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Apoteka> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Apoteka> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Apoteka entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Apoteka> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Apoteka> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Apoteka> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Apoteka> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Apoteka> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

}
