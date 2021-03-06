package com.backend.springboot.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.LekUIzvestaju;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.domain.TipStavkeBodovanja;
import com.backend.springboot.dto.FarmaceutDTO;
import com.backend.springboot.dto.IzvestajDTO;
import com.backend.springboot.dto.LekUIzvestajuDTO;
import com.backend.springboot.dto.MinimalApotekaDTO;
import com.backend.springboot.dto.PacijentTerminDTO;
import com.backend.springboot.dto.SavetovanjeDTO;
import com.backend.springboot.service.EmailService;
import com.backend.springboot.service.LekService;
import com.backend.springboot.service.LekUIzvestajuService;
import com.backend.springboot.service.PacijentService;
import com.backend.springboot.service.SavetovanjeService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping(value = "/savetovanje")
public class SavetovanjeController {
	@Autowired
	private SavetovanjeService service;
	
	@Autowired
	private PacijentService pacService;
	
	@Autowired
	private LekService lekService;
	@Autowired
	private LekUIzvestajuService izvestajService;
	@Autowired
	private EmailService emailService;
	
	@DeleteMapping("otkazi/{id}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<Object> deleteSavetovanje(@PathVariable("id") int id) {
		try {
			service.deleteSavetovanje(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(EmptyResultDataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
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
	@GetMapping("/farmaceutiPacijenta/{id}")
	public ResponseEntity<List<FarmaceutDTO>> poseceniFarmaceuti(@PathVariable Integer id){
		Set<Farmaceut> farmaceuti = service.poseceniFarmaceuti(id);
		List<FarmaceutDTO> dtos = new ArrayList<FarmaceutDTO>();
		
		for (Farmaceut f : farmaceuti) {
			dtos.add(new FarmaceutDTO(f));
		}

		List<FarmaceutDTO> dtoBezDuplikata = new ArrayList<FarmaceutDTO>();
		
		for (FarmaceutDTO dto : dtos) {
            if (!dtoBezDuplikata.contains(dto)) {
            	dtoBezDuplikata.add(dto);
            }
        }

		return new ResponseEntity<List<FarmaceutDTO>>(dtoBezDuplikata, HttpStatus.OK);
	}
	
	@PutMapping("/penal/{id}")
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<String> pacijentNijeDosao(@PathVariable Integer id){
		Savetovanje s=service.findOne(id);
		s.setIzvrsen(true);
		s.setIzvestaj("Pacijent se nije pojavio");
		
		try {
			service.save(s);
		}catch(Exception e){
			return new ResponseEntity<String>("Greska",HttpStatus.LOCKED);
		}
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PutMapping("/izvestaj/{id}")
	@PreAuthorize("hasRole('FARMACEUT')")
	public ResponseEntity<String> sacuvajIzvestaj(@PathVariable Integer id,@RequestBody IzvestajDTO izvestaj){
		Savetovanje s=service.findOne(id);
		
		if(s.getPacijent() != null) {
			pacService.dodajBodove(s.getPacijent().getId(), TipStavkeBodovanja.SAVETOVANJE, null);
		}
		
		
		s.setIzvrsen(true);
		s.setIzvestaj(izvestaj.getText());
		for(LekUIzvestajuDTO lekDto: izvestaj.getLekovi()) {
			Lek l=lekService.findOneById(lekDto.getLekId());
			System.out.println("========================"+lekDto.getLekId()+"==============================");
			LekUIzvestaju lekIz=new LekUIzvestaju( l, lekDto.getTerapija(),s);
			izvestajService.addLek(lekIz);
			s.getLekovi().add(lekIz);
		}
		
		try {
			service.save(s);
		}catch(Exception e){
			return new ResponseEntity<String>("Greska",HttpStatus.LOCKED);
		}
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/{id}")
	@PreAuthorize("hasAnyRole('FARMACEUT','PACIJENT')")
	public ResponseEntity<List<SavetovanjeDTO>> getAllForFarmaceut(@PathVariable Integer id) {		

		List<Savetovanje> savetovanja = service.findAllByFarmaceutId(id);
		
		System.out.println(savetovanja.size());

		List<SavetovanjeDTO> savetovanjaDTO = new ArrayList<>();
		for (Savetovanje s : savetovanja) {
			savetovanjaDTO.add(new SavetovanjeDTO(s));
		}

		return new ResponseEntity<>(savetovanjaDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/pacijent/{id}")
	@PreAuthorize("hasAnyRole('FARMACEUT','PACIJENT')")
	public ResponseEntity<List<SavetovanjeDTO>> getAllForPacijent(@PathVariable Integer id) {		
		List<Savetovanje> savetovanja = service.findAllByPacijentId(id);

		List<SavetovanjeDTO> savetovanjaDTO = new ArrayList<>();
		for (Savetovanje s : savetovanja) {
			if (LocalDateTime.now().compareTo(s.getPocetak()) <= 0)
				savetovanjaDTO.add(new SavetovanjeDTO(s));
		}

		return new ResponseEntity<>(savetovanjaDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/istorija/pacijent/{id}")
	@PreAuthorize("hasAnyRole('FARMACEUT','DERMATOLOG','PACIJENT')")
	public ResponseEntity<List<SavetovanjeDTO>> getAllIstorijaForPacijent(@PathVariable Integer id) {		
		List<Savetovanje> savetovanja = service.findAllByPacijentId(id);

		List<SavetovanjeDTO> savetovanjaDTO = new ArrayList<>();
		for (Savetovanje s : savetovanja) {
			if (LocalDateTime.now().compareTo(s.getPocetak()) > 0)
				savetovanjaDTO.add(new SavetovanjeDTO(s));
		}

		return new ResponseEntity<>(savetovanjaDTO, HttpStatus.OK);
	}
	
	/*@PutMapping("/zauzmi/{id}/{pacijentId}")
	public ResponseEntity<String> zauzmiTermin(@PathVariable Integer id,@PathVariable Integer pacijentId){
		Savetovanje s=service.findOne(id);
		Pacijent p=pacijentService.findOne(pacijentId);
		s.setPacijent(p);
		service.save(s);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}*/
	
	@PutMapping("/dodaj/{id}")
	@PreAuthorize("hasAnyRole('FARMACEUT','PACIJENT')")
	public ResponseEntity<Boolean> dodajTermin(@PathVariable Integer id,@RequestBody SavetovanjeDTO savetovanje) throws InterruptedException{
		LocalDateTime pocetak=savetovanje.getStart();
		LocalDateTime kraj=savetovanje.getEnd();
		
		boolean odg=service.dodajSavetovanje(id, pocetak, kraj, savetovanje);
		
		if(odg) {
			try {
				emailService.novoSavetovanje(savetovanje);
			} catch(Exception e){
				Thread.currentThread().interrupt();
				System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
			}
		}
		
		if(odg) {
			return new ResponseEntity<Boolean>(odg,HttpStatus.OK);
		}else {
			return new ResponseEntity<Boolean>(odg,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/all/pacijenti/{id}")
	@PreAuthorize("hasRole('FARMACEUT')")
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
	
	@GetMapping("/preporuceni_lekovi/{id}")
	@PreAuthorize("hasAnyRole('FARMACEUT','PACIJENT')")
	public ResponseEntity<List<LekUIzvestajuDTO>> getPreporuceni(@PathVariable Integer id){
		List<LekUIzvestaju> lekovi=izvestajService.findAllByTerminId(id);
		ArrayList<LekUIzvestajuDTO> dtos=new ArrayList<LekUIzvestajuDTO>();
		for(LekUIzvestaju lek:lekovi) {
			dtos.add(new LekUIzvestajuDTO(lek));
		}
		
		return new ResponseEntity<List<LekUIzvestajuDTO>>(dtos,HttpStatus.OK);
	}
}
