package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.repository.SavetovanjeRepository;

@Service
public class SavetovanjeService {
	
	@Autowired
	private SavetovanjeRepository savetovanjeRep;
	
	public List<Savetovanje> findAllByFarmaceutId(Integer farmaceutId){
		return savetovanjeRep.findAllByFarmaceutId(farmaceutId);
	}

}