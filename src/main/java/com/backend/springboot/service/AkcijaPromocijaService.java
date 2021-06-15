package com.backend.springboot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.backend.springboot.domain.AkcijaPromocija;
import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.repository.AkcijaPromocijaRepository;
import com.backend.springboot.repository.ApotekaRepository;

@Service
public class AkcijaPromocijaService {
	@Autowired
	private ApotekaRepository apotekaRep;
	
	@Autowired
	private AkcijaPromocijaRepository apRep;
	
	public List<AkcijaPromocija> findAllByPacijent(Integer id){
		List<AkcijaPromocija> akcijePromocije = new ArrayList<AkcijaPromocija>();
		
		for (Apoteka a : apotekaRep.findAll()) {
			akcijePromocije.addAll(apRep.findAllByApotekaId(a.getId()));
		}
		
		return akcijePromocije;
	}
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public AkcijaPromocija dodaj(Integer apotekaId, String opis, LocalDate kraj ) {
		AkcijaPromocija nova = new AkcijaPromocija();
		nova.setApoteka(apotekaRep.findOneById(apotekaId));
		nova.setKrajVazenja(kraj);
		nova.setOpis(opis);
		nova.setPocetakVazenja(LocalDate.now());
		
		return apRep.save(nova);
		
	}
}
