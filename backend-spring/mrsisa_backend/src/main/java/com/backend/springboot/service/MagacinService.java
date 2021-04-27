package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.LekUMagacinu;
import com.backend.springboot.domain.Magacin;
import com.backend.springboot.domain.StavkaCenovnika;
import com.backend.springboot.repository.ApotekaRepository;
import com.backend.springboot.repository.LekRepository;
import com.backend.springboot.repository.LekUMagacinuRepository;
import com.backend.springboot.repository.MagacinRepository;
import com.backend.springboot.repository.StavkaCenovnikaRepository;

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
	@Autowired
	private StavkaCenovnikaRepository cenovnikRep;
	
	public List<Magacin> findAll(){
		return magacinRep.findAll();
	}
	
	public Magacin save(Magacin m) {
		return magacinRep.save(m);
	}
	
	public LekUMagacinu save(LekUMagacinu l) {
		return lekUMagacinuRep.save(l);
	}
	
	public Magacin findOneByApotekaId(Integer apotekaId) {
		return magacinRep.findOneByApotekaId(apotekaId);
	}
	
	public boolean proveriStanje(Integer magacinId,Integer lekId,Double kolicina) {
		LekUMagacinu lum=lekUMagacinuRep.findOneByMagacinIdAndLekIdAndKolicinaGreaterThanEqual(magacinId, lekId, kolicina);
		if(lum==null) {
			return false;
		}
		return true;
	};
	
	public LekUMagacinu dodajLek(LocalDateTime pocetakVazenja, Double cena,  Integer lekId, Integer apotekaId) {
		LekUMagacinu l = new LekUMagacinu();
		l.setPocetakVazenja(pocetakVazenja);
		l.setKolicina(0.0);
		l.setLek(lekRep.findOneById(lekId));
		l.setMagacin(apotekaRep.getOne(apotekaId).getMagacin());
		l.setObrisan(false);
		l.setCena(cena);
		StavkaCenovnika s = new StavkaCenovnika();
		s.setCena(cena);
		s.setLekUMagacinu(l);
		s.setPocetakVazenja(pocetakVazenja);
		l.getSveCene().add(s);
		return lekUMagacinuRep.save(l);
	}
	
	public LekUMagacinu dodajLek(LekUMagacinu l) {
		
		return lekUMagacinuRep.save(l);
	}
	
	public StavkaCenovnika preuzmiTrenutnuCenu(Integer lekId) {
		List<StavkaCenovnika> stavke = cenovnikRep.findAllByLekUMagacinuId(lekId);
		for (StavkaCenovnika s: stavke) {
			if (s.getKrajVazenja() == null) {
				return s;
			}
		}
		return null;
	}
	
	public LekUMagacinu izmeniLekUMagacinu(Double cena, Double kolicina, Integer lekId, Integer apotekaId) {
		Magacin m = magacinRep.findOneByApotekaId(apotekaId);
		ArrayList<LekUMagacinu> lekovi = (ArrayList<LekUMagacinu>) lekUMagacinuRep.findAllByMagacinId(m.getId());
		LocalDateTime sada = LocalDateTime.now();
		for (LekUMagacinu l : lekovi) {
			if (l.getId() == lekId) {
				double trenutnaCena = l.getCena();
				
				if (trenutnaCena != cena) {
					StavkaCenovnika staraStavka = this.preuzmiTrenutnuCenu(lekId);
					
					StavkaCenovnika novaStavka = new StavkaCenovnika();
					novaStavka.setCena(cena);
					novaStavka.setPocetakVazenja(sada);
					novaStavka.setLekUMagacinu(l);
					
					cenovnikRep.save(novaStavka);
					
					staraStavka.setKrajVazenja(sada);
					cenovnikRep.save(staraStavka);
					
					l.setPocetakVazenja(sada);
					l.setKolicina(kolicina);
					l.setCena(cena);
					l.getSveCene().add(novaStavka);
					
					
					
					return lekUMagacinuRep.save(l);
					
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
		LekUMagacinu pronadjen = lekUMagacinuRep.getOne(lekId);
		lekUMagacinuRep.delete(pronadjen);
		
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
			if (l.getId() == lekId)
				return l;
		}
		return null;
	}
}
