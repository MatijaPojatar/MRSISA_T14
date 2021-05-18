package com.backend.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.AkcijaPromocija;
import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.repository.AkcijaPromocijaRepository;
import com.backend.springboot.repository.ApotekaRepository;

@Service
public class AkcijaPromocijaService {
	@Autowired
	private ApotekaRepository aRep;
	
	@Autowired
	private AkcijaPromocijaRepository apRep;
	
	public List<AkcijaPromocija> findAllByPacijent(Integer id){
		List<AkcijaPromocija> akcijePromocije = new ArrayList<AkcijaPromocija>();
		
		for (Apoteka a : aRep.findAll()) {
			akcijePromocije.addAll(apRep.findAllByApotekaId(a.getId()));
		}
		
		return akcijePromocije;
	}
}
