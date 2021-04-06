package com.backend.springboot.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.LekUMagacinu;
import com.backend.springboot.domain.Magacin;
import com.backend.springboot.repository.LekRepository;
import com.backend.springboot.repository.LekUMagacinuRepository;
import com.backend.springboot.repository.MagacinRepository;

@Service
public class MagacinService {

	@Autowired
	private MagacinRepository magacinRep;
	@Autowired
	private LekUMagacinuRepository lekUMagacinuRep;
	@Autowired
	private LekRepository lekRep;
	
	public List<Magacin> findAll(){
		return magacinRep.findAll();
	}
	
	public Magacin save(Magacin m) {
		return magacinRep.save(m);
	}
	
	public LekUMagacinu save(LekUMagacinu l) {
		return lekUMagacinuRep.save(l);
	}
	
	public LekUMagacinu dodajLek(Date pocetakVazenja, Double cena, Double kolicina, Integer lekId, Integer magacinId) {
		LekUMagacinu l = new LekUMagacinu();
		l.setPocetakVazenja(pocetakVazenja);
		l.setKolicina(kolicina);
		l.setLek(lekRep.findOneById(lekId));
		l.setMagacin(magacinRep.getOne(magacinId));
		l.setObrisan(false);
		l.setCena(cena);
		return lekUMagacinuRep.save(l);
	}
	
	public LekUMagacinu dodajLek(LekUMagacinu l) {
		
		return lekUMagacinuRep.save(l);
	}
	
	public void obrisiLek(Integer lekId, Integer magacinId) {
		List<LekUMagacinu> pronadjeni = lekUMagacinuRep.findAllByLekId(lekId);
		for(LekUMagacinu l: pronadjeni){
			if (magacinId == l.getMagacin().getId())
				lekUMagacinuRep.delete(l);
		}
	}
	
public void obrisiLek(LekUMagacinu l) {
		lekUMagacinuRep.delete(l);
	}
}
