package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.repository.FarmaceutRepository;

@Service
public class FarmaceutService {
	
	@Autowired
	private FarmaceutRepository farmaceutRep;
	
	public List<Farmaceut> findAll(){
		return farmaceutRep.findAll();
	}
	
	public Farmaceut save(Farmaceut f) {
		return farmaceutRep.save(f);
	}

}
