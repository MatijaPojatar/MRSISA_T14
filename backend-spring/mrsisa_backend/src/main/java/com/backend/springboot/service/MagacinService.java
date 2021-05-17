package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.LekIzNarudzbenice;
import com.backend.springboot.domain.LekUMagacinu;
import com.backend.springboot.domain.Magacin;
import com.backend.springboot.domain.Narudzbenica;
import com.backend.springboot.domain.OblikLeka;
import com.backend.springboot.domain.Ponuda;
import com.backend.springboot.domain.RezimIzdavanja;
import com.backend.springboot.domain.StatusNarudzbenice;
import com.backend.springboot.domain.StatusPonude;
import com.backend.springboot.domain.StavkaCenovnika;
import com.backend.springboot.domain.Upit;
import com.backend.springboot.domain.VrstaLeka;
import com.backend.springboot.repository.ApotekaRepository;
import com.backend.springboot.repository.LekRepository;
import com.backend.springboot.repository.LekUMagacinuRepository;
import com.backend.springboot.repository.MagacinRepository;
import com.backend.springboot.repository.NarudzbenicaRepository;
import com.backend.springboot.repository.PonudaRepository;
import com.backend.springboot.repository.StavkaCenovnikaRepository;
import com.backend.springboot.repository.UpitRepository;

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
	@Autowired
	private UpitRepository upitRep;
	@Autowired
	private NarudzbenicaRepository narudzbenicaRep;
	@Autowired
	private PonudaRepository ponudeRep;
	
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
	public List<Upit> preuzmiUpite(Integer magacinId){
		return upitRep.findAllByMagacinId(magacinId);
	}
	
	public List<Narudzbenica> preuzmiSveNarudzbenice(Integer magacinId){
		return narudzbenicaRep.findAllByMagacinId(magacinId);
	}
	
	public List<Narudzbenica> preuzmiNarudzbenicePoStatusu(Integer magacinId, StatusNarudzbenice status){
		return narudzbenicaRep.findAllByStatusAndMagacinId(status, magacinId);
	}
	
	public void prihvatiPonudu(Integer narudzbenicaId, Integer ponudaId) {
		//dodati slanje mejla
		Narudzbenica narudzbenica = narudzbenicaRep.findOneById(narudzbenicaId);
		narudzbenica.setStatus(StatusNarudzbenice.OBRADJENA);
		for(Ponuda p : ponudeRep.findAllByNarudzbenicaId(narudzbenicaId)) {
			if (p.getId() != ponudaId) {
				p.setStatus(StatusPonude.ODBIJENA);
				ponudeRep.save(p);
				continue;
			}
			p.setStatus(StatusPonude.PRIHVACENA);
			ponudeRep.save(p);
			
		}
		for(LekIzNarudzbenice lek: narudzbenica.getLekovi()) {
			LekUMagacinu m = lekUMagacinuRep.findOneByMagacinIdAndLekId(narudzbenica.getMagacin().getId(), lek.getLek().getId());
			if (m != null) {
				m.setKolicina(m.getKolicina() + lek.getKolicina());
				lekUMagacinuRep.save(m);
			}
			else {
				dodajLek(LocalDateTime.now(), 200.0, lek.getLek().getId(), narudzbenica.getMagacin().getApoteka().getId(), lek.getKolicina());
			}
		}
		
	}
	
	
	public boolean proveriStanje(Integer magacinId,Integer lekId,Double kolicina) {
		LekUMagacinu lum=lekUMagacinuRep.findOneByMagacinIdAndLekIdAndKolicinaGreaterThanEqual(magacinId, lekId, kolicina);
		if(lum==null) {
			Upit u = upitRep.findOneByMagacinIdAndLekId(magacinId, lekId);
			if (u == null) {
				Magacin m = magacinRep.getOne(magacinId);
				Lek l = lekRep.findOneById(lekId);
				Upit upit = new Upit();
				upit.setKolicina(1.0);
				upit.setMagacin(m);
				upit.setLek(l);
				upit.setObrisan(false);
			}else {
				u.setKolicina(u.getKolicina()+1);
			}
			return false;
		}
		return true;
	};
	
	public LekUMagacinu dodajLek(LocalDateTime pocetakVazenja, Double cena,  Integer lekId, Integer apotekaId, Double kolicina) {
		LekUMagacinu l = new LekUMagacinu();
		l.setPocetakVazenja(pocetakVazenja);
		l.setKolicina(kolicina);
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
	
	public List<LekUMagacinu> pretraziLekoveMagacina(Integer sifraLeka, String naziv, OblikLeka oblik, VrstaLeka vrsta,
			RezimIzdavanja rezim, Integer apotekaId, String proizvodjac) {
		
		
		Magacin magacin = magacinRep.findOneByApotekaId(apotekaId);
		ArrayList<LekUMagacinu> ret=new ArrayList<LekUMagacinu>(); 
		Collection<LekUMagacinu> svi= preuzmiAktivneLekove(magacin.getId());
		if(sifraLeka == 0) {
			for(LekUMagacinu l: svi) {
					ret.add(l);	
			}
			
		}else {
			for(LekUMagacinu l: svi) {
				if ( l.getLek().getId() == sifraLeka) {
					ret.add(l);
				}
			}
		}
		ArrayList<LekUMagacinu> toRemove=new ArrayList<LekUMagacinu>(); 
		if(!naziv.equals("")) {
			for(LekUMagacinu l:ret) {
				if(!l.getLek().getNaziv().equals(naziv)) {
					if(!toRemove.contains(l)) {
						toRemove.add(l);
					}
				}
			}
		}
		if(!(oblik==null)) {
			for(LekUMagacinu l:ret) {
				if(!l.getLek().getOblikLeka().equals(oblik)) {
					if(!toRemove.contains(l)) {
						toRemove.add(l);
					}
				}
			}
		}
		if(!(vrsta==null)) {
			for(LekUMagacinu l:ret) {
				if(!l.getLek().getVrstaLeka().equals(vrsta)) {
					if(!toRemove.contains(l)) {
						toRemove.add(l);
					}
				}
			}
		}
		if(!(rezim==null)) {
			for(LekUMagacinu l:ret) {
				if(!l.getLek().getRezimIzdavanja().equals(rezim)) {
					if(!toRemove.contains(l)) {
						toRemove.add(l);
					}
				}
			}
		}
		if(!proizvodjac.equals("")) {
			for(LekUMagacinu l:ret) {
				if(!l.getLek().getProizvodjac().equals(proizvodjac)) {
					if(!toRemove.contains(l)) {
						toRemove.add(l);
					}
				}
			}
		}
		
		for(LekUMagacinu rl:toRemove) {
			ret.remove(rl);
			System.out.println("\n\njjjjjjjjjjjjjjjjjjjjjjj\n\n");
		}
		
		return ret;
	}
}
