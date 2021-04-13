package com.backend.springboot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.LekUMagacinu;
import com.backend.springboot.domain.Magacin;
import com.backend.springboot.repository.ApotekaRepository;
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
	@Autowired
	private ApotekaRepository apotekaRep;
	
	public List<Magacin> findAll(){
		return magacinRep.findAll();
	}
	
	public Magacin save(Magacin m) {
		return magacinRep.save(m);
	}
	
	public LekUMagacinu save(LekUMagacinu l) {
		return lekUMagacinuRep.save(l);
	}
	
	public LekUMagacinu dodajLek(LocalDate pocetakVazenja, Double cena, Double kolicina, Integer lekId, Integer apotekaId) {
		LekUMagacinu l = new LekUMagacinu();
		l.setPocetakVazenja(pocetakVazenja);
		l.setKolicina(kolicina);
		l.setLek(lekRep.findOneById(lekId));
		l.setMagacin(apotekaRep.getOne(apotekaId).getMagacin());
		l.setObrisan(false);
		l.setCena(cena);
		return lekUMagacinuRep.save(l);
	}
	
	public LekUMagacinu dodajLek(LekUMagacinu l) {
		
		return lekUMagacinuRep.save(l);
	}
	
	public LekUMagacinu izmeniLekUMagacinu(Double cena, Double kolicina, Integer lekId, Integer apotekaId) {
		Magacin m = magacinRep.findOneByApotekaId(apotekaId);
		ArrayList<LekUMagacinu> lekovi = (ArrayList<LekUMagacinu>) lekUMagacinuRep.findAllByMagacinId(m.getId());
		for (LekUMagacinu l : lekovi) {
			if (l.getId() == lekId) {
				double staraCena = l.getCena();
				
				if (staraCena != cena) {
					LekUMagacinu novi = new LekUMagacinu();
					novi.setPocetakVazenja(LocalDate.now());
					novi.setKolicina(kolicina);
					novi.setLek(l.getLek());
					novi.setMagacin(l.getMagacin());
					novi.setObrisan(false);
					novi.setCena(cena);
					
					
					l.setObrisan(true);
					l.setKrajVazenja(LocalDate.now());
					
					lekUMagacinuRep.save(l);
					System.out.println("\n\n\n\n\n");
					System.out.println(novi.getId());
					System.out.println("\n\n\n\n\n");
					return lekUMagacinuRep.save(novi);
					
				}
				else {
					l.setKolicina(kolicina);
					return lekUMagacinuRep.save(l);
				}
		
			}
		}
		return null;
		
	}
	
	
	
	public void obrisiLek(Integer lekId, Integer apotekaId) {
		List<LekUMagacinu> pronadjeni = lekUMagacinuRep.findAllByLekId(lekId);
		for(LekUMagacinu l: pronadjeni){
			if (apotekaRep.getOne(apotekaId).getMagacin().getId() == l.getMagacin().getId())
				lekUMagacinuRep.delete(l);
		}
	}
	
	public void obrisiLek(LekUMagacinu l) {
			lekUMagacinuRep.delete(l);
		}
	
	public List<LekUMagacinu> preuzmiAktivneLekove(Integer magacinId) {
		return lekUMagacinuRep.findAllByMagacinId(magacinId);
		
	}
	
	public LekUMagacinu preuzmiJedanLekApoteke(Integer lekId, Integer apotekaId) {
		List<LekUMagacinu> lekovi = lekUMagacinuRep.findAllByMagacinId(apotekaRep.getOne(apotekaId).getMagacin().getId());
		for(LekUMagacinu l: lekovi){
			if (l.getLek().getId() == lekId)
				return l;
		}
		return null;
	}
}
