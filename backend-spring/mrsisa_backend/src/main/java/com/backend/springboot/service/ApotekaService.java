package com.backend.springboot.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.dto.ApotekaDTO;
import com.backend.springboot.repository.ApotekaRepository;

/*
 * Service za rad sa apotekama
 */

@Service
public class ApotekaService  {

	@Autowired
	private ApotekaRepository apotekaRep;
	
	public List<Apoteka> findAll() {
		return apotekaRep.findAll();
	}

	
	public Apoteka findOne(Integer sifra) {
		return apotekaRep.findOneById(sifra);
	}
	
	public Apoteka save(Apoteka a) {
		return apotekaRep.save(a);
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





	public ApotekaDTO addApoteka(ApotekaDTO dto) throws Exception {
		Apoteka check = apotekaRep.findByNazivIgnoringCase(dto.getNaziv());
		System.out.println(dto.getAdresa() + "ADRSEA U SERVISU");
		if(check != null) {
			throw new Exception();
		}
		
		Apoteka apoteka = new Apoteka(dto);
		System.out.println(apoteka.getAdresa() + "ADRSEA OD KRAJNJE");
		
		return new ApotekaDTO(apotekaRep.save(apoteka));
	}
	
		
}
