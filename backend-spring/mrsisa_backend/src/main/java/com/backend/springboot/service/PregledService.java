package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.Pregled;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.repository.PregledRepository;

@Service
public class PregledService {
	
	@Autowired
	private PregledRepository pregledRep;
	
	public List<Pregled> findAllByPacijentId(Integer pacijentId) {
		return pregledRep.findAllByPacijentId(pacijentId);
	}
	
	public Set<Dermatolog> poseceniDermatolozi(Integer pacijentId){
	Set<Dermatolog> dermatolozi = new HashSet<Dermatolog>();
	List<Pregled> pregledi = pregledRep.findAllByPacijentId(pacijentId);
	
	for (Pregled p : pregledi) {
		dermatolozi.add(p.getDermatolog());
	}
	return dermatolozi;
}
	
	//daj apoteke gde je bio pacijent na pregledu
	public Set<Apoteka> poseceneApoteke(Integer pacijentId) {
		Set<Apoteka> apoteke = new HashSet<Apoteka>();
		List<Pregled> pregledi = pregledRep.findAllByPacijentId(pacijentId);
		
		for (Pregled s : pregledi) {
			apoteke.add(s.getApoteka());
		}
		return apoteke;
	}
		
	public List<Pregled> findAllByDermatologId(Integer dermatologId){
		return pregledRep.findAllByDermatologId(dermatologId);
	}
	
	public Pregled save(Pregled p) {
		return pregledRep.save(p);
	}
	
	public Pregled findOne(Integer id) {
		return pregledRep.findOneById(id);
	}
	
	public List<Pregled> findAllActive(Integer dermatologId,Integer apotekaId,LocalDateTime pocetak){
		return pregledRep.findAllByDermatologIdAndPacijentIdAndApotekaIdAndPocetakGreaterThanEqual(dermatologId, null, apotekaId, pocetak);
	}
	
	public List<Pregled> findAllInRangeForDermatolog(Integer id,LocalDateTime start,LocalDateTime end){
		return pregledRep.findInRangeForDermatolog(id,start, end);
	}
	
	public List<Pregled> findAllInRangeForPacijent(Integer id,LocalDateTime start,LocalDateTime end){
		return pregledRep.findInRangeForPacijent(id,start, end);
	}

}
