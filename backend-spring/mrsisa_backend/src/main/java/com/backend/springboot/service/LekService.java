package com.backend.springboot.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.LekUMagacinu;
import com.backend.springboot.domain.OblikLeka;
import com.backend.springboot.domain.RezimIzdavanja;
import com.backend.springboot.domain.VrstaLeka;
import com.backend.springboot.repository.ApotekaRepository;
import com.backend.springboot.repository.LekRepository;
import com.backend.springboot.repository.LekUMagacinuRepository;
import com.backend.springboot.repository.MagacinRepository;

/*
 * Service za rad sa lekovima
 */

@Service
public class LekService {
	
	@Autowired
	private LekRepository lekRep;
	@Autowired
	private ApotekaRepository apotekaRep;
	@Autowired
	private MagacinRepository magacinRep;
	@Autowired
	private LekUMagacinuRepository lekUMagacinuRep;
	
	

	public List<Lek> findAll() {
		return lekRep.findAll();
	}

	public Lek addLek(Lek lek) {
		return lekRep.save(lek);
	}

	public Lek findOneById(Integer sifra) {
		return lekRep.findOneById(sifra);
	}
	
	public void deleteLek(int id) {
		lekRep.deleteById(id);
	}

	

	public Collection<Lek> findWithParams(int sifraLeka, String naziv, OblikLeka oblik, VrstaLeka vrsta,
			RezimIzdavanja rezim, int sifraApoteke, String proizvodjac) {
		
		//IZMENITI KADA SKONTAM KAKO DA VIDIM KOJI ADMIN APOTEKE JE ULOGOVAN
		
		ArrayList<Lek> ret=new ArrayList<Lek>(); 
		Collection<Lek> svi= lekRep.findAll();
		if(sifraLeka == 0) {
			for(Lek l: svi) {
				for (Apoteka a: l.getSnabdeveneApoteke()) {
					if (a.getId() == sifraApoteke) {
						ret.add(l);
					}
				}
			}
		}else {
			for(Lek l: svi) {
				for (Apoteka a: l.getSnabdeveneApoteke()) {
					if (a.getId() == sifraApoteke && l.getId() == sifraLeka) {
						ret.add(l);
					}
				}
			}
		}
		ArrayList<Lek> toRemove=new ArrayList<Lek>(); 
		if(!naziv.equals("")) {
			for(Lek l:ret) {
				if(!l.getNaziv().equals(naziv)) {
					if(!toRemove.contains(l)) {
						toRemove.add(l);
					}
				}
			}
		}
		if(!(oblik==null)) {
			for(Lek l:ret) {
				if(!l.getOblikLeka().equals(oblik)) {
					if(!toRemove.contains(l)) {
						toRemove.add(l);
					}
				}
			}
		}
		if(!(vrsta==null)) {
			for(Lek l:ret) {
				if(!l.getVrstaLeka().equals(vrsta)) {
					if(!toRemove.contains(l)) {
						toRemove.add(l);
					}
				}
			}
		}
		if(!(rezim==null)) {
			for(Lek l:ret) {
				if(!l.getRezimIzdavanja().equals(rezim)) {
					if(!toRemove.contains(l)) {
						toRemove.add(l);
					}
				}
			}
		}
		if(!proizvodjac.equals("")) {
			for(Lek l:ret) {
				if(!l.getProizvodjac().equals(proizvodjac)) {
					if(!toRemove.contains(l)) {
						toRemove.add(l);
					}
				}
			}
		}
		
		for(Lek rl:toRemove) {
			ret.remove(rl);
		}
		
		return ret;
	}
	
	public List<Lek> findAllByApoteka(Integer id) {
		//funkcija vraca i obrisane lekove, za preuzimanje aktuelnih lekova, koristiti funkciju iz magacin servisa
		return lekRep.findAllBySnabdeveneApoteke_Id(id);
	}

	public void addZamenskeZaLek(int id, List<Integer> zamenskiIds) {
		Lek lek = lekRep.findOneById(id);
		
		for (Integer i : zamenskiIds) {
			Lek zamenski = lekRep.findOneById(i);
			
			lek.addZamenskiLek(zamenski);
		}
		
		lekRep.save(lek);
	}
	
	public List<Lek> preuzmiLekoveVanApoteke(Integer apotekaId){
		ArrayList<Lek> lekovi = (ArrayList<Lek>) lekRep.findAll();
		ArrayList<LekUMagacinu> lekoviApoteke = (ArrayList<LekUMagacinu>) lekUMagacinuRep.findAllByMagacinId(magacinRep.findOneByApotekaId(apotekaId).getId());
		
		boolean dodavanje = true;
		for (LekUMagacinu l: lekoviApoteke) {
			lekovi.remove(l.getLek());
		}
		return lekovi;
	}

}
