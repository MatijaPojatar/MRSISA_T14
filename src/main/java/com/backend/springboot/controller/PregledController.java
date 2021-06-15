package com.backend.springboot.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.LekUIzvestaju;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.Pregled;
import com.backend.springboot.dto.DermatologDTO;
import com.backend.springboot.dto.IzvestajDTO;
import com.backend.springboot.dto.LekUIzvestajuDTO;
import com.backend.springboot.dto.MinimalApotekaDTO;
import com.backend.springboot.dto.PacijentTerminDTO;
import com.backend.springboot.dto.PregledDTO;
import com.backend.springboot.dto.PregledDermatologDTO;
import com.backend.springboot.service.EmailService;
import com.backend.springboot.service.LekService;
import com.backend.springboot.service.LekUIzvestajuService;
import com.backend.springboot.service.PacijentService;
import com.backend.springboot.service.PregledService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping(value = "/pregled")
public class PregledController {
	
	@Autowired
	private PregledService service;
	@Autowired
	private LekUIzvestajuService izvestajService;
	@Autowired
	private LekService lekService;
	@Autowired
	private PacijentService pacijentService;
	@Autowired
	private EmailService emailService;
	
	@GetMapping(value = "/pacijent/{id}")
	@PreAuthorize("hasAnyRole('DERMATOLOG','PACIJENT')")
	public ResponseEntity<List<PregledDTO>> getAllForPacijent(@PathVariable Integer id) {		
		List<Pregled> pregledi = service.findAllByPacijentId(id);

		List<PregledDTO> preglediDTO = new ArrayList<>();
		for (Pregled p : pregledi) {
			if (LocalDateTime.now().compareTo(p.getPocetak()) <= 0)
				preglediDTO.add(new PregledDTO(p));
		}

		return new ResponseEntity<>(preglediDTO, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('PACIJENT')")
	@GetMapping("/apotekePacijenta/{id}")
	public ResponseEntity<List<MinimalApotekaDTO>> poseceneApoteke(@PathVariable Integer id){
		
		Set<Apoteka> apoteke = service.poseceneApoteke(id);
		
		List<MinimalApotekaDTO> minimalne = new ArrayList<MinimalApotekaDTO>();
		for(Apoteka a : apoteke) {
			minimalne.add(new MinimalApotekaDTO(a));
		}
		
		return new ResponseEntity<List<MinimalApotekaDTO>>(minimalne, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('PACIJENT')")
	@GetMapping("/dermatoloziPacijenta/{id}")
	public ResponseEntity<List<DermatologDTO>> poseceniDermatolozi(@PathVariable Integer id){
		
		Set<Dermatolog> dermatolozi = service.poseceniDermatolozi(id);
		
		List<DermatologDTO> dtos = new ArrayList<DermatologDTO>();
		
		for (Dermatolog f : dermatolozi) {
			dtos.add(new DermatologDTO(f));
		}
		
		return new ResponseEntity<List<DermatologDTO>>(dtos, HttpStatus.OK);
	}
	
	
	@PutMapping("/penal/{id}")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<String> pacijentNijeDosao(@PathVariable Integer id){
		Pregled p=service.findOne(id);
		p.setIzvrsen(true);
		p.setIzvestaj("Pacijent se nije pojavio");
		
		try {
			service.save(p);
		}catch(Exception e){
			return new ResponseEntity<String>("Greska",HttpStatus.NOT_ACCEPTABLE);
		}
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PutMapping("/izvestaj/{id}")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<String> sacuvajIzvestaj(@PathVariable Integer id,@RequestBody IzvestajDTO izvestaj){
		Pregled p=service.findOne(id);
		p.setIzvrsen(true);
		p.setIzvestaj(izvestaj.getText());
		for(LekUIzvestajuDTO lekDto: izvestaj.getLekovi()) {
			Lek l=lekService.findOneById(lekDto.getLekId());
			LekUIzvestaju lekIz=new LekUIzvestaju( l, lekDto.getTerapija(),p);
			izvestajService.addLek(lekIz);
			p.getLekovi().add(lekIz);
		}
		
		try {
			service.save(p);
		}catch(Exception e){
			return new ResponseEntity<String>("Greska",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/{id}")
	@PreAuthorize("hasAnyRole('DERMATOLOG','PACIJENT')")
	public ResponseEntity<List<PregledDTO>> getAllForDermatolog(@PathVariable Integer id) {

		List<Pregled> pregledi = service.findAllByDermatologId(id);

		List<PregledDTO> preglediDTO = new ArrayList<>();
		for (Pregled p : pregledi) {
			preglediDTO.add(new PregledDTO(p));
		}

		return new ResponseEntity<>(preglediDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/active/{id}")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<List<PregledDTO>> getAllActiveForDermatolog(@PathVariable Integer id,@RequestParam Integer apotekaId) {		

		LocalDateTime pocetak=LocalDateTime.now();
		List<Pregled> pregledi = service.findAllActive(id, apotekaId, pocetak);
		

		List<PregledDTO> preglediDTO = new ArrayList<>();
		for (Pregled p : pregledi) {
			preglediDTO.add(new PregledDTO(p));
		}

		return new ResponseEntity<>(preglediDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/slobodni/{apotekaId}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<List<PregledDermatologDTO>> slobodniPregledi(@PathVariable Integer apotekaId) {		
		LocalDateTime pocetak = LocalDateTime.now();
		List<Pregled> pregledi = service.findAllSlobodni(apotekaId, pocetak);

		List<PregledDermatologDTO> preglediDTO = new ArrayList<>();
		for (Pregled p : pregledi) {
			preglediDTO.add(new PregledDermatologDTO(p));
		}

		return new ResponseEntity<List<PregledDermatologDTO>>(preglediDTO, HttpStatus.OK);
	}
	
	@PutMapping("/zauzmi/{id}/{pacijentId}")
	@PreAuthorize("hasAnyRole('DERMATOLOG', 'PACIJENT')")
	public ResponseEntity<String> zauzmiTermin(@PathVariable Integer id,@PathVariable Integer pacijentId){
		Pacijent p=pacijentService.findOne(pacijentId);
		try {
			service.zauzmiPregled(id, p);
		}catch(Exception e) {
			Thread.currentThread().interrupt();
			return new ResponseEntity<String>("Greska",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PutMapping("/dodaj/{id}")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<Boolean> dodajTermin(@PathVariable Integer id, @RequestBody PregledDTO pregled) throws InterruptedException{
		LocalDateTime pocetak=pregled.getStart();
		LocalDateTime kraj=pregled.getEnd();

		boolean odg=service.dodajPregled(id, pocetak, kraj, pregled);
		
		if(odg) {
			try {
				emailService.noviPregled(pregled);
			} catch(Exception e){
				Thread.currentThread().interrupt();
				System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
			}
		}
		
		if(odg) {
			return new ResponseEntity<Boolean>(odg,HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(odg,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/zakazi/{pacijentId}/{pregledId}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<String> zakaziPregled(@PathVariable Integer pacijentId, @PathVariable Integer pregledId) throws InterruptedException{
		try {
			zauzmiTermin(pregledId, pacijentId);
		}catch(Exception e) {
			return new ResponseEntity<String>("Greska", HttpStatus.BAD_REQUEST);
		}
		
		PregledDTO pregled = new PregledDTO(service.findOne(pregledId));

		boolean odg = service.izmeniPregled(pregled);
		
		if(odg) {
			try {
				emailService.noviPregled(pregled);
			} catch(Exception e){
				System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
			}
		}
		
		if(odg) {
			return new ResponseEntity<String>("Uspeh", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Greska", HttpStatus.OK);
		}
	}
	
	@PutMapping("/dodajSlobodan/{id}")
	@PreAuthorize("hasAnyRole('DERMATOLOG','ADMIN_APOTEKE')")
	public ResponseEntity<Boolean> dodajSlobodanTermin(@PathVariable Integer id,@RequestBody PregledDTO pregled){
		LocalDateTime pocetak=pregled.getStart();
		LocalDateTime kraj=pregled.getEnd();
		
		boolean odg=service.dodajSlobodanPregled(id, pocetak, kraj, pregled);;
		
		if(odg) {
			return new ResponseEntity<Boolean>(odg,HttpStatus.OK);
		}else {
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(value = "/all/pacijenti/{id}")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<List<PacijentTerminDTO>> getAllPacijentiForDermatolog(@PathVariable Integer id) {	
		
		System.out.println("==================================================");

		List<Pregled> pregledi = service.findAllByDermatologId(id);
		
		List<Pacijent> pacijenti = new ArrayList<Pacijent>();
		
		HashMap<Integer, LocalDateTime> termini= new HashMap<Integer, LocalDateTime>();

		for (Pregled p :pregledi) {
			if(!p.isIzvrsen()) {
				continue;
			}
			if(!pacijenti.contains(p.getPacijent())) {
				pacijenti.add(p.getPacijent());
				termini.put(p.getPacijent().getId(), p.getPocetak());
			}else {
				if(termini.get(p.getPacijent().getId()).isBefore(p.getPocetak())) {
					termini.replace(p.getPacijent().getId(), p.getPocetak());
				}
			}
		}
		
		List<PacijentTerminDTO> pacijentiDTO=new ArrayList<PacijentTerminDTO>();
		
		for(Pacijent p:pacijenti) {
			pacijentiDTO.add(new PacijentTerminDTO(p, termini.get(p.getId())));
		}

		return new ResponseEntity<List<PacijentTerminDTO>>(pacijentiDTO, HttpStatus.OK);
	}
	
	@GetMapping("/preporuceni_lekovi/{id}")
	@PreAuthorize("hasAnyRole('DERMATOLOG','PACIJENT')")
	public ResponseEntity<List<LekUIzvestajuDTO>> getPreporuceni(@PathVariable Integer id){
		List<LekUIzvestaju> lekovi=izvestajService.findAllByTerminId(id);
		ArrayList<LekUIzvestajuDTO> dtos=new ArrayList<LekUIzvestajuDTO>();
		for(LekUIzvestaju lek:lekovi) {
			dtos.add(new LekUIzvestajuDTO(lek));
		}
		
		return new ResponseEntity<List<LekUIzvestajuDTO>>(dtos,HttpStatus.OK);
	}
	
	@GetMapping(value = "/slobodni")
	@PreAuthorize("hasRole('DERMATOLOG')")
	public ResponseEntity<List<PregledDTO>> getSlobodni() {		
		LocalDateTime pocetak = LocalDateTime.now();
		List<Pregled> pregledi = service.findAllActive(pocetak);

		List<PregledDTO> dtos = new ArrayList<>();
		for (Pregled p : pregledi) {
			dtos.add(new PregledDTO(p));
		}

		return new ResponseEntity<List<PregledDTO>>(dtos, HttpStatus.OK);
	}
	
	@PutMapping("otkazi/{id}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<String> otkaziPregled(@PathVariable Integer id){
		Pregled p = service.findOne(id);
		
		if(p == null) {
			return new ResponseEntity<String>("Zakazani pregled ne postoji", HttpStatus.NOT_FOUND);
		}
		
		p.setPacijent(null);
		try {
			service.save(p);
		} catch(Exception e){
			return new ResponseEntity<String>("Greska", HttpStatus.LOCKED);
		}
		
		return new ResponseEntity<String>("Uspeh", HttpStatus.OK);
	}
}
