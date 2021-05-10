package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.ZalbaNaApoteku;
import com.backend.springboot.repository.ZalbaNaApotekuRepository;

@Service
public class ZalbaNaApotekuService {
	
	@Autowired
	private ZalbaNaApotekuRepository zalbeRep;
	
	
	public ZalbaNaApoteku odgovoriNaZalbu(ZalbaNaApoteku zalba, String odgovor) {
		zalba.setOdgovor(odgovor);
		zalba.setObradjena(true);
		return zalbeRep.save(zalba);
	}
	
	public ZalbaNaApoteku dodajZalbu(ZalbaNaApoteku zalba) {
		return zalbeRep.save(zalba);
	}
	
	public ZalbaNaApoteku findOne(Integer id) {
		return zalbeRep.findOneById(id);
	}
	
	public List<ZalbaNaApoteku> findAll(){
		return zalbeRep.findAll();
	}
	public List<ZalbaNaApoteku> findAllNeobradjene(){
		return zalbeRep.findByObradjenaFalse();
	}
	
}
