package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.repository.FarmaceutRepository;
import com.backend.springboot.repository.SavetovanjeRepository;

@Service
public class FarmaceutService {
	
	@Autowired
	private FarmaceutRepository farmaceutRep;
	@Autowired
	private SavetovanjeRepository savetovanjeRep;
	
	public List<Farmaceut> findAll(){
		return farmaceutRep.findAll();
	}
	
	public Farmaceut save(Farmaceut f) {
		return farmaceutRep.save(f);
	}
	
	public Farmaceut findOne(Integer id) {
		return farmaceutRep.findOneById(id);
	}
	
	public List<Farmaceut> findAllByApotekaId(Integer id){
		return farmaceutRep.findAllByApotekaId(id);
	}
	
	public void obrisiFarmaceuta (Integer id) {
		Farmaceut f = farmaceutRep.findOneById(id);
		LocalDateTime sada = LocalDateTime.now();
		boolean dozvoljenoBrisanje = true;
		List<Savetovanje> savetovanja = savetovanjeRep.findAllByFarmaceutId(id);
		
		for (Savetovanje s: savetovanja) {
			if (s.getPocetak().isAfter(sada)) {
				dozvoljenoBrisanje = false;
				break;
			}
		}
		
		if (dozvoljenoBrisanje) {
			f.setObrisan(true);
			farmaceutRep.save(f);
		}
		
	}

}
