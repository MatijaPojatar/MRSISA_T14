package com.backend.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.RezervacijaLeka;
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
	
	public void save(RezervacijaLeka rl) {
		rep.save(rl);
	}
}
