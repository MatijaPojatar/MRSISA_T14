package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.DermatologApoteka;
import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.Pol;
import com.backend.springboot.domain.Pregled;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.repository.ApotekaRepository;
import com.backend.springboot.repository.DermatologApotekaRepository;
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
	@Autowired
	private DermatologApotekaRepository zaposlenjeRep;
	
	public List<Dermatolog> findAll(){
		return dermatologRep.findAll();
	}
	
	public Dermatolog save(Dermatolog d) {
		return dermatologRep.save(d);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
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
			for (Apoteka a: d.getApotekeV2()) {
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
				System.out.println("\n\n\n\n\n heheheh \n\n\n\n");
				break;
			}
		}
		
		if (dozvoljenoBrisanje) {
			List<DermatologApoteka> zaposlenja = d.getZaposlenja();
			Set<Dermatolog> dermatolozi =a.getDermatoloziV2();
			DermatologApoteka pronadjen = new DermatologApoteka();
			for (DermatologApoteka z: zaposlenja) {
				if (z.getApoteka().getId() == apotekaId) {
					pronadjen = z;
					break;
					
				}
			}

			zaposlenja.remove(pronadjen);
			pronadjen.setObrisan(true);
			zaposlenjeRep.save(pronadjen);
			a.getDermatoloziV2().remove(d);
			
			d.getApoteke().remove(a);
			dermatologRep.save(d);
			apotekaRep.save(a);
			
			
		}
		
	}
	
	public boolean promeniRadnoVreme (Integer id, Integer apotekaId, LocalTime pocetak, LocalTime kraj) {
		Dermatolog d = dermatologRep.findOneById(id);
		Apoteka a = apotekaRep.findOneById(apotekaId);
		boolean dozvoljenoDodavanje = true;
		for (DermatologApoteka z : d.getZaposlenja()) {
			if (z.getApoteka().getId() != apotekaId && preklapaSe(z.getPocetakRadnogVremena(), z.getKrajRadnogVremena(), pocetak, kraj)) {
				dozvoljenoDodavanje = false;
			}
		}
		
		if (dozvoljenoDodavanje) {
			DermatologApoteka pronadjen = new DermatologApoteka();
			for (DermatologApoteka z : d.getZaposlenja()) {
				if (z.getApoteka().getId() == apotekaId) {
					pronadjen = z;
					break;
				}
			}
			pronadjen.setObrisan(true);
			d.getZaposlenja().remove(pronadjen);
			zaposlenjeRep.save(pronadjen);
			DermatologApoteka zap = new DermatologApoteka();
			zap.setDermatolog(d);
			zap.setApoteka(a);
			zap.setPocetakRadnogVremena(pocetak);
			zap.setKrajRadnogVremena(kraj);
			d.getZaposlenja().add(zap);
			zaposlenjeRep.save(zap);
			dermatologRep.save(d);
			return true;
		}
		
		return false;
	}
	
	public boolean dodajDermatologaUApoteku (Integer id, Integer apotekaId, LocalTime pocetak, LocalTime kraj) {
		Dermatolog d = dermatologRep.findOneById(id);
		Apoteka a = apotekaRep.findOneById(apotekaId);
		boolean dozvoljenoDodavanje = true;
		for (DermatologApoteka z : d.getZaposlenja()) {
			if (preklapaSe(z.getPocetakRadnogVremena(), z.getKrajRadnogVremena(), pocetak, kraj)) {
				dozvoljenoDodavanje = false;
			}
		}
		if (dozvoljenoDodavanje) {
			DermatologApoteka zap = new DermatologApoteka();
			zap.setDermatolog(d);
			zap.setApoteka(a);
			zap.setPocetakRadnogVremena(pocetak);
			zap.setKrajRadnogVremena(kraj);
			d.getZaposlenja().add(zap);
			d.getApoteke().add(a);
			zaposlenjeRep.save(zap);
			dermatologRep.save(d);
			return true;
		}
		
		return false;
	}
	
	public List<Dermatolog> preuzmiDermatologeVanApoteke (Integer apotekaId) {
		ArrayList<Dermatolog> svi = (ArrayList<Dermatolog>) dermatologRep.findAll();
		ArrayList<Dermatolog> zaposleni = (ArrayList<Dermatolog>) findAllByApotekaId(apotekaId);
		ArrayList<Dermatolog> rezultat = new ArrayList<Dermatolog>();
		
		for(Dermatolog d:svi) {
			if (!zaposleni.contains(d)) {
				rezultat.add(d);
			}
		}
		return rezultat;
		
	}
	
	public List<Dermatolog> pretraziDermatologeApoteke(String ime, String prezime, String grad, String drzava, Pol pol, Integer apotekaId){
		ArrayList<Dermatolog> ret = (ArrayList<Dermatolog>) findAllByApotekaId(apotekaId);
		ArrayList<Dermatolog> toRemove=new ArrayList<Dermatolog>(); 
		
		if(!ime.equals("")) {
			for(Dermatolog f:ret) {
				if(!f.getIme().contains(ime)) {
					if(!toRemove.contains(f)) {
						toRemove.add(f);
					}
				}
			}
		}
		if(!prezime.equals("")) {
			for(Dermatolog f:ret) {
				if(!f.getPrezime().contains(prezime)) {
					if(!toRemove.contains(f)) {
						toRemove.add(f);
					}
				}
			}
		}
		if(!grad.equals("")) {
			for(Dermatolog f:ret) {
				if(!f.getGrad().contains(grad)) {
					if(!toRemove.contains(f)) {
						toRemove.add(f);
					}
				}
			}
		}
		if(!drzava.equals("")) {
			for(Dermatolog f:ret) {
				if(!f.getDrzava().contains(drzava)) {
					if(!toRemove.contains(f)) {
						toRemove.add(f);
					}
				}
			}
		}
		if(!(pol==null)) {
			for(Dermatolog f:ret) {
				if(!f.getPol().equals(pol)) {
					if(!toRemove.contains(f)) {
						toRemove.add(f);
					}
				}
			}
		}
		
		
		for(Dermatolog rl:toRemove) {
			ret.remove(rl);
		}
		return ret;
		
	}
	
	public static boolean preklapaSe(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
	    return start1.isBefore(end2) && start2.isBefore(end1);
	}
	
	public DermatologApoteka findZaposlenje(Integer apotekaId,Integer dermatologId) {
		return zaposlenjeRep.findOneByApotekaIdAndDermatologId(apotekaId, dermatologId);
	}

	public Dermatolog findByMail(String mail) {
		return dermatologRep.findOneByMail(mail);
	}

}
