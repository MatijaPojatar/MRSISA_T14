package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.OdsustvoFarmaceut;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.domain.StatusZahtevaZaOdmor;
import com.backend.springboot.dto.OdsustvoFarmaceutDTO;
import com.backend.springboot.repository.OdsustvoFarmaceutRepository;
import com.backend.springboot.repository.SavetovanjeRepository;

@Transactional
@Service
public class OdsustvoFarmaceutService {
	
	@Autowired
	private OdsustvoFarmaceutRepository rep;
	
	@Autowired
	private SavetovanjeRepository saveRep;
	
	@Autowired
	private FarmaceutService farmService;
	
	public void save(OdsustvoFarmaceut od) {
		rep.save(od);
	}
	public OdsustvoFarmaceut getOne(Integer id) {
		return rep.getOne(id);
	}
	
	public OdsustvoFarmaceut odobri(Integer id) {
		OdsustvoFarmaceut o = rep.getOne(id);
		o.setStatus(StatusZahtevaZaOdmor.PRIHVACEN);
		return rep.save(o);
	}
	
	public OdsustvoFarmaceut odbij(Integer id, String razlog) {
		OdsustvoFarmaceut o = rep.getOne(id);
		o.setStatus(StatusZahtevaZaOdmor.ODBIJEN);
		o.setRazlog(razlog);
		return rep.save(o);
	}
	
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public List<OdsustvoFarmaceut> findExistInTime(Integer id,LocalDateTime start,LocalDateTime end){
		return rep.findExistInTime(id,start, end);
	}
	
	public List<OdsustvoFarmaceut> findAllByFarmaceutId(Integer farmaceutId){
		return rep.findAllByFarmaceutId(farmaceutId);
	}
	
	public List<OdsustvoFarmaceut> findAllNotOdobrenInFutureApoteka(Integer apotekaId, LocalDateTime start){
		return rep.findAllNotOdobrenInFutureApoteka(apotekaId, start);
	} 
	
	public boolean dodajOdsustvo(Integer id,OdsustvoFarmaceutDTO odsustvo) {
		List<Savetovanje> checkList;
		try {
			checkList=saveRep.findInRangeForFarmaceut(id,odsustvo.getPocetak(),odsustvo.getKraj());
		}catch(Exception e) {
			return false;
		}
		int counter=0;
		for(Savetovanje s:checkList) {
			if(!s.isIzvrsen()) {
				counter++;
			}
		}
		if(counter!=0) {
			return false;
		}
		
		OdsustvoFarmaceut of=new OdsustvoFarmaceut();
		Farmaceut f;
		try {
			f=farmService.findOne(id);
		}catch(Exception e) {
			return false;
		}
		of.setKraj(odsustvo.getKraj());
		of.setPocetak(odsustvo.getPocetak());
		of.setStatus(StatusZahtevaZaOdmor.OBRADA);
		of.setFarmaceut(f);
		of.setApoteka(f.getApoteka());
		
		save(of);
		return true;
		
	}

}
