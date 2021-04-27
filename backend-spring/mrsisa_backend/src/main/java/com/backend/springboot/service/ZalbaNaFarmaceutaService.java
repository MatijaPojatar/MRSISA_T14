package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.ZalbaNaApoteku;
import com.backend.springboot.domain.ZalbaNaDermatologa;
import com.backend.springboot.domain.ZalbaNaFarmaceuta;
import com.backend.springboot.repository.ZalbaNaFarmaceutaRepository;

@Service
public class ZalbaNaFarmaceutaService {
	
	@Autowired
	private ZalbaNaFarmaceutaRepository zalbeRep; 
	
	public void odgovoriNaZalbu(ZalbaNaFarmaceuta zalba, String odgovor) {
		zalba.setOdgovor(odgovor);
		zalba.setObradjena(true);
		zalbeRep.save(zalba);
		return;
	}
	
	public ZalbaNaFarmaceuta dodajZalbu(ZalbaNaFarmaceuta zalba) {
		return zalbeRep.save(zalba);
	}
	
	public ZalbaNaFarmaceuta findOne(Integer id) {
		return zalbeRep.findOneById(id);
	}
	
	public List<ZalbaNaFarmaceuta> findAll(){
		return zalbeRep.findAll();
	}
	
	
	public List<ZalbaNaFarmaceuta> findAllNeobradjene(){
		return zalbeRep.findByObradjenaFalse();
	}
}
