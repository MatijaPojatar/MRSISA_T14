package com.backend.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Narudzbenica;
import com.backend.springboot.repository.NarudzbenicaRepository;

@Service
public class NarudzbenicaService {
	
	@Autowired
	private NarudzbenicaRepository repo;
	
	public Narudzbenica findOne(Integer id) {
		return repo.findOneById(id);
	}
}
