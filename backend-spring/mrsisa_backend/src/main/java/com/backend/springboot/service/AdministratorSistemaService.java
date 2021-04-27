package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.AdministratorApoteke;
import com.backend.springboot.domain.AdministratorSistema;
import com.backend.springboot.repository.AdministratorSistemaRepository;

@Service
public class AdministratorSistemaService {
	@Autowired
	private AdministratorSistemaRepository adminRep;
	
	public List<AdministratorSistema> findAll(){
		return adminRep.findAll();
	}
	
	public AdministratorSistema save(AdministratorSistema d) {
		return adminRep.save(d);
	}
	
	public AdministratorSistema findOne(Integer id) {
		return adminRep.findOneById(id);
	}

}
