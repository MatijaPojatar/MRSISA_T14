package com.backend.springboot.controller;

import java.security.Timestamp;
import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.AdministratorApoteke;
import com.backend.springboot.domain.Osoba;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.Pol;
import com.backend.springboot.service.OsobaService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/osobe")
public class OsobaController {
	
	@Autowired
	private OsobaService osobaService;
	
	@GetMapping()
	@PreAuthorize("hasRole('ADMIN_APOTEKE')") //todo promeniti, dodati
	public ResponseEntity<Collection<Osoba>> getOsobe() {
		Collection<Osoba> osobe = osobaService.findAll();
		
		return new ResponseEntity<Collection<Osoba>>(osobe, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Osoba> dodajOsobu(@RequestBody Osoba osobaInfo){
		Osoba osoba = osobaService.addOsoba(osobaInfo);
		
		return new ResponseEntity<Osoba>(osoba, HttpStatus.OK);
	}
	
	@PutMapping("/lozinka/{id}")
	@PreAuthorize("hasAnyRole('ADMIN_APOTEKE','ADMIN_SISTEMA','FARMACEUT','DERMATOLOG','DOBAVLJAC')") 
	public ResponseEntity<String> promeniLozinkuPrviPut(@PathVariable Integer id, @RequestBody String lozinka){
		Osoba osoba = osobaService.findOne(id);
		
		if(osoba.isPromenjenaLozinka()) {
			return new ResponseEntity<String>("Lozinka je vec promenjena", HttpStatus.METHOD_NOT_ALLOWED);
		}
		
		osoba.setPassword(new BCryptPasswordEncoder().encode( lozinka));
		osoba.setPromenjenaLozinka(true);
		
		osobaService.save(osoba);
		return new ResponseEntity<String>("Uspeh", HttpStatus.OK);
	}
	
	@PutMapping("/novaLozinka/{id}")
	@PreAuthorize("hasAnyRole('ADMIN_APOTEKE','ADMIN_SISTEMA','FARMACEUT','DERMATOLOG','DOBAVLJAC','PACIJENT')")
	public ResponseEntity<String> promeniLozinku(@PathVariable Integer id, @RequestBody String lozinka){
		Osoba osoba = osobaService.findOne(id);
		osoba.setPassword(new BCryptPasswordEncoder().encode( lozinka));
		
		osobaService.save(osoba);
		return new ResponseEntity<String>("Uspeh", HttpStatus.OK);
	}
	
	@GetMapping("/novaLozinka/{id}")
	@PreAuthorize("hasAnyRole('ADMIN_APOTEKE','ADMIN_SISTEMA','FARMACEUT','DERMATOLOG','DOBAVLJAC','PACIJENT')")
	public ResponseEntity<Boolean> proveriStaruLozinku(@PathVariable Integer id,@RequestParam String lozinka){
		Osoba osoba = osobaService.findOne(id);
		if(new BCryptPasswordEncoder().matches(lozinka, osoba.getPassword())) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}
		
		return new ResponseEntity<Boolean>(false,HttpStatus.NOT_MODIFIED);
	}
	
}
	

	

