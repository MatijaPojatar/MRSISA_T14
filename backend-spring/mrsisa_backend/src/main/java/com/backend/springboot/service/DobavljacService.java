package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Dobavljac;
import com.backend.springboot.dto.DobavljacDTO;
import com.backend.springboot.repository.DobavljacRepository;

@Service
public class DobavljacService {
	
	@Autowired
	private DobavljacRepository dobavljacRep;
	
	public List<Dobavljac> findAll(){
		return dobavljacRep.findAll();
	}
	
	public Dobavljac findOne(Integer id) {
		return dobavljacRep.findOneById(id);
	}

	public Dobavljac save(Dobavljac d) {
		return dobavljacRep.save(d);
	}
}
