package com.backend.springboot.controller;

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

import com.backend.springboot.domain.Osoba;
import com.backend.springboot.service.OsobaService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/osobe")
public class OsobaController {
	
	@Autowired
	private OsobaService osobaService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Osoba>> getOsobe() {
		Collection<Osoba> osobe = osobaService.findAll();
		
		return new ResponseEntity<Collection<Osoba>>(osobe, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Osoba> dodajOsobu(@RequestBody Osoba osobaInfo){
		Osoba osoba = osobaService.addOsoba(osobaInfo);
		
		return new ResponseEntity<Osoba>(osoba, HttpStatus.OK);
	}
	
}
	

	

