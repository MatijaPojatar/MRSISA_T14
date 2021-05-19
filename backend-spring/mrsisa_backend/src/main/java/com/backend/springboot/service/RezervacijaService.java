package com.backend.springboot.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.RezervacijaLeka;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.repository.RezervacijaRepository;

@Service
public class RezervacijaService {

	@Autowired
	private RezervacijaRepository rep;
	
	public RezervacijaLeka findOneActiveByCodeAndApoteka(String code,Integer apotekaId) {
		return rep.findOneActiveByCodeAndApoteka(code,apotekaId);
	}
	
	public RezervacijaLeka findOneActiveByCode(String code) {
		return rep.findOneActiveByCode(code);
	}
	
	public Set<Apoteka> poseceneApoteke(Integer pacijentId) {
		Set<Apoteka> apoteke = new HashSet<Apoteka>();
		List<RezervacijaLeka> rezervacije = rep.findAllByPacijentId(pacijentId);
		
		for (RezervacijaLeka r : rezervacije) {
			apoteke.add(r.getApoteka());
		}
		return apoteke;
	}
	
	
	public void save(RezervacijaLeka rl) {
		rep.save(rl);
	}
	
	public List<RezervacijaLeka> findAllActive(){
		return rep.findAllActive();
	}
}
