package com.backend.springboot.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.LekUMagacinu;
import com.backend.springboot.domain.Magacin;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.RezervacijaLeka;
import com.backend.springboot.domain.StatusRezervacije;
import com.backend.springboot.dto.MinimalApotekaDTO;
import com.backend.springboot.dto.RezervacijaLekaDTO;
import com.backend.springboot.service.EmailService;
import com.backend.springboot.service.MagacinService;
import com.backend.springboot.service.PacijentService;
import com.backend.springboot.service.RezervacijaService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/rezervacija")
public class RezervacijaController {
	
	
	@Autowired
	private RezervacijaService rezService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private MagacinService magacinService;
	
	@GetMapping("checkOne/{code}")
	public ResponseEntity<String> proveriRezervaciju(@PathVariable String code,@RequestParam Integer apotekaId){
		
		RezervacijaLeka rl=rezService.findOneActiveByCodeAndApoteka(code,apotekaId);
		if(rl==null) {
			return new ResponseEntity<String>("Rezervacija ne postoji.",HttpStatus.OK);
		}
		if(!rl.getDatum().isAfter(LocalDate.now())) {
			rl.setStatus(StatusRezervacije.OTKAZANA);
			Pacijent p=rl.getPacijent();
			if(p.getPenali()<3) {
				p.setPenali(p.getPenali()+1);
				pacijentService.save(p);
			}
			Magacin m=magacinService.findOneByApotekaId(rl.getApoteka().getId());
			LekUMagacinu lek=magacinService.preuzmiJedanLekApoteke(rl.getLek().getId(), rl.getApoteka().getId());
			magacinService.izmeniLekUMagacinu(lek.getCena(), lek.getKolicina()+rl.getKolicina(), rl.getLek().getId(), rl.getApoteka().getId());
			magacinService.save(m);
			try {
				rezService.save(rl);
			}catch(Exception e){
				return new ResponseEntity<String>("Greska.",HttpStatus.OK);
			}
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
		if(rl==null) {
			return new ResponseEntity<String>("Greska",HttpStatus.OK);
		}
		rl.setStatus(StatusRezervacije.PREUZETA);
		try {
			rezService.save(rl);
		}catch(Exception e){
			return new ResponseEntity<String>("Greska.",HttpStatus.OK);
		}
		
		try {
			emailService.preuzimanjeRezervacije(rl);
		} catch (MailException | InterruptedException e) {
			System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
		}
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	
	@GetMapping("/apotekePacijenta/{id}")
	public ResponseEntity<List<MinimalApotekaDTO>> poseceneApoteke(@PathVariable Integer id){
		
		Set<Apoteka> apoteke = rezService.poseceneApoteke(id);
		
		List<MinimalApotekaDTO> minimalne = new ArrayList<MinimalApotekaDTO>();
		for(Apoteka a : apoteke) {
			minimalne.add(new MinimalApotekaDTO(a));
		}
		
		return new ResponseEntity<List<MinimalApotekaDTO>>(minimalne, HttpStatus.OK);
	}
	
	@Scheduled(cron = "${rez.cron}")
	public void checkRezervacije() {
		ArrayList<RezervacijaLeka> rezs=(ArrayList<RezervacijaLeka>) rezService.findAllActive();
		for(RezervacijaLeka rl:rezs) {
			if(!rl.getDatum().isAfter(LocalDate.now()) ) {
				rl.setStatus(StatusRezervacije.OTKAZANA);
				Pacijent p=rl.getPacijent();
				if(p.getPenali()<3) {
					p.setPenali(p.getPenali()+1);
					pacijentService.save(p);
				}
				Magacin m=magacinService.findOneByApotekaId(rl.getApoteka().getId());
				LekUMagacinu lek=magacinService.preuzmiJedanLekApoteke(rl.getLek().getId(), rl.getApoteka().getId());
				magacinService.izmeniLekUMagacinu(lek.getCena(), lek.getKolicina()+rl.getKolicina(), rl.getLek().getId(), rl.getApoteka().getId());
				magacinService.save(m);
				rezService.save(rl);
			}
		}
	}
	
	
	@PostMapping("novaRezervacija")
	public ResponseEntity<String> novaRezervacija(@RequestBody RezervacijaLekaDTO dto ){
		Magacin m = magacinService.findOneByApotekaId(dto.getApotekaId());
		Boolean naStanju = magacinService.proveriStanje(m.getId(), dto.getLekId(), dto.getKolicina());
		if(!naStanju) {
			return new ResponseEntity<String>("Leka nema na stanju u željenoj količini.",HttpStatus.OK);
		}
		rezService.napraviRezervaciju(dto.getLekId(), dto.getPacijentId(), dto.getApotekaId(), dto.getKolicina(), dto.getDatum());
		magacinService.smanjiKolicinuLeka(m.getId(), dto.getLekId(), dto.getKolicina());
		
		return new ResponseEntity<String>("Rezervacija uspešno kreirana.",HttpStatus.OK);
	}
	
	
}
