package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Pregled;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.repository.PregledRepository;

@Service
public class PregledService {
	
	@Autowired
	private PregledRepository pregledRep;
	
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
