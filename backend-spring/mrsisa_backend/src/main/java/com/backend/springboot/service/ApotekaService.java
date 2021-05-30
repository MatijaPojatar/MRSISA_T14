package com.backend.springboot.service;

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
	
	public List<Apoteka> pretraga(String naziv, String grad){
		if (!naziv.equals("") && !grad.equals("")) {
			return apotekaRep.findByNazivIgnoringCaseAndGradIgnoringCase(naziv, grad);
		} else if (!naziv.equals("") && grad.equals("")) {
			return apotekaRep.findByNazivIgnoringCase(naziv);
		} else if (naziv.equals("") && !grad.equals("")) {
			return apotekaRep.findByGradIgnoringCase(grad);
		} else {
			return apotekaRep.findAll();
		}
	}
	
	public Apoteka save(Apoteka a) {
		return apotekaRep.save(a);
	}

	public Collection<Lek> preuzmiLekoveApoteke(String sifra) {
		Apoteka a = apotekaRep.findOneById(Integer.parseInt(sifra));
		
		return a.getLekovi();
		
	}
	
	public ApotekaDTO addApoteka(ApotekaDTO dto) throws Exception {
		List<Apoteka> check = apotekaRep.findByNazivIgnoringCase(dto.getNaziv());
		System.out.println(dto.getAdresa() + "ADRSEA U SERVISU");
		if(!check.isEmpty()) {
			throw new Exception();
		}
		
		Apoteka apoteka = new Apoteka(dto);
		System.out.println(apoteka.getAdresa() + "ADRSEA OD KRAJNJE");
		
		return new ApotekaDTO(apotekaRep.save(apoteka));
	}
}
