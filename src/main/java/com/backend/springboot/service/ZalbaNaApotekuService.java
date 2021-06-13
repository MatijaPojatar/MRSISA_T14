package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.AdministratorSistema;
import com.backend.springboot.domain.ZalbaNaApoteku;
import com.backend.springboot.repository.ZalbaNaApotekuRepository;

@Service
public class ZalbaNaApotekuService {
	
	@Autowired
	private ZalbaNaApotekuRepository zalbeRep;
	
	
	public ZalbaNaApoteku odgovoriNaZalbu(ZalbaNaApoteku zalba, String odgovor, AdministratorSistema admin) {
		zalba.setOdgovor(odgovor);
		zalba.setObradjena(true);
		zalba.setAdministrator(admin);
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
	
	public List<ZalbaNaApoteku> findAllObradjeneByAdmin(Integer id){
		return zalbeRep.findByObradjenaTrueAndAdministratorId(id);
	}
	
	public List<ZalbaNaApoteku> findAllByPacijent(Integer id){
		return zalbeRep.findAllByPacijentId(id);
	}
}
