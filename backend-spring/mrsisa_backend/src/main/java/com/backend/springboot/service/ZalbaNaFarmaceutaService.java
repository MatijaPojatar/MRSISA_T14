package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.AdministratorSistema;
import com.backend.springboot.domain.ZalbaNaApoteku;
import com.backend.springboot.domain.ZalbaNaDermatologa;
import com.backend.springboot.domain.ZalbaNaFarmaceuta;
import com.backend.springboot.repository.ZalbaNaFarmaceutaRepository;

@Service
public class ZalbaNaFarmaceutaService {
	
	@Autowired
	private ZalbaNaFarmaceutaRepository zalbeRep; 
	
	public ZalbaNaFarmaceuta odgovoriNaZalbu(ZalbaNaFarmaceuta zalba, String odgovor, AdministratorSistema admin) {
		zalba.setOdgovor(odgovor);
		zalba.setObradjena(true);
		zalba.setAdministrator(admin);
		return zalbeRep.save(zalba);
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
	
	public List<ZalbaNaFarmaceuta> findAllObradjeneByAdmin(Integer id){
		return zalbeRep.findByObradjenaTrueAndAdministrator(id);
	}
	
	public List<ZalbaNaFarmaceuta> findAllByPacijent(Integer id){
		return zalbeRep.findByPacijent(id);
	}
}
