package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
