package com.backend.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.repository.PacijentRepository;

@Service
public class PacijentService {
	
	@Autowired
	PacijentRepository pacijentRep;
	
	
	public Pacijent findOne(Integer id) {
		return pacijentRep.findOneById(id);
	}
	
	public Pacijent save(Pacijent p) {
		return pacijentRep.save(p);
	}

	public Pacijent findByMail(String mail) {
		return pacijentRep.findOneByMail(mail);
	}
	
	
}
