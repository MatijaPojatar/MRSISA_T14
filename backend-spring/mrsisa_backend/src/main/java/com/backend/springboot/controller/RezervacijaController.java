package com.backend.springboot.controller;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.ParametriPretrageRezervacije;
import com.backend.springboot.domain.RezervacijaLeka;
import com.backend.springboot.service.RezervacijaService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/rezervacija")
public class RezervacijaController {
	
	@Autowired
	private RezervacijaService rezService;
	
	private ArrayList<RezervacijaLeka> rezervacijePronadjene;
	
	@PostMapping("/rezultat")
	public ResponseEntity<Collection<RezervacijaLeka>> pretrazi(@RequestBody ParametriPretrageRezervacije params) {
		
		rezervacijePronadjene=(ArrayList<RezervacijaLeka>) rezService.findWithParams(params.getSifra(),
				params.getNaziv(), params.getOblik(), params.getVrsta(), params.getRezim(),"ap1");
		
		return new ResponseEntity<Collection<RezervacijaLeka>>(rezService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RezervacijaLeka>> getRezervacije() {
		return new ResponseEntity<Collection<RezervacijaLeka>>(rezervacijePronadjene, HttpStatus.OK);
	}

}
