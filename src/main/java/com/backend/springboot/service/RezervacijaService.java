package com.backend.springboot.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.LekUMagacinu;
import com.backend.springboot.domain.Magacin;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.RezervacijaLeka;
import com.backend.springboot.domain.StatusRezervacije;
import com.backend.springboot.repository.ApotekaRepository;
import com.backend.springboot.repository.LekRepository;
import com.backend.springboot.repository.LekUMagacinuRepository;
import com.backend.springboot.repository.PacijentRepository;
import com.backend.springboot.repository.RezervacijaRepository;

@Transactional
@Service
public class RezervacijaService {

	@Autowired
	private RezervacijaRepository rep;
	@Autowired
	private ApotekaRepository apotekaRep;
	@Autowired
	private LekRepository lekRep;
	@Autowired
	private PacijentRepository pacijentRep;

	@Autowired
	private LekUMagacinuRepository lekUMagacinuRep;
	
	@Transactional(readOnly=true)
	public RezervacijaLeka findOneActiveByCodeAndApoteka(String code,Integer apotekaId) {
		return rep.findOneActiveByCodeAndApoteka(code,apotekaId);
	}
	
	@Transactional(readOnly=true)
	public RezervacijaLeka findOneActiveByCode(String code) {
		return rep.findOneActiveByCode(code);
	}
	
	@Transactional(readOnly=true)
	public Set<Apoteka> poseceneApoteke(Integer pacijentId) {
		Set<Apoteka> apoteke = new HashSet<Apoteka>();
		List<RezervacijaLeka> rezervacije = rep.findAllByPacijentId(pacijentId);
		
		for (RezervacijaLeka r : rezervacije) {
			apoteke.add(r.getApoteka());
		}
		return apoteke;
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void save(RezervacijaLeka rl) {
		rep.save(rl);
	}
	
	@Transactional(readOnly=true)
	public List<RezervacijaLeka> findAllActive(){
		return rep.findAllActive();
	}
	
	@Transactional(readOnly=false, propagation=Propagation.SUPPORTS)
	public boolean smanjiKolicinuLeka(Integer magacinId,Integer lekId,Double kolicina) {
		try {
			LekUMagacinu lum=lekUMagacinuRep.findOneByMagacinIdAndLekIdAndKolicinaGreaterThanEqual(magacinId, lekId, kolicina);
			if(lum==null) {
				
				return false;
			}
			if ((lum.getKolicina()-kolicina) < 0) {
				return false;
			}
			lum.setKolicina(lum.getKolicina()-kolicina);
			//Thread.sleep(10000);
			lekUMagacinuRep.save(lum);
			return true;
		}catch(Exception e) {

			Thread.currentThread().interrupt();
			return false;
		}
	};
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public RezervacijaLeka napraviRezervaciju(Integer lekId,Integer pacijentId, Integer apotekaId, Double kolicina, LocalDate datum) {
		try {
			Apoteka a = apotekaRep.findOneById(apotekaId);
			Lek l = lekRep.findOneById(lekId);
			Pacijent p = pacijentRep.findOneById(pacijentId);
			Magacin m = a.getMagacin();
			
			RezervacijaLeka rez = new RezervacijaLeka();
			
			String code;
			while (true){
				code = generateCode();
				RezervacijaLeka pronadjena = rep.findOneActiveByCode(code);
				if (pronadjena==null) {
					rez.setCode(code);
					break;
				}
			}
			
			boolean uspeh = smanjiKolicinuLeka(m.getId(), lekId, kolicina);
			if (!uspeh) {
				return null;
			}
			
			
			rez.setApoteka(a);
			rez.setLek(l);
			rez.setPacijent(p);
			rez.setDatum(datum);
			rez.setKolicina(kolicina);
			rez.setStatus(StatusRezervacije.KREIRANA);
			rez.setKreiranje(LocalDate.now());
			
			try {
				RezervacijaLeka kreirana = rep.save(rez);
				return kreirana;
			}catch(Exception e){
				return null;
			}
		}
		catch(Exception e) {
			return null;
		}
	}
	Random random = new Random();
	
	public String generateCode() {
	    int leftLimit = 48; // '0'
	    int rightLimit = 122; //  'z'
	    int targetStringLength = 5;
	    

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    return generatedString;
	}
	
	public List<RezervacijaLeka> rezervacijePacijenta(Integer pacijentId, StatusRezervacije status) {
		return rep.findByPacijentIdAndStatus(pacijentId, status);
	}
}
