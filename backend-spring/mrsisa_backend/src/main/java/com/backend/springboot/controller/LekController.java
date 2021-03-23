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
import com.backend.springboot.service.LekService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/lekovi")
public class LekController {

	@Autowired
	private LekService lekService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Lek>> getLekovi() {
		Collection<Lek> lekovi = lekService.findAll();
		
		return new ResponseEntity<Collection<Lek>>(lekovi, HttpStatus.OK);
	}
}
