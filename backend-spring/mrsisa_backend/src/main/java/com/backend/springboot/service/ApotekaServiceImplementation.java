package com.backend.springboot.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.repository.InMemoryApotekaRepository;

/*
 * Service za rad sa apotekama
 */

@Service
public class ApotekaServiceImplementation implements ApotekaService {

	@Autowired
	private InMemoryApotekaRepository apotekaRep;
	
	@Override
	public Collection<Apoteka> findAll() {
		return apotekaRep.findAll();
	}

	@Override
	public Apoteka addApoteka(Apoteka apoteka) {
		return apotekaRep.addApoteka(apoteka);
	}

	@Override
	public Apoteka findOne(String sifra) {
		return apotekaRep.findOne(sifra);
	}

	@Override
	public void delete(String sifra) {
		apotekaRep.delete(sifra);
	}

	@Override
	public Collection<Lek> preuzmiLekoveApoteke(String sifra) {
		Apoteka a = apotekaRep.findOne(sifra);
		
		return a.getLekovi();
		
	}

	@Override
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
