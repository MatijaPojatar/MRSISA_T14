package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.repository.SavetovanjeRepository;

@Service
public class SavetovanjeService {
	
	@Autowired
	private SavetovanjeRepository savetovanjeRep;
	
	public List<Savetovanje> findAllByFarmaceutId(Integer farmaceutId){
		return savetovanjeRep.findAllByFarmaceutId(farmaceutId);
	}
	
	public Savetovanje save(Savetovanje s) {
		return savetovanjeRep.save(s);
	}
	
	public Savetovanje findOne(Integer id) {
		return savetovanjeRep.findOneById(id);
	}
	
	public List<Savetovanje> findAllActive(Integer farmaceutId,Integer apotekaId,LocalDateTime pocetak){
		return savetovanjeRep.findAllByFarmaceutIdAndPacijentIdAndApotekaIdAndPocetakGreaterThanEqual(farmaceutId, null, apotekaId, pocetak);
	}
	
	public List<Savetovanje> findAllInRange(LocalDateTime start,LocalDateTime end){
		return savetovanjeRep.findInRange(start, end);
	}

//	public Set<Integer> poseceneApoteke(Integer pacijentId) {
//		Set<Integer> apotekeIds = new HashSet<Integer>();
//		List<Savetovanje> savetovanja = savetovanjeRep.findAllByPacijentId(pacijentId);
//		
//		for (Savetovanje s : savetovanja) {
//			apotekeIds.add(s.getApoteka().getId());
//		}
//		return apotekeIds;
//	}
	
	public Set<Apoteka> poseceneApoteke(Integer pacijentId) {
		Set<Apoteka> apoteke = new HashSet<Apoteka>();
		List<Savetovanje> savetovanja = savetovanjeRep.findAllByPacijentId(pacijentId);
		
		for (Savetovanje s : savetovanja) {
			apoteke.add(s.getApoteka());
		}
		return apoteke;
	}
	
//	public Set<Integer> poseceniFarmaceuti(Integer pacijentId){
//		Set<Integer> farmaceutiIds = new HashSet<Integer>();
//		List<Savetovanje> savetovanja = savetovanjeRep.findAllByPacijentId(pacijentId);
//		
//		for (Savetovanje s : savetovanja) {
//			farmaceutiIds.add(s.getFarmaceut().getId());
//		}
//		
//		return farmaceutiIds;
//	}
	
	public Set<Farmaceut> poseceniFarmaceuti(Integer pacijentId){
		Set<Farmaceut> farmaceuti = new HashSet<Farmaceut>();
		List<Savetovanje> savetovanja = savetovanjeRep.findAllByPacijentId(pacijentId);
		
		for (Savetovanje s : savetovanja) {
			farmaceuti.add(s.getFarmaceut());
		}
		
		return farmaceuti;
	}

}
