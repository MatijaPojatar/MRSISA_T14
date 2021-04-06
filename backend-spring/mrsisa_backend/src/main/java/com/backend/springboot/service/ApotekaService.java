package com.backend.springboot.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.repository.ApotekaRepository;

/*
 * Service za rad sa apotekama
 */

@Service
public class ApotekaService  {

	@Autowired
	private ApotekaRepository apotekaRep;
	
	
	
	
	public Collection<Apoteka> findAll() {
		return apotekaRep.findAll();
	}

	
	

	
	public Apoteka findOne(Integer sifra) {
		return apotekaRep.findOneById(sifra);
	}

	
	

	public Collection<Lek> preuzmiLekoveApoteke(String sifra) {
		Apoteka a = apotekaRep.findOneById(Integer.parseInt(sifra));
		
		return a.getLekovi();
		
	}

	public Collection<Apoteka> pronadjiApoteke(String naziv, String grad) {
		ArrayList<Apoteka> pronadjene = new ArrayList<Apoteka>();
		Collection<Apoteka> sve = findAll();
		
		if (naziv.equals("") && grad.equals("")) {
			pronadjene = (ArrayList<Apoteka>) sve;
		}
	    else if (naziv.equals("")) {
			for (Apoteka apoteka : sve) {
				if (apoteka.getGrad().equals(grad)) {
					pronadjene.add(apoteka);
				}
			}
		}
	    else if (grad.equals("")) {
			for (Apoteka apoteka : sve) {
				if (apoteka.getNaziv().equals(naziv)) {
					pronadjene.add(apoteka);
				}
			}
		}
	    else {
	    	for (Apoteka apoteka : sve) {
				if (apoteka.getNaziv().equals(naziv) && apoteka.getGrad().equals(grad)) {
					pronadjene.add(apoteka);
				}
			}
	    }

		return pronadjene;
	}
}
