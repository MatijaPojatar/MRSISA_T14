package com.backend.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.repository.DermatologRepository;

@Service
public class DermatologService {
	
	@Autowired
	private DermatologRepository dermatologRep;
	
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

}
