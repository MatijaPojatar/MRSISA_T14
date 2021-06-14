package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.backend.springboot.domain.OdsustvoFarmaceut;
import com.backend.springboot.domain.StatusZahtevaZaOdmor;
import com.backend.springboot.repository.OdsustvoFarmaceutRepository;

@Transactional
@Service
public class OdsustvoFarmaceutService {
	
	@Autowired
	private OdsustvoFarmaceutRepository rep;
	
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

}
