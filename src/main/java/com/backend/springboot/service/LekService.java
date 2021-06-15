package com.backend.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.LekUMagacinu;
import com.backend.springboot.repository.ApotekaRepository;
import com.backend.springboot.repository.LekRepository;
import com.backend.springboot.repository.LekUMagacinuRepository;
import com.backend.springboot.repository.MagacinRepository;

/*
 * Service za rad sa lekovima
 */

@Service
public class LekService {
	
	@Autowired
	private LekRepository lekRep;
	@Autowired
	private ApotekaRepository apotekaRep;
	@Autowired
	private MagacinRepository magacinRep;
	@Autowired
	private LekUMagacinuRepository lekUMagacinuRep;
	
	

	public List<Lek> findAll() {
		return lekRep.findAll();
	}

	public Lek addLek(Lek lek) {
		return lekRep.save(lek);
	}

	public Lek findOneById(Integer sifra) {
		return lekRep.findOneById(sifra);
	}
	
	public Lek findOneByNaziv(String naziv) {
		return lekRep.findOneByNaziv(naziv);
	}
	
	public void deleteLek(int id) {
		lekRep.deleteById(id);
	}
	
	public List<Lek> findAllByApoteka(Integer id) {
		//funkcija vraca i obrisane lekove, za preuzimanje aktuelnih lekova, koristiti funkciju iz magacin servisa
		return lekRep.findAllBySnabdeveneApoteke_Id(id);
	}

	public void addZamenskeZaLek(int id, List<Integer> zamenskiIds) {
		Lek lek = lekRep.findOneById(id);
		
		for (Integer i : zamenskiIds) {
			Lek zamenski = lekRep.findOneById(i);
			
			lek.addZamenskiLek(zamenski);
		}
		
		lekRep.save(lek);
	}
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public List<Lek> preuzmiLekoveVanApoteke(Integer apotekaId){
		ArrayList<Lek> lekovi = (ArrayList<Lek>) lekRep.findAll();
		ArrayList<LekUMagacinu> lekoviApoteke = (ArrayList<LekUMagacinu>) lekUMagacinuRep.findAllByMagacinId(magacinRep.findOneByApotekaId(apotekaId).getId());
		
		boolean dodavanje = true;
		for (LekUMagacinu l: lekoviApoteke) {
			lekovi.remove(l.getLek());
		}
		return lekovi;
	}
	
	public List<Lek> findZamenski(Integer id){
		return lekRep.findZamenski(id);
	}

	public Lek updateLek(Lek lek) {
		return lekRep.save(lek);
	}

	public List<Lek> getZamenskeZaLek(Integer id) {
		Lek lek = lekRep.findOneById(id);
		return lek.getZamenskiLekovi();
	}
}
