package com.backend.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.LekUIzvestaju;
import com.backend.springboot.repository.LekUIzvestajuRepository;

@Service
public class LekUIzvestajuService {
	
	@Autowired
	private LekUIzvestajuRepository rep;
	
	public void addLek(LekUIzvestaju lek) {
		rep.save(lek);
	}
	
	public List<LekUIzvestaju> findAllByTerminId(Integer id){
		return rep.findAllByTerminId(id);
	}

}
