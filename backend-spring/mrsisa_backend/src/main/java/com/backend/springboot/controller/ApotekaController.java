package com.backend.springboot.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.LekUMagacinu;
import com.backend.springboot.dto.LekUMagacinuDTO;
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
	
	@PostMapping("/rezultat")
	public ResponseEntity<Collection<Apoteka>> pretrazi(String naziv, String grad) {
		pronadjeneApoteke = (ArrayList<Apoteka>) apotekaService.pronadjiApoteke(naziv, grad);
		return new ResponseEntity<Collection<Apoteka>>(pronadjeneApoteke, HttpStatus.OK);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Apoteka>> getApoteke() {
		return new ResponseEntity<Collection<Apoteka>>(pronadjeneApoteke, HttpStatus.OK);
	}
	
	@PutMapping("/obrisiLek/{id}")
	public ResponseEntity<String> obrisiLek(@PathVariable Integer id, @RequestBody String apotekaId) {
		magacinService.obrisiLek(id, Integer.parseInt(apotekaId));
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PostMapping("/dodajLek")
	public ResponseEntity<Collection<Apoteka>> dodajLek() {
		LocalDate pocetakVazenja = LocalDate.now();
		magacinService.dodajLek(pocetakVazenja, 100.0, 200.0, 1, 1);
		return new ResponseEntity<Collection<Apoteka>>(pronadjeneApoteke, HttpStatus.OK);
	}
	
	@PutMapping("/izmeniLek/{apotekaId}")
	public ResponseEntity<String> izmeniLek(@PathVariable Integer apotekaId, @RequestBody LekUMagacinuDTO lek) {
		magacinService.izmeniLekUMagacinu(lek.getCena(), lek.getKolicina(), lek.getId(), apotekaId);
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PutMapping("/preuzmiLek/{id}")
	public ResponseEntity<LekUMagacinuDTO> preuzmiLek(@PathVariable Integer id, @RequestBody String apotekaId) {
		LekUMagacinu lek = magacinService.preuzmiJedanLekApoteke(id, Integer.parseInt(apotekaId));
		LekUMagacinuDTO lekdto= new LekUMagacinuDTO(lek);
		return new ResponseEntity<LekUMagacinuDTO>(lekdto,HttpStatus.OK);
	}
}
