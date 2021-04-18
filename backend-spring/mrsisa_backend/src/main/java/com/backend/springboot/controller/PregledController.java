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
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.LekUIzvestaju;
import com.backend.springboot.domain.Pregled;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.dto.IzvestajDTO;
import com.backend.springboot.dto.LekUIzvestajuDTO;
import com.backend.springboot.dto.PregledDTO;
import com.backend.springboot.service.LekService;
import com.backend.springboot.service.LekUIzvestajuService;
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

}
