package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.dto.ApotekaDTO;
import com.backend.springboot.service.ApotekaService;
import com.backend.springboot.service.MagacinService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/apoteke")
public class ApotekaController {
	@Autowired
	private ApotekaService apotekaService;

	private ArrayList<Apoteka> pronadjeneApoteke;
	
	@Autowired 
	private MagacinService magacinService;
	
	/*@GetMapping("/lekovi")
	public ResponseEntity<Collection<Lek>> getLekovi() {
		apotekaService.findAll();
		Collection<Lek> lekovi = apotekaService.preuzmiLekoveApoteke("ap2");
		
		return new ResponseEntity<Collection<Lek>>(lekovi, HttpStatus.OK);
	}*/
	
	@PostMapping()
	public ResponseEntity<ApotekaDTO> addApoteka(ApotekaDTO dto) throws Exception{
		try {
			System.out.println(dto.getNaziv() + "NAZIV");
			System.out.println(dto.getAdresa() + "ADRSEA");
            return new ResponseEntity<>(apotekaService.addApoteka(dto), HttpStatus.CREATED);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
	
	
	@PostMapping("/rezultat")
	public ResponseEntity<Collection<Apoteka>> pretrazi(String naziv, String grad) {
		pronadjeneApoteke = (ArrayList<Apoteka>) apotekaService.pronadjiApoteke(naziv, grad);
		return new ResponseEntity<Collection<Apoteka>>(pronadjeneApoteke, HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Apoteka>> getApoteke() {
		return new ResponseEntity<Collection<Apoteka>>(pronadjeneApoteke, HttpStatus.OK);
	}
	
	@PostMapping("/obrisiLek")
	public ResponseEntity<String> obrisiLek(Integer idLeka) {
		magacinService.obrisiLek(idLeka, 1);
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PostMapping("/dodajLek")
	public ResponseEntity<Collection<Apoteka>> dodajLek() {
		Date pocetakVazenja = new Date(2020, 4, 6);
		magacinService.dodajLek(pocetakVazenja, 100.0, 200.0, 1, 1);
		return new ResponseEntity<Collection<Apoteka>>(pronadjeneApoteke, HttpStatus.OK);
	}
}
