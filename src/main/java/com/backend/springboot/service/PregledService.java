package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.OdsustvoDermatolog;
import com.backend.springboot.domain.OdsustvoFarmaceut;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.Pregled;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.dto.PregledDTO;
import com.backend.springboot.dto.SavetovanjeDTO;
import com.backend.springboot.repository.PregledRepository;

@Transactional
@Service
public class PregledService {
	
	@Autowired
	private PregledRepository pregledRep;
	
	@Autowired
	private OdsustvoDermatologService odsustvoService;
	
	@Autowired
	private ApotekaService apotekaService;
	
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private DermatologService dermService;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void deletePregled(int id) {
		pregledRep.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public List<Pregled> findAllByPacijentId(Integer pacijentId) {
		return pregledRep.findAllByPacijentId(pacijentId);
	}
	
	@Transactional(readOnly=true)
	public Set<Dermatolog> poseceniDermatolozi(Integer pacijentId){
	Set<Dermatolog> dermatolozi = new HashSet<Dermatolog>();
	List<Pregled> pregledi = pregledRep.findAllByPacijentId(pacijentId);
	
	for (Pregled p : pregledi) {
		dermatolozi.add(p.getDermatolog());
	}
	return dermatolozi;
}
	
	//daj apoteke gde je bio pacijent na pregledu
	@Transactional(readOnly=true)
	public Set<Apoteka> poseceneApoteke(Integer pacijentId) {
		Set<Apoteka> apoteke = new HashSet<Apoteka>();
		List<Pregled> pregledi = pregledRep.findAllByPacijentId(pacijentId);
		
		for (Pregled s : pregledi) {
			apoteke.add(s.getApoteka());
		}
		return apoteke;
	}
	
	@Transactional(readOnly=true)
	public List<Pregled> findAllByDermatologId(Integer dermatologId){
		return pregledRep.findAllByDermatologId(dermatologId);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public Pregled save(Pregled p) {
		return pregledRep.save(p);
	}
	
	@Transactional(readOnly=true)
	public Pregled findOne(Integer id) {
		return pregledRep.findOneById(id);
	}
	
	@Transactional(readOnly=true)
	public List<Pregled> findAllActive(LocalDateTime pocetak){
		return pregledRep.findAllByPocetakGreaterThanEqual(pocetak);
	}
	
	@Transactional(readOnly=true)
	public List<Pregled> findAllActive(Integer dermatologId,Integer apotekaId,LocalDateTime pocetak){
		return pregledRep.findAllByDermatologIdAndPacijentIdAndApotekaIdAndPocetakGreaterThanEqual(dermatologId, null, apotekaId, pocetak);
	}
	
	@Transactional(readOnly=false)
	public List<Pregled> findAllInRangeForDermatolog(Integer id,LocalDateTime start,LocalDateTime end){
		return pregledRep.findInRangeForDermatolog(id,start, end);
	}
	
	@Transactional(readOnly=true)
	public List<Pregled> findAllInRangeForPacijent(Integer id,LocalDateTime start,LocalDateTime end){
		return pregledRep.findInRangeForPacijent(id,start, end);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void zauzmiPregled(Integer id,Pacijent p) {
		Pregled preg=findOne(id);
		if(preg!=null) {
			preg.setPacijent(p);
			pregledRep.save(preg);
		}
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public boolean dodajPregled(Integer id,LocalDateTime pocetak,LocalDateTime kraj,PregledDTO dto) throws InterruptedException {
		List<Pregled> checkList;
		
		try {
			checkList=pregledRep.findInRangeForDermatolog(id,pocetak,kraj);
		}catch(Exception e) {
			return false;
		}
		if(checkList.size()!=0) {
			return false;
		}
		List<OdsustvoDermatolog> checkOdsustva;
		try {
			checkOdsustva=odsustvoService.findExistInTime(id,pocetak, kraj);
		}catch(Exception e) {
			return false;
		}
		if(checkOdsustva.size()!=0) {
			return false;
		}
		
		Pregled p=new Pregled();
		try {
			p.setDermatolog(dermService.findOne(id));
		}catch(Exception e) {
			return false;
		}
		p.setIzvrsen(dto.isIzvrsen());
		try {
			p.setApoteka(apotekaService.findOne(dto.getApotekaId()));
		}catch(Exception e) {
			return false;
		}
		try {
			p.setPacijent(pacijentService.findOne(dto.getPacijentId()));
		}catch(Exception e) {
			return false;
		}
		p.setIzvestaj(dto.getIzvestaj());
		p.setKraj(kraj);
		p.setPocetak(pocetak);
		p.setCena(dto.getCena());
		
		
		pregledRep.save(p);
		
		return true;
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public boolean dodajSlobodanPregled(Integer id,LocalDateTime pocetak,LocalDateTime kraj,PregledDTO dto) {
		List<Pregled> checkList;
		try {
			checkList=pregledRep.findInRangeForDermatolog(id,pocetak,kraj);
		}catch(Exception e) {
			return false;
		}
		if(checkList.size()!=0) {
			return false;
		}
		List<OdsustvoDermatolog> checkOdsustva;
		try {
			checkOdsustva=odsustvoService.findExistInTime(id,pocetak, kraj);
		}catch(Exception e) {
			return false;
		}
		if(checkOdsustva.size()!=0) {
			return false;
		}
		
		Pregled p=new Pregled();
		p.setDermatolog(dermService.findOne(id));
		p.setIzvrsen(dto.isIzvrsen());
		p.setApoteka(apotekaService.findOne(dto.getApotekaId()));
		p.setPacijent(null);
		p.setIzvestaj(null);
		p.setKraj(kraj);
		p.setPocetak(pocetak);
		p.setCena(dto.getCena());
		
		pregledRep.save(p);
		
		return true;
	}

}
