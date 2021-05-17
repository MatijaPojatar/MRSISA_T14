package com.backend.springboot.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.ParametriPretrageRezervacije;
import com.backend.springboot.domain.RezervacijaLeka;
import com.backend.springboot.domain.StatusRezervacije;
import com.backend.springboot.dto.RezervacijaLekaDTO;
import com.backend.springboot.service.EmailService;
import com.backend.springboot.service.RezervacijaService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/rezervacija")
public class RezervacijaController {
	
	@Autowired
	private RezervacijaService rezService;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("checkOne/{code}")
	public ResponseEntity<String> proveriRezervaciju(@PathVariable String code,@RequestParam Integer apotekaId){
		
		RezervacijaLeka rl=rezService.findOneActiveByCodeAndApoteka(code,apotekaId);
		if(rl==null) {
			return new ResponseEntity<String>("Rezervacija ne postoji.",HttpStatus.OK);
		}
		if(!rl.getDatum().isAfter(LocalDate.now())) {
			rl.setStatus(StatusRezervacije.OTKAZANA);
			rezService.save(rl);
			return new ResponseEntity<String>("Rezervacija je istekla.",HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@GetMapping("findOne/{code}")
	public ResponseEntity<RezervacijaLekaDTO> pronadjiRezervaciju(@PathVariable String code){
		
		RezervacijaLeka rl=rezService.findOneActiveByCode(code);
		RezervacijaLekaDTO dto=new RezervacijaLekaDTO(rl);
		
		return new ResponseEntity<RezervacijaLekaDTO>(dto,HttpStatus.OK);
	}
	
	@PutMapping("preuzmi/{code}")
	public ResponseEntity<String> preuzmiLek(@PathVariable String code){
		
		RezervacijaLeka rl=rezService.findOneActiveByCode(code);
		rl.setStatus(StatusRezervacije.PREUZETA);
		rezService.save(rl);
		
		try {
			emailService.preuzimanjeRezervacije(rl);
		} catch (MailException | InterruptedException e) {
			System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
		}
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
}
