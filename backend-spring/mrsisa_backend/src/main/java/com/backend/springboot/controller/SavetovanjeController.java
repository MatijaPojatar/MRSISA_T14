package com.backend.springboot.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.LekUIzvestaju;
import com.backend.springboot.domain.OdsustvoFarmaceut;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.dto.FarmaceutDTO;
import com.backend.springboot.dto.IzvestajDTO;
import com.backend.springboot.dto.LekUIzvestajuDTO;
import com.backend.springboot.dto.MinimalApotekaDTO;
import com.backend.springboot.dto.PacijentTerminDTO;
import com.backend.springboot.dto.SavetovanjeDTO;
import com.backend.springboot.service.ApotekaService;
import com.backend.springboot.service.EmailService;
import com.backend.springboot.service.FarmaceutService;
import com.backend.springboot.service.LekService;
import com.backend.springboot.service.LekUIzvestajuService;
import com.backend.springboot.service.OdsustvoFarmaceutService;
import com.backend.springboot.service.PacijentService;
import com.backend.springboot.service.SavetovanjeService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping(value = "/savetovanje")
public class SavetovanjeController {
	
	@Autowired
	private SavetovanjeService service;
	@Autowired
	private FarmaceutService farmService;
	@Autowired
	private LekService lekService;
	@Autowired
	private ApotekaService apotekaService;
	@Autowired
	private LekUIzvestajuService izvestajService;
	@Autowired
	private PacijentService pacijentService;
	@Autowired
	private OdsustvoFarmaceutService odsustvoService;
	@Autowired
	private EmailService emailService;
		
	@GetMapping(value = "/all")
	public ResponseEntity<List<SavetovanjeDTO>> getAll() {		

		List<Savetovanje> savetovanja = service.findAllByFarmaceutId(1);
		
		System.out.println(savetovanja.size());

		List<SavetovanjeDTO> savetovanjaDTO = new ArrayList<>();
		for (Savetovanje s : savetovanja) {
			savetovanjaDTO.add(new SavetovanjeDTO(s));
		}

		return new ResponseEntity<>(savetovanjaDTO, HttpStatus.OK);
	}
	
	@GetMapping("/apotekePacijenta/{id}")
	public ResponseEntity<List<MinimalApotekaDTO>> poseceneApoteke(@PathVariable Integer id){
		
		Set<Apoteka> apoteke = service.poseceneApoteke(id);
		
		List<MinimalApotekaDTO> minimalne = new ArrayList<MinimalApotekaDTO>();
		for(Apoteka a : apoteke) {
			minimalne.add(new MinimalApotekaDTO(a));
		}
		
		return new ResponseEntity<List<MinimalApotekaDTO>>(minimalne, HttpStatus.OK);
	}
	
