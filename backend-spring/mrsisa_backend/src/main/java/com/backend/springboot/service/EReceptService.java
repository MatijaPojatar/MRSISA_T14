package com.backend.springboot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.ERecept;
import com.backend.springboot.domain.LekERecepta;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.Pregled;
import com.backend.springboot.domain.StatusErecepta;
import com.backend.springboot.dto.LekEReceptaDTO;
import com.backend.springboot.repository.EReceptRepository;
import com.backend.springboot.repository.LekEReceptaRepository;
import com.backend.springboot.repository.LekRepository;
import com.backend.springboot.repository.PacijentRepository;

@Service
public class EReceptService {
	
	@Autowired
	private EReceptRepository ereceptRep;
	
	@Autowired 
	private LekEReceptaRepository lekoviEReceptaRep;
	
	@Autowired
	private LekRepository lekRep;
	
	@Autowired
	private PacijentRepository pacijentRep;
	
	public List<ERecept> findAll(){
		return ereceptRep.findAll();
	}
	
	public ERecept findOne(Integer id) {
		return ereceptRep.findOneById(id);
	}
	
	public List<ERecept> findByPacijent (Integer pacijentId){
		return ereceptRep.findAllByPacijentId(pacijentId);
	}
	
	public ERecept update(ERecept erecept) {
		return ereceptRep.save(erecept);
	}
	
	public ERecept save(ERecept erecept) {

		
		List<LekERecepta> lekoviERecepta = new ArrayList<LekERecepta>();
		
		for(LekERecepta lek : erecept.getLekovi()) {
			
			lek.setErecept(erecept); //racunam da lek i kolicinu ima
			
			lek = lekoviEReceptaRep.save(lek);
			
			lekoviERecepta.add(lek);	
		}
		
		erecept.setLekovi(lekoviERecepta);
		
		return ereceptRep.save(erecept);
	}

	public boolean qrExists(Integer id) {
		
		if(ereceptRep.findOneByQrId(id) == null) {
			return false;
		}
		
		return true;
	}

	public Set<Apoteka> poseceneApoteke(Integer id) {
		
		Set<Apoteka> apoteke = new HashSet<Apoteka>();
		List<ERecept> erecepti = ereceptRep.findAllByPacijentId(id);
		
		for (ERecept e : erecepti) {
			if(e.getApoteka() != null) {
				apoteke.add(e.getApoteka());
			}
			
		}
		return apoteke;

	}
	
}
