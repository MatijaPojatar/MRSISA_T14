package com.backend.springboot.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.backend.springboot.dto.OdsustvoDermatologDTO;
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
		List<Savetovanje> checkList=savService.findAllInRangeForFarmaceut(id,odsustvo.getPocetak(),odsustvo.getKraj());
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
		List<Pregled> checkList=preService.findAllInRangeForDermatolog(id,odsustvo.getPocetak(),odsustvo.getKraj());
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
	
	@GetMapping("/farmaceut/all/{id}")
	public ResponseEntity<List<OdsustvoFarmaceutDTO>> getAllForFarmaceut(@PathVariable Integer id){
		List<OdsustvoFarmaceut> odsustva=odFarmService.findAllByFarmaceutId(id);
		ArrayList<OdsustvoFarmaceutDTO> dtos=new ArrayList<OdsustvoFarmaceutDTO>();
		for(OdsustvoFarmaceut o:odsustva) {
			dtos.add(new OdsustvoFarmaceutDTO(o));
		}
		
		return new ResponseEntity<List<OdsustvoFarmaceutDTO>>(dtos,HttpStatus.OK);
	}
	
	@GetMapping("/dermatolog/all/{id}")
	public ResponseEntity<List<OdsustvoDermatologDTO>> getAllForDermatolog(@PathVariable Integer id){
		List<OdsustvoDermatolog> odsustva=odDermService.findAllByDermatologId(id);
		ArrayList<OdsustvoDermatologDTO> dtos=new ArrayList<OdsustvoDermatologDTO>();
		for(OdsustvoDermatolog o:odsustva) {
			dtos.add(new OdsustvoDermatologDTO(o));
		}
		
		return new ResponseEntity<List<OdsustvoDermatologDTO>>(dtos,HttpStatus.OK);
	}
	
	@GetMapping("/farmaceut/zaOdobrenje/{apotekaId}")
	public ResponseEntity<List<OdsustvoFarmaceutDTO>> getAllZaOdobrenjaForFarmaceut(@PathVariable Integer apotekaId){
		List<OdsustvoFarmaceut> odsustva=odFarmService.findAllNotOdobrenInFutureApoteka(apotekaId, LocalDateTime.now());
		ArrayList<OdsustvoFarmaceutDTO> dtos=new ArrayList<OdsustvoFarmaceutDTO>();
		for(OdsustvoFarmaceut o:odsustva) {
			dtos.add(new OdsustvoFarmaceutDTO(o));
		}
		
		return new ResponseEntity<List<OdsustvoFarmaceutDTO>>(dtos,HttpStatus.OK);
	}
	
	@GetMapping("/dermatolog/zaOdobrenje")
	public ResponseEntity<List<OdsustvoDermatologDTO>> getAllZaOdobrenjaForDermatolog(){
		List<OdsustvoDermatolog> odsustva=odDermService.findAllNotOdobrenInFuture(LocalDateTime.now());
		ArrayList<OdsustvoDermatologDTO> dtos=new ArrayList<OdsustvoDermatologDTO>();
		for(OdsustvoDermatolog o:odsustva) {
			dtos.add(new OdsustvoDermatologDTO(o));
		}
		
		return new ResponseEntity<List<OdsustvoDermatologDTO>>(dtos,HttpStatus.OK);
	}
	
	@PutMapping("/farmaceut/zaOdobrenje/{zahtevId}")
	public ResponseEntity<Boolean> odobriFarmaceut(@PathVariable Integer zahtevId){
		odFarmService.odobri(zahtevId);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
	@PutMapping("/dermatolog/zaOdobrenje/{zahtevId}")
	public ResponseEntity<Boolean> odobriDermatolog(@PathVariable Integer zahtevId){
		odDermService.odobri(zahtevId);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}

}
