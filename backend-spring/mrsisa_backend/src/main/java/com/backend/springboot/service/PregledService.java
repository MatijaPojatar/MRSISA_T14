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
	
//	//daj dermatologe gde je bio pacijent
//	public Set<Integer> poseceniDermatolozi(Integer pacijentId){
//		Set<Integer> dermatoloziIds = new HashSet<Integer>();
//		List<Pregled> pregledi = pregledRep.findAllByPacijentId(pacijentId);
//		
//		for (Pregled p : pregledi) {
//			dermatoloziIds.add(p.getDermatolog().getId());
//		}
//		return dermatoloziIds;
//	}
	
	
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
	
	
	
//	//daj apoteke gde je bio pacijent na pregledu
//	public Set<Integer> poseceneApoteke(Integer pacijentId){
//		Set<Integer> apotekeIds = new HashSet<Integer>();
//		List<Pregled> pregledi = pregledRep.findAllByPacijentId(pacijentId);
//		
//		for (Pregled p : pregledi) {
//			apotekeIds.add(p.getApoteka().getId());
//		}
//		return apotekeIds;
//	}
	
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
	
	public List<Pregled> findAllInRange(LocalDateTime start,LocalDateTime end){
		return pregledRep.findInRange(start, end);
	}

}
