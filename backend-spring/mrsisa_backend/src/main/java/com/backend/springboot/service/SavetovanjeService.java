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
import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.repository.SavetovanjeRepository;

@Transactional
@Service
public class SavetovanjeService {
	
	@Autowired
	private SavetovanjeRepository savetovanjeRep;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void deleteSavetovanje(int id) {
		savetovanjeRep.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public List<Savetovanje> findAllByFarmaceutId(Integer farmaceutId){
		return savetovanjeRep.findAllByFarmaceutId(farmaceutId);
	}
	
	@Transactional(readOnly=true)
	public List<Savetovanje> findAllByPacijentId(Integer pacijentId){
		return savetovanjeRep.findAllByPacijentId(pacijentId);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public Savetovanje save(Savetovanje s) {
		return savetovanjeRep.save(s);
	}
	
	@Transactional(readOnly=true)
	public Savetovanje findOne(Integer id) {
		return savetovanjeRep.findOneById(id);
	}
	
	@Transactional(readOnly=true)
	public List<Savetovanje> findAllActive(Integer farmaceutId,Integer apotekaId,LocalDateTime pocetak){
		return savetovanjeRep.findAllByFarmaceutIdAndPacijentIdAndApotekaIdAndPocetakGreaterThanEqual(farmaceutId, null, apotekaId, pocetak);
	}
	
	public List<Savetovanje> findAllInRangeForFarmaceut(Integer id,LocalDateTime start,LocalDateTime end){
		return savetovanjeRep.findInRangeForFarmaceut(id,start, end);
	}
	
	@Transactional(readOnly=true)
	public List<Savetovanje> findAllInRangeForPacijent(Integer id,LocalDateTime start,LocalDateTime end){
		return savetovanjeRep.findInRangeForPacijent(id,start, end);
	}

	@Transactional(readOnly=true)
	public Set<Apoteka> poseceneApoteke(Integer pacijentId) {
		Set<Apoteka> apoteke = new HashSet<Apoteka>();
		List<Savetovanje> savetovanja = savetovanjeRep.findAllByPacijentId(pacijentId);
		
		for (Savetovanje s : savetovanja) {
			apoteke.add(s.getApoteka());
		}
		return apoteke;
	}
	
	@Transactional(readOnly=true)
	public Set<Farmaceut> poseceniFarmaceuti(Integer pacijentId){
		Set<Farmaceut> farmaceuti = new HashSet<Farmaceut>();
		List<Savetovanje> savetovanja = savetovanjeRep.findAllByPacijentId(pacijentId);
		
		for (Savetovanje s : savetovanja) {
			farmaceuti.add(s.getFarmaceut());
		}
		
		return farmaceuti;
	}

}
