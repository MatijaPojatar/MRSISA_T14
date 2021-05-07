package com.backend.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.OdsustvoDermatolog;
import com.backend.springboot.repository.OdsustvoDermatologRepository;

@Service
public class OdsustvoDermatologService {

	@Autowired
	private OdsustvoDermatologRepository rep;
	
	public void save(OdsustvoDermatolog od) {
		rep.save(od);
	}
	
	public List<OdsustvoDermatolog> findExistInTime(Integer id,LocalDateTime start,LocalDateTime end){
		return rep.findExistInTime(id,start, end);
	}
}
