package com.backend.springboot.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.OblikLeka;
import com.backend.springboot.domain.RezimIzdavanja;
import com.backend.springboot.domain.VrstaLeka;
import com.backend.springboot.repository.LekRepository;

/*
 * Service za rad sa lekovima
 */

@Service
public class LekService {
	
	@Autowired
	private LekRepository lekRep;
	
	

	public Collection<Lek> findAll() {
		return lekRep.findAll();
	}

	public Lek addLek(Lek lek) {
		return lekRep.save(lek);
	}

	public Lek findOneById(Integer sifra) {
		return lekRep.findOneById(sifra);
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

}
