package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.AkcijaPromocija;
import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.LekIzNarudzbenice;
import com.backend.springboot.dto.AkcijaPromocijaDTO;
import com.backend.springboot.dto.NovaNarudzbenicaDTO;
import com.backend.springboot.service.AkcijaPromocijaService;
import com.backend.springboot.service.ApotekaService;
import com.backend.springboot.service.EmailService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/akcijaPromocija")
public class AkcijaPromocijaController {
	@Autowired
	private AkcijaPromocijaService apService;
	
	@Autowired
	private ApotekaService apotekaService;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/pacijent/{id}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<AkcijaPromocijaDTO>> getAPByPacijent(@PathVariable Integer id){
		List<AkcijaPromocija> akcijePromocije = apService.findAllByPacijent(id);
		
		List<AkcijaPromocijaDTO> dtos = new ArrayList<AkcijaPromocijaDTO>();
		for(AkcijaPromocija ap : akcijePromocije) {
			dtos.add(new AkcijaPromocijaDTO(ap));
		}

		return new ResponseEntity<List<AkcijaPromocijaDTO>>(dtos, HttpStatus.OK);	
	}
	
	@PreAuthorize("hasRole('ADMIN_APOTEKE')")
	@PostMapping("/dodaj/{apotekaId}")
	public ResponseEntity<Boolean> dodajAkcijuPromociju(@PathVariable Integer apotekaId, @RequestBody AkcijaPromocijaDTO apDTO){
		AkcijaPromocija akcija = apService.dodaj(apotekaId, apDTO.getOpis(), apDTO.getKraj());
		
		Apoteka apoteka = apotekaService.findOne(apotekaId);
		apoteka.getPretplaceniPacijenti().stream().forEach(pacijent ->{
			try {
			emailService.novaAkcija(apoteka.getNaziv(), akcija, pacijent);
		} catch (MailException | InterruptedException e) {
			System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
		}
			});
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
	}
}
