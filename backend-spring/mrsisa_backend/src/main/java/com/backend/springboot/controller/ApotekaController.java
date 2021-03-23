package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Lek;
import com.backend.springboot.service.ApotekaService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/apoteka")
public class ApotekaController {
	@Autowired
	private ApotekaService apotekaService;

	@GetMapping("/lekovi")
	public ResponseEntity<Collection<Lek>> getLekovi() {
		apotekaService.findAll();
		Collection<Lek> lekovi = apotekaService.preuzmiLekoveApoteke("ap2");
		
		return new ResponseEntity<Collection<Lek>>(lekovi, HttpStatus.OK);
	}
	
	/*
	 * @GetMapping("/lekovi") public ResponseEntity<Collection<Lek>>
	 * getLekovi(@PathVariable String idApoteke) { Collection<Lek> lekovi =
	 * apotekaService.preuzmiLekoveApoteke(idApoteke);
	 * 
	 * return new ResponseEntity<Collection<Lek>>(lekovi, HttpStatus.OK); }
	 */

}
