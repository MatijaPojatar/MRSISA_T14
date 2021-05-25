package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.Pregled;
import com.backend.springboot.repository.PregledRepository;

@Transactional
@Service
public class PregledService {
	
	@Autowired
	private PregledRepository pregledRep;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void deletePregled(int id) {
		pregledRep.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public List<Pregled> findAllByPacijentId(Integer pacijentId) {
		return pregledRep.findAllByPacijentId(pacijentId);
	}
	
	@Transactional(readOnly=true)
	public Set<Dermatolog> poseceniDermatolozi(Integer pacijentId){
	Set<Dermatolog> dermatolozi = new HashSet<Dermatolog>();
	List<Pregled> pregledi = pregledRep.findAllByPacijentId(pacijentId);
	
	for (Pregled p : pregledi) {
		dermatolozi.add(p.getDermatolog());
	}
	return dermatolozi;
}
	
	//daj apoteke gde je bio pacijent na pregledu
	@Transactional(readOnly=true)
	public Set<Apoteka> poseceneApoteke(Integer pacijentId) {
		Set<Apoteka> apoteke = new HashSet<Apoteka>();
		List<Pregled> pregledi = pregledRep.findAllByPacijentId(pacijentId);
		
		for (Pregled s : pregledi) {
			apoteke.add(s.getApoteka());
		}
		return apoteke;
	}
	
	@Transactional(readOnly=true)
	public List<Pregled> findAllByDermatologId(Integer dermatologId){
		return pregledRep.findAllByDermatologId(dermatologId);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public Pregled save(Pregled p) {
		return pregledRep.save(p);
	}
	
	@Transactional(readOnly=true)
	public Pregled findOne(Integer id) {
		return pregledRep.findOneById(id);
	}
	
	@Transactional(readOnly=true)
	public List<Pregled> findAllActive(LocalDateTime pocetak){
		return pregledRep.findAllByPocetakGreaterThanEqual(pocetak);
	}
	
	@Transactional(readOnly=true)
	public List<Pregled> findAllActive(Integer dermatologId,Integer apotekaId,LocalDateTime pocetak){
		return pregledRep.findAllByDermatologIdAndPacijentIdAndApotekaIdAndPocetakGreaterThanEqual(dermatologId, null, apotekaId, pocetak);
	}
	
	public List<Pregled> findAllInRangeForDermatolog(Integer id,LocalDateTime start,LocalDateTime end){
		return pregledRep.findInRangeForDermatolog(id,start, end);
	}
	
	@Transactional(readOnly=true)
	public List<Pregled> findAllInRangeForPacijent(Integer id,LocalDateTime start,LocalDateTime end){
		return pregledRep.findInRangeForPacijent(id,start, end);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void zauzmiPregled(Integer id,Pacijent p) {
		Pregled preg=findOne(id);
		if(preg!=null) {
			preg.setPacijent(p);
			pregledRep.save(preg);
		}
	}

}
