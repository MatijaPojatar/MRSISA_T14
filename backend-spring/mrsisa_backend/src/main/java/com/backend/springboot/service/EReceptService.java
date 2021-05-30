package com.backend.springboot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.ERecept;
import com.backend.springboot.domain.LekERecepta;
import com.backend.springboot.domain.Pacijent;
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
	
	public ERecept dodajERecept(Integer pacijentId, LocalDate datumIzdavanja, List<LekEReceptaDTO> lekovi) {
		Pacijent pacijent = pacijentRep.findOneById(pacijentId);
		
		ERecept erecept = new ERecept();
		erecept.setDatumIzdavanja(datumIzdavanja);
		erecept.setPacijent(pacijentRep.findOneById(pacijentId));
		erecept.setStatus(StatusErecepta.NOV);
		ereceptRep.save(erecept);
		
		ArrayList<LekERecepta> lekoviERecepta = new ArrayList<LekERecepta>();
		
		for(LekEReceptaDTO dto : lekovi) {
			LekERecepta lek = new LekERecepta();
			
			lek.setErecept(erecept);
			lek.setKolicina(dto.getKolicina());
			lek.setLek(lekRep.findOneById(dto.getLekId()));
			
			lek = lekoviEReceptaRep.save(lek);
			
			lekoviERecepta.add(lek);	
		}
		
		erecept.setLekovi(lekoviERecepta);
		
		return ereceptRep.save(erecept);
	}
}
