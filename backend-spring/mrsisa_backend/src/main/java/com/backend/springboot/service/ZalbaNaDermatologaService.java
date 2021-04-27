package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.ZalbaNaApoteku;
import com.backend.springboot.domain.ZalbaNaDermatologa;
import com.backend.springboot.repository.ZalbaNaDermatologaRepository;

@Service
public class ZalbaNaDermatologaService {
	
	@Autowired
	private ZalbaNaDermatologaRepository zalbeRep;
	
	public void odgovoriNaZalbu(ZalbaNaDermatologa zalba, String odgovor) {
		zalba.setOdgovor(odgovor);
		zalba.setObradjena(true);
		zalbeRep.save(zalba);
		return;
	}
	
	public ZalbaNaDermatologa dodajZalbu(ZalbaNaDermatologa zalba) {
		return zalbeRep.save(zalba);
	}
	
	public ZalbaNaDermatologa findOne(Integer id) {
		return zalbeRep.findOneById(id);
	}
	
	public List<ZalbaNaDermatologa> findAll(){
		return zalbeRep.findAll();
	}
	
	public List<ZalbaNaDermatologa> findAllNeobradjene(){
		return zalbeRep.findByObradjenaFalse();
	}
}
