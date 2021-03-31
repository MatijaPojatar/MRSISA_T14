package com.backend.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.service.PacijentService;


@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/pacijenti")
public class PacijentController {

	@Autowired
	private PacijentService pacijentService;
	
//	int id, String ime, String prezime, String mail, String password, String adresa, String grad,
//	String drzava, String brojTelefona, Pol pol, LocalDate datumRodjenja, boolean promenjenaLozinka, int brojPoena, int penali
	
	
	
	
	@PostMapping()
	public ResponseEntity<Pacijent> registrujPacijenta(@RequestBody Pacijent pacijentInfo){
		Pacijent pacijent = pacijentService.registrujPacijenta(pacijentInfo);
		
		return new ResponseEntity<Pacijent>(pacijent, HttpStatus.OK);
	}
	
}
