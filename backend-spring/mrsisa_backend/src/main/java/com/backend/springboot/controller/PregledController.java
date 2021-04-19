package com.backend.springboot.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.LekUIzvestaju;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.Pregled;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.dto.IzvestajDTO;
import com.backend.springboot.dto.LekUIzvestajuDTO;
import com.backend.springboot.dto.PregledDTO;
import com.backend.springboot.dto.SavetovanjeDTO;
import com.backend.springboot.service.ApotekaService;
import com.backend.springboot.service.DermatologService;
import com.backend.springboot.service.LekService;
import com.backend.springboot.service.LekUIzvestajuService;
import com.backend.springboot.service.PacijentService;
import com.backend.springboot.service.PregledService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping(value = "/pregled")
public class PregledController {
	
	@Autowired
	private PregledService service;
	@Autowired
	private LekUIzvestajuService izvestajService;
	@Autowired
	private LekService lekService;
	@Autowired
	private PacijentService pacijentService;
	@Autowired
	private ApotekaService apotekaService;
	@Autowired
	private DermatologService derService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<PregledDTO>> getAll() {

		List<Pregled> pregledi = service.findAllByDermatologId(3);

		List<PregledDTO> preglediDTO = new ArrayList<>();
		for (Pregled p : pregledi) {
			preglediDTO.add(new PregledDTO(p));
		}

		return new ResponseEntity<>(preglediDTO, HttpStatus.OK);
	}
	
	@PutMapping("/penal/{id}")
	public ResponseEntity<String> pacijentNijeDosao(@PathVariable Integer id){
		Pregled p=service.findOne(id);
		p.setIzvrsen(true);
		p.setIzvestaj("Pacijent se nije pojavio");
		
		service.save(p);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PutMapping("/izvestaj/{id}")
	public ResponseEntity<String> sacuvajIzvestaj(@PathVariable Integer id,@RequestBody IzvestajDTO izvestaj){
		Pregled p=service.findOne(id);
		p.setIzvrsen(true);
		p.setIzvestaj(izvestaj.getText());
		for(LekUIzvestajuDTO lekDto: izvestaj.getLekovi()) {
			Lek l=lekService.findOneById(lekDto.getLekId());
			LekUIzvestaju lekIz=new LekUIzvestaju( l, lekDto.getTerapija(),p);
			izvestajService.addLek(lekIz);
			p.getLekovi().add(lekIz);
		}
		
		service.save(p);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/{id}")
	public ResponseEntity<List<PregledDTO>> getAllForDermatolog(@PathVariable Integer id) {

		List<Pregled> pregledi = service.findAllByDermatologId(id);

		List<PregledDTO> preglediDTO = new ArrayList<>();
		for (Pregled p : pregledi) {
			preglediDTO.add(new PregledDTO(p));
		}

		return new ResponseEntity<>(preglediDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/active/{id}")
	public ResponseEntity<List<PregledDTO>> getAllActiveForDermatolog(@PathVariable Integer id,@RequestParam Integer apotekaId) {		

		LocalDateTime pocetak=LocalDateTime.now();
		List<Pregled> pregledi = service.findAllActive(id, apotekaId, pocetak);
		

		List<PregledDTO> preglediDTO = new ArrayList<>();
		for (Pregled p : pregledi) {
			preglediDTO.add(new PregledDTO(p));
		}

		return new ResponseEntity<>(preglediDTO, HttpStatus.OK);
	}
	
	@PutMapping("/zauzmi/{id}/{pacijentId}")
	public ResponseEntity<String> zauzmiTermin(@PathVariable Integer id,@PathVariable Integer pacijentId){
		Pregled pre=service.findOne(id);
		Pacijent p=pacijentService.findOne(pacijentId);
		pre.setPacijent(p);
		service.save(pre);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PutMapping("/dodaj/{id}")
	public ResponseEntity<Boolean> dodajTermin(@PathVariable Integer id,@RequestBody PregledDTO pregled){
		LocalDateTime pocetak=pregled.getStart().plusHours(2);
		LocalDateTime kraj=pregled.getEnd().plusHours(2);
		
		List<Pregled> checkList=service.findAllInRange(pocetak,kraj);
		if(checkList.size()!=0) {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		Pregled p=new Pregled();
		p.setDermatolog(derService.findOne(id));
		p.setIzvrsen(pregled.isIzvrsen());
		p.setApoteka(apotekaService.findOne(pregled.getApotekaId()));
		p.setPacijent(pacijentService.findOne(pregled.getPacijentId()));
		p.setIzvestaj(pregled.getIzvestaj());
		p.setKraj(kraj);
		p.setPocetak(pocetak);
		
		service.save(p);
		
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}

}
