package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.OdsustvoDermatolog;
import com.backend.springboot.domain.OdsustvoFarmaceut;
import com.backend.springboot.domain.Pregled;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.domain.StatusZahtevaZaOdmor;
import com.backend.springboot.dto.OdsustvoDermatologDTO;
import com.backend.springboot.dto.OdsustvoFarmaceutDTO;
import com.backend.springboot.repository.OdsustvoDermatologRepository;
import com.backend.springboot.repository.PregledRepository;

@Service
@Transactional
public class OdsustvoDermatologService {

	@Autowired
	private OdsustvoDermatologRepository rep;
	
	@Autowired
	private PregledRepository preRep;
	
	@Autowired
	private DermatologService dermService;
	
	public void save(OdsustvoDermatolog od) {
		rep.save(od);
	}
	
	public OdsustvoDermatolog getOne(Integer id) {
		return rep.getOne(id);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.SUPPORTS)
	public OdsustvoDermatolog odobri(Integer id, OdsustvoDermatologDTO odsustvo) {
		try {
			List<Pregled> checkList;
			try {
				checkList=preRep.findInRangeForDermatolog(id,odsustvo.getPocetak(),odsustvo.getKraj());
			}catch(Exception e) {
				return null;
			}
			int counter=0;
			for(Pregled p:checkList) {
				if(!p.isIzvrsen()) {
					counter++;
				}
			}
			if(counter!=0) {
				return null;
			}
			
			OdsustvoDermatolog o = rep.findOneById(id);
			if( o.getStatus() != StatusZahtevaZaOdmor.OBRADA) {
				
				return null;
			}
			o.setStatus(StatusZahtevaZaOdmor.PRIHVACEN);
			
			rep.save(o);
			return o;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	@Transactional(readOnly=false,propagation=Propagation.SUPPORTS)
	public OdsustvoDermatolog odbij(Integer id, String razlog) {
		try {
			OdsustvoDermatolog o = rep.findOneById(id);
			if( o.getStatus() != StatusZahtevaZaOdmor.OBRADA) {
				
				return null;
			}
			o.setStatus(StatusZahtevaZaOdmor.ODBIJEN);
			o.setRazlog(razlog);
			
			rep.save(o);
			return o;
		}catch(Exception e) {
			return null;
		}
		
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public OdsustvoDermatolog promeniStatus(Integer id,OdsustvoDermatologDTO odsustvo) {
		if(odsustvo.getStatus() == StatusZahtevaZaOdmor.ODBIJEN) {
			return odbij(id, odsustvo.getRazlog());
		}else if(odsustvo.getStatus() == StatusZahtevaZaOdmor.PRIHVACEN){
			return odobri(id, odsustvo);
		}
		return null;
		
		
	}
	
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public List<OdsustvoDermatolog> findExistInTime(Integer id,LocalDateTime start,LocalDateTime end){
		return rep.findExistInTime(id,start, end);
	}
	
	public List<OdsustvoDermatolog> findAllByDermatologId(Integer dermatologId){
		return rep.findAllByDermatologId(dermatologId);
	}
	
	public List<OdsustvoDermatolog> findAllNotOdobrenInFuture(LocalDateTime start){
		return rep.findAllNotOdobrenInFuture(start);
	} 
	
	public boolean dodajOdsustvo(Integer id,OdsustvoDermatologDTO odsustvo) {
		List<Pregled> checkList;
		try {
			checkList=preRep.findInRangeForDermatolog(id,odsustvo.getPocetak(),odsustvo.getKraj());
		}catch(Exception e) {
			return false;
		}
		int counter=0;
		for(Pregled p:checkList) {
			if(!p.isIzvrsen()) {
				counter++;
			}
		}
		if(counter!=0) {
			return false;
		}
		
		OdsustvoDermatolog od=new OdsustvoDermatolog();
		Dermatolog d;
		try {
			d=dermService.findOne(id);
		}catch(Exception e) {
			return false;
		}
		od.setKraj(odsustvo.getKraj());
		od.setPocetak(odsustvo.getPocetak());
		od.setStatus(StatusZahtevaZaOdmor.OBRADA);
		od.setDermatolog(d);
		
		save(od);
		return true;
		
	}
	
	
	
}
