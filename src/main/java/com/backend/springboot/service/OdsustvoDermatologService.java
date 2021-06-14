package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.OdsustvoDermatolog;
import com.backend.springboot.domain.OdsustvoFarmaceut;
import com.backend.springboot.domain.StatusZahtevaZaOdmor;
import com.backend.springboot.repository.OdsustvoDermatologRepository;

@Service
@Transactional
public class OdsustvoDermatologService {

	@Autowired
	private OdsustvoDermatologRepository rep;
	
	public void save(OdsustvoDermatolog od) {
		rep.save(od);
	}
	
	public OdsustvoDermatolog getOne(Integer id) {
		return rep.getOne(id);
	}
	
	public OdsustvoDermatolog odobri(Integer id) {
		OdsustvoDermatolog o = rep.getOne(id);
		o.setStatus(StatusZahtevaZaOdmor.PRIHVACEN);
		return rep.save(o);
	}
	
	public OdsustvoDermatolog odbij(Integer id, String razlog) {
		OdsustvoDermatolog o = rep.getOne(id);
		o.setStatus(StatusZahtevaZaOdmor.ODBIJEN);
		o.setRazlog(razlog);
		return rep.save(o);
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
}
