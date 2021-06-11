package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.AdministratorApoteke;
import com.backend.springboot.repository.AdministratorApotekeRepository;

@Service
public class AdministratorApotekeService {
	@Autowired
	private AdministratorApotekeRepository adminRep;
	
	public List<AdministratorApoteke> findAll(){
		return adminRep.findAll();
	}
	
	public AdministratorApoteke save(AdministratorApoteke d) {
		return adminRep.save(d);
	}
	
	public AdministratorApoteke findOne(Integer id) {
		return adminRep.findOneById(id);
	}

	public AdministratorApoteke findByMail(String mail) {
		return adminRep.findOneByMail(mail);
	}
}
