package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.Pregled;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.repository.ApotekaRepository;
import com.backend.springboot.repository.DermatologRepository;
import com.backend.springboot.repository.PregledRepository;

@Service
public class DermatologService {
	
	@Autowired
	private DermatologRepository dermatologRep;
	@Autowired
	private PregledRepository pregledRep;
	@Autowired
	private ApotekaRepository apotekaRep;
	
	public List<Dermatolog> findAll(){
		return dermatologRep.findAll();
	}
	
	public Dermatolog save(Dermatolog d) {
		return dermatologRep.save(d);
	}
	
	public Dermatolog findOne(Integer id) {
		return dermatologRep.findOneById(id);
	}

	public Dermatolog registrujDermatologa(Dermatolog dermatolog) {
		return dermatologRep.save(dermatolog);
	}
	
	public List<Dermatolog> findAllByApotekaId(Integer id){
		ArrayList<Dermatolog> svi = (ArrayList<Dermatolog>) dermatologRep.findAll();
		ArrayList<Dermatolog> pronadjeni = new ArrayList<Dermatolog>();
		for (Dermatolog d: svi) {
			for (Apoteka a: d.getApoteke()) {
				if (a.getId() == id) {
					pronadjeni.add(d);
				}
			}
			
		}
		return pronadjeni;
	}
	
	public void obrisiDermatologaIzApoteke (Integer id, Integer apotekaId) {
		Dermatolog d = dermatologRep.findOneById(id);
		Apoteka a = apotekaRep.findOneById(apotekaId);
		LocalDateTime sada = LocalDateTime.now();
		boolean dozvoljenoBrisanje = true;
		List<Pregled> pregledi = pregledRep.findAllByDermatologId(id);
		
		for (Pregled p: pregledi) {
			if (p.getPocetak().isAfter(sada)) {
				dozvoljenoBrisanje = false;
				break;
			}
		}
		
		if (dozvoljenoBrisanje) {
			d.getApoteke().remove(a);
			dermatologRep.save(d);
		}
		
	}

}