	@GetMapping("/farmaceutiPacijenta/{id}")
	public ResponseEntity<List<FarmaceutDTO>> poseceniFarmaceuti(@PathVariable Integer id){
		Set<Farmaceut> farmaceuti = service.poseceniFarmaceuti(id);
		List<FarmaceutDTO> dtos = new ArrayList<FarmaceutDTO>();
		
		for (Farmaceut f : farmaceuti) {
			dtos.add(new FarmaceutDTO(f));
		}
		
		return new ResponseEntity<List<FarmaceutDTO>>(dtos, HttpStatus.OK);
	}
	
	
	@PutMapping("/penal/{id}")
	public ResponseEntity<String> pacijentNijeDosao(@PathVariable Integer id){
		Savetovanje s=service.findOne(id);
		s.setIzvrsen(true);
		s.setIzvestaj("Pacijent se nije pojavio");
		
		service.save(s);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PutMapping("/izvestaj/{id}")
	public ResponseEntity<String> sacuvajIzvestaj(@PathVariable Integer id,@RequestBody IzvestajDTO izvestaj){
		Savetovanje s=service.findOne(id);
		s.setIzvrsen(true);
		s.setIzvestaj(izvestaj.getText());
		for(LekUIzvestajuDTO lekDto: izvestaj.getLekovi()) {
			Lek l=lekService.findOneById(lekDto.getLekId());
			System.out.println("========================"+lekDto.getLekId()+"==============================");
			LekUIzvestaju lekIz=new LekUIzvestaju( l, lekDto.getTerapija(),s);
			izvestajService.addLek(lekIz);
			s.getLekovi().add(lekIz);
		}
		
		service.save(s);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/{id}")
	public ResponseEntity<List<SavetovanjeDTO>> getAllForFarmaceut(@PathVariable Integer id) {		

		List<Savetovanje> savetovanja = service.findAllByFarmaceutId(id);
		
		System.out.println(savetovanja.size());

		List<SavetovanjeDTO> savetovanjaDTO = new ArrayList<>();
		for (Savetovanje s : savetovanja) {
			savetovanjaDTO.add(new SavetovanjeDTO(s));
		}

		return new ResponseEntity<>(savetovanjaDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/active/{id}")
	public ResponseEntity<List<SavetovanjeDTO>> getAllActiveForFarmaceut(@PathVariable Integer id,@RequestParam Integer apotekaId) {		

		LocalDateTime pocetak=LocalDateTime.now();
		List<Savetovanje> savetovanja = service.findAllActive(id, apotekaId, pocetak);
		
		System.out.println(savetovanja.size());

		List<SavetovanjeDTO> savetovanjaDTO = new ArrayList<>();
		for (Savetovanje s : savetovanja) {
			savetovanjaDTO.add(new SavetovanjeDTO(s));
		}

		return new ResponseEntity<>(savetovanjaDTO, HttpStatus.OK);
	}
	
	@PutMapping("/zauzmi/{id}/{pacijentId}")
	public ResponseEntity<String> zauzmiTermin(@PathVariable Integer id,@PathVariable Integer pacijentId){
		Savetovanje s=service.findOne(id);
		Pacijent p=pacijentService.findOne(pacijentId);
		s.setPacijent(p);
		service.save(s);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PutMapping("/dodaj/{id}")
	public ResponseEntity<Boolean> dodajTermin(@PathVariable Integer id,@RequestBody SavetovanjeDTO savetovanje){
		LocalDateTime pocetak=savetovanje.getStart().plusHours(2);
		LocalDateTime kraj=savetovanje.getEnd().plusHours(2);
		
		List<Savetovanje> checkList=service.findAllInRange(pocetak,kraj);
		if(checkList.size()!=0) {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		
		List<OdsustvoFarmaceut> checkOdsustva=odsustvoService.findExistInTime(pocetak, kraj);
		if(checkOdsustva.size()!=0) {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		
		Savetovanje s=new Savetovanje();
		s.setFarmaceut(farmService.findOne(id));
		s.setIzvrsen(savetovanje.isIzvrsen());
		s.setApoteka(apotekaService.findOne(savetovanje.getApotekaId()));
		s.setPacijent(pacijentService.findOne(savetovanje.getPacijentId()));
		s.setIzvestaj(savetovanje.getIzvestaj());
		s.setKraj(kraj);
		s.setPocetak(pocetak);
		
		service.save(s);
		
		try {
			emailService.novoSavetovanje(s);
		} catch(Exception e){
			System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
		}
		
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/pacijenti/{id}")
	public ResponseEntity<List<PacijentTerminDTO>> getAllPacijentiForFarmaceut(@PathVariable Integer id) {	
		
		System.out.println("==================================================");

		List<Savetovanje> savetovanja = service.findAllByFarmaceutId(id);
		
		List<Pacijent> pacijenti = new ArrayList<Pacijent>();
		
		HashMap<Integer, LocalDateTime> termini= new HashMap<Integer, LocalDateTime>();

		for (Savetovanje s : savetovanja) {
			if(!s.isIzvrsen()) {
				continue;
			}
			if(!pacijenti.contains(s.getPacijent())) {
				pacijenti.add(s.getPacijent());
				termini.put(s.getPacijent().getId(), s.getPocetak());
			}else {
				if(termini.get(s.getPacijent().getId()).isBefore(s.getPocetak())) {
					termini.replace(s.getPacijent().getId(), s.getPocetak());
				}
			}
		}
		
		List<PacijentTerminDTO> pacijentiDTO=new ArrayList<PacijentTerminDTO>();
		
		for(Pacijent p:pacijenti) {
			pacijentiDTO.add(new PacijentTerminDTO(p, termini.get(p.getId())));
		}

		return new ResponseEntity<List<PacijentTerminDTO>>(pacijentiDTO, HttpStatus.OK);
	}
}
