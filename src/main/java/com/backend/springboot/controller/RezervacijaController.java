package com.backend.springboot.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.backend.springboot.dto.RezLekaPacijentDTO;
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
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<String> proveriRezervaciju(@PathVariable String code,@RequestParam Integer apotekaId){
		
		RezervacijaLeka rl=rezService.findOneActiveByCodeAndApoteka(code,apotekaId);
		if(rl==null) {
			return new ResponseEntity<String>("Rezervacija ne postoji.",HttpStatus.NOT_FOUND);
		}
		if(!rl.getDatum().isAfter(LocalDate.now())) {
			boolean uspeh = magacinService.vratiLekUMagacin(rl);
			if(!uspeh) {
				return new ResponseEntity<String>("Greska.",HttpStatus.LOCKED);
			}
			rl.setStatus(StatusRezervacije.OTKAZANA);
			Pacijent p=rl.getPacijent();
			if(p.getPenali()<3) {
				p.setPenali(p.getPenali()+1);
				pacijentService.save(p);
			}
			try {
				rezService.save(rl);
			}catch(Exception e){
				return new ResponseEntity<String>("Greska.",HttpStatus.LOCKED);
			}
			return new ResponseEntity<String>("Rezervacija je istekla.",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@GetMapping("otkazi/{code}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<String> otkaziRezervaciju(@PathVariable String code,@RequestParam Integer apotekaId){
		
		RezervacijaLeka rl=rezService.findOneActiveByCodeAndApoteka(code,apotekaId);
		if(rl==null) {
			return new ResponseEntity<String>("Rezervacija ne postoji.",HttpStatus.NOT_FOUND);
		}
		boolean uspeh = magacinService.vratiLekUMagacin(rl);
		if(!uspeh) {
			return new ResponseEntity<String>("Greska.",HttpStatus.LOCKED);
		}
		rl.setStatus(StatusRezervacije.OTKAZANA);
		Pacijent p=rl.getPacijent();
		if(p.getPenali()<3) {
			p.setPenali(p.getPenali()+1);
			pacijentService.save(p);
		}
		
		try {
			rezService.save(rl);
		}catch(Exception e){
			return new ResponseEntity<String>("Greska.",HttpStatus.LOCKED);
		}
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@GetMapping("findOne/{code}")
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<RezervacijaLekaDTO> pronadjiRezervaciju(@PathVariable String code){
		
		RezervacijaLeka rl=rezService.findOneActiveByCode(code);
		RezervacijaLekaDTO dto=new RezervacijaLekaDTO(rl);
		
		return new ResponseEntity<RezervacijaLekaDTO>(dto,HttpStatus.OK);
	}
	
	@PutMapping("preuzmi/{code}")
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<String> preuzmiLek(@PathVariable String code){
		
		RezervacijaLeka rl=rezService.findOneActiveByCode(code);
		if(rl==null) {
			return new ResponseEntity<String>("Greska",HttpStatus.BAD_REQUEST);
		}
		rl.setStatus(StatusRezervacije.PREUZETA);
		try {
			rezService.save(rl);
		}catch(Exception e){
			return new ResponseEntity<String>("Greska.",HttpStatus.LOCKED);
		}
		
		try {
			emailService.preuzimanjeRezervacije(rl);
		} catch (MailException | InterruptedException e) {
			System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
		}
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('PACIJENT')")
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
				boolean uspeh = magacinService.vratiLekUMagacin(rl);
				if(uspeh) {
					rl.setStatus(StatusRezervacije.OTKAZANA);
					Pacijent p=rl.getPacijent();
					if(p.getPenali()<3) {
						p.setPenali(p.getPenali()+1);
						pacijentService.save(p);
					}
				}
			}
		}
	}
	
	@PostMapping("novaRezervacija")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<String> novaRezervacija(@RequestBody RezervacijaLekaDTO dto ) throws MailException, InterruptedException{
		Magacin m = magacinService.findOneByApotekaId(dto.getApotekaId());
		Boolean naStanju = magacinService.proveriStanje(m.getId(), dto.getLekId(), dto.getKolicina());
		if(!naStanju) {
			return new ResponseEntity<String>("Leka nema na stanju u željenoj količini.",HttpStatus.OK);
		}
		RezervacijaLeka rl = rezService.napraviRezervaciju(dto.getLekId(), dto.getPacijentId(), dto.getApotekaId(), dto.getKolicina(), dto.getDatum());
		emailService.rezervacijaLeka(rl);
		magacinService.smanjiKolicinuLeka(m.getId(), dto.getLekId(), dto.getKolicina());
		
		return new ResponseEntity<String>("Rezervacija uspešno kreirana.",HttpStatus.OK);
	}
	
	@GetMapping("pacijent/{id}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<RezLekaPacijentDTO>> aktivneRezPacijenta(@PathVariable Integer id){
		List<RezervacijaLeka> rezervacije = rezService.rezervacijePacijenta(id, StatusRezervacije.KREIRANA);
		List<RezLekaPacijentDTO> dtos = new ArrayList<RezLekaPacijentDTO>();
		
		for (RezervacijaLeka r : rezervacije) {
			dtos.add(new RezLekaPacijentDTO(r));
		}
		
		return new ResponseEntity<List<RezLekaPacijentDTO>>(dtos, HttpStatus.OK);
	}
}
