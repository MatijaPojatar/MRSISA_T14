package com.backend.springboot.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.OblikLeka;
import com.backend.springboot.domain.RezervacijaLeka;
import com.backend.springboot.domain.RezimIzdavanja;
import com.backend.springboot.domain.VrstaLeka;
import com.backend.springboot.repository.InMemoryRezervacijaRepository;

/*
 * Service za rad sa rezervacijama
 */

@Service
public class RezervacijaServiceImplementation implements RezervacijaService{

	@Autowired
	private InMemoryRezervacijaRepository rezRep;

	@Override
	public RezervacijaLeka addRezervacija(RezervacijaLeka rezervacija) {
		return rezRep.addRezervacija(rezervacija);
	}

	@Override
	public RezervacijaLeka findOne(String id) {
		return rezRep.findOne(id);
	}

	@Override
	public Collection<RezervacijaLeka> findAll() {
		return rezRep.findAll();
	}

	@Override
	public void delete(String id) {
		rezRep.delete(id);
		
	}

	@Override
	public Collection<RezervacijaLeka> findWithParams(String sifraLeka, String naziv, OblikLeka oblik, VrstaLeka vrsta,
			RezimIzdavanja rezim, String sifraApoteke) {
		ArrayList<RezervacijaLeka> ret=new ArrayList<RezervacijaLeka>(); 
		if(sifraLeka.equals("")) {
			for(RezervacijaLeka rl: rezRep.findAll()) {
				if(rl.getApotekaId().equals(sifraApoteke)) {
					ret.add(rl);
				}
			}
		}else {
			for(RezervacijaLeka rl: rezRep.findAll()) {
				if(rl.getApotekaId().equals(sifraApoteke) && rl.getLek().getSifra().equals(sifraLeka)) {
					ret.add(rl);
				}
			}
		}
		if(!naziv.equals("")) {
			for(RezervacijaLeka rl:ret) {
				if(!rl.getLek().getNaziv().equals(naziv)) {
					ret.remove(rl);
				}
			}
		}
		if(!oblik.equals(null)) {
			for(RezervacijaLeka rl:ret) {
				if(!rl.getLek().getOblikLeka().equals(oblik)) {
					ret.remove(rl);
				}
			}
		}
		if(!vrsta.equals(null)) {
			for(RezervacijaLeka rl:ret) {
				if(!rl.getLek().getVrstaLeka().equals(vrsta)) {
					ret.remove(rl);
				}
			}
		}
		if(!rezim.equals(null)) {
			for(RezervacijaLeka rl:ret) {
				if(!rl.getLek().getRezimIzdavanja().equals(rezim)) {
					ret.remove(rl);
				}
			}
		}
		return ret;
	} 

}
