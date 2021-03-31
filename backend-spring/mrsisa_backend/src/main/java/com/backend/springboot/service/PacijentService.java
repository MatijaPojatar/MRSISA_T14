package com.backend.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.repository.PacijentRepository;

@Service
public class PacijentService {
	
	@Autowired
	PacijentRepository pacijentRep;
	
	public Pacijent registrujPacijenta(Pacijent pacijentInfo) {
		return pacijentRep.registrujPacijenta(pacijentInfo);
	}
}
