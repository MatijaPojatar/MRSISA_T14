package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Pregled;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.repository.PregledRepository;

@Service
public class PregledService {
	
	@Autowired
	private PregledRepository pregledRep;
	
	public List<Pregled> findAllByDermatologId(Integer dermatologId){
		return pregledRep.findAllByDermatologId(dermatologId);
	}
	
	public Pregled save(Pregled p) {
		return pregledRep.save(p);
	}
	
	public Pregled findOne(Integer id) {
		return pregledRep.findOneById(id);
	}

}
