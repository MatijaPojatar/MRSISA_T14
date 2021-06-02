package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Ponuda;
import com.backend.springboot.repository.PonudaRepository;

@Service
public class PonudaService {
	
	@Autowired
	private PonudaRepository ponudaRep;
	
	
	public List<Ponuda> findAll() {
		return ponudaRep.findAll();
	}
	
	public Ponuda findOne(Integer id) {
		return ponudaRep.findOneById(id);
	}
	
	public Ponuda dodajPonudu(Ponuda p) {
		return ponudaRep.save(p);
	}
	
	public Ponuda izmeniPonudu(Ponuda p) {
		return ponudaRep.save(p);
	}
	
	public List<Ponuda> findAllByDobavljacId(Integer dobavljacId) {
		return ponudaRep.findAllByDobavljacId(dobavljacId);
	}
	
	public List<Ponuda> findAllByNarudzbenicaId(Integer narudzbenicaId){
		return ponudaRep.findAllByNarudzbenicaId(narudzbenicaId);
	}
}
