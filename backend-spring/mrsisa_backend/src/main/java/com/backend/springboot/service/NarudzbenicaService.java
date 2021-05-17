package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Narudzbenica;
import com.backend.springboot.domain.Ponuda;
import com.backend.springboot.repository.NarudzbenicaRepository;
import com.backend.springboot.repository.PonudaRepository;

@Service
public class NarudzbenicaService {
	
	@Autowired
	private NarudzbenicaRepository narudzbenicaRep;
	
	@Autowired
	private PonudaRepository ponudeRep;
	
	public Narudzbenica findOne(Integer id) {
		return narudzbenicaRep.findOneById(id);
	}
	
	public List<Ponuda> preuzmiPonude(Integer narudzbenicaId) {
		return ponudeRep.findAllByNarudzbenicaId(narudzbenicaId);
	}
}
