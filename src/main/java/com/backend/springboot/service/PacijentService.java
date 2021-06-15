package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.StavkaBodovanja;
import com.backend.springboot.domain.TipStavkeBodovanja;
import com.backend.springboot.repository.ApotekaRepository;
import com.backend.springboot.repository.LekRepository;
import com.backend.springboot.repository.PacijentRepository;

@Service
public class PacijentService {
	
	@Autowired
	StavkaBodovanjaService SBservice;
	
	@Autowired
	PacijentRepository pacijentRep;
	@Autowired
	ApotekaRepository apotekaRep;
	@Autowired
	LekRepository lekRep;
	
	@Transactional
	public void dodajBodove(Integer idPac, TipStavkeBodovanja tip, Integer idLeka) {
		Pacijent pac = pacijentRep.findOneById(idPac);
		
		StavkaBodovanja s = SBservice.find(tip, idLeka);
		if(s ==null) {
			return;
		}
		pac.setBrojPoena(pac.getBrojPoena() + s.getBrojPoena());
		pacijentRep.save(pac);
		return;
	}
	
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public Pacijent findOne(Integer id) {
		return pacijentRep.findOneById(id);
	}
	
	public Pacijent save(Pacijent p) {
		return pacijentRep.save(p);
	}

	public Pacijent findByMail(String mail) {
		return pacijentRep.findOneByMail(mail);
	}
	
	public List<Pacijent> findAll(){
		return pacijentRep.findAll();
	}
	
	public List<Lek> findAllAlergijeById(Integer id){
		return pacijentRep.findAllAlergijeById(id);
	}
	
	public void pretplati(Integer pacijentId, Integer apotekaId) {
		Pacijent p=pacijentRep.getOne(pacijentId);
		Apoteka a = apotekaRep.getOne(apotekaId);
		
		p.getPretplaceneApoteke().add(a);
		pacijentRep.save(p);
	}
	
	public void setAlergije(Integer id, List<Lek> alergije) {
		Pacijent p = pacijentRep.getOne(id);
		
		p.setAlergije(alergije);
		pacijentRep.save(p);		
	}
}
