package com.backend.springboot.controller;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Lek;
import com.backend.springboot.service.ApotekaService;
import com.backend.springboot.service.LekService;
import com.backend.springboot.service.RezervacijaService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/rezervacija")
public class RezervacijaController {
	
	@Autowired
	private ApotekaService apotekaService;
	@Autowired
	private LekService lekService;
	@Autowired
	private RezervacijaService rezService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Lek>> getLekovi() {
		Collection<Lek> lekovi = lekService.findAll();
		
		return new ResponseEntity<Collection<Lek>>(lekovi, HttpStatus.OK);
	}

}
