package com.backend.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.OdsustvoDermatolog;
import com.backend.springboot.domain.OdsustvoFarmaceut;
import com.backend.springboot.domain.Pregled;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.dto.OdsustvoFarmaceutDTO;
import com.backend.springboot.service.DermatologService;
import com.backend.springboot.service.FarmaceutService;
import com.backend.springboot.service.OdsustvoDermatologService;
import com.backend.springboot.service.OdsustvoFarmaceutService;
import com.backend.springboot.service.PregledService;
import com.backend.springboot.service.SavetovanjeService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping(value = "/odsustvo")
public class OdsustvoController {
	
	@Autowired
	private OdsustvoFarmaceutService odFarmService;
	@Autowired
	private SavetovanjeService savService;
	@Autowired
	private FarmaceutService farmService;
	@Autowired
	private OdsustvoDermatologService odDermService;
	@Autowired
	private PregledService preService;
	@Autowired
	private DermatologService dermService;
	
	@PutMapping("/farmaceut/dodaj/{id}")
	public ResponseEntity<Boolean> dodajTerminFarmaceut(@PathVariable Integer id,@RequestBody OdsustvoFarmaceutDTO odsustvo){
		List<Savetovanje> checkList=savService.findAllInRange(odsustvo.getPocetak(),odsustvo.getKraj());
		int counter=0;
		for(Savetovanje s:checkList) {
			if(!s.isIzvrsen()) {
				counter++;
			}
		}
		if(counter>0) {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		OdsustvoFarmaceut of=new OdsustvoFarmaceut();
		Farmaceut f=farmService.findOne(id);
		of.setKraj(odsustvo.getKraj());
		of.setPocetak(odsustvo.getPocetak());
		of.setOdobren(odsustvo.isOdobren());
		of.setFarmaceut(f);
		of.setApoteka(f.getApoteka());
		
		odFarmService.save(of);
		
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
	@PutMapping("/dermatolog/dodaj/{id}")
	public ResponseEntity<Boolean> dodajTerminDermatolog(@PathVariable Integer id,@RequestBody OdsustvoFarmaceutDTO odsustvo){
		List<Pregled> checkList=preService.findAllInRange(odsustvo.getPocetak(),odsustvo.getKraj());
		int counter=0;
		for(Pregled p:checkList) {
			if(!p.isIzvrsen()) {
				counter++;
			}
		}
		if(counter>0) {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		OdsustvoDermatolog od=new OdsustvoDermatolog();
		Dermatolog d=dermService.findOne(id);
		od.setKraj(odsustvo.getKraj());
		od.setPocetak(odsustvo.getPocetak());
		od.setOdobren(odsustvo.isOdobren());
		od.setDermatolog(d);
		
		odDermService.save(od);
		
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}

}
