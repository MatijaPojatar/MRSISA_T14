package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.OdsustvoDermatolog;
import com.backend.springboot.domain.OdsustvoFarmaceut;
import com.backend.springboot.repository.OdsustvoFarmaceutRepository;

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
		o.setOdobren(true);
		return rep.save(o);
	}
	
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
