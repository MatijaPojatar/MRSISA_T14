package com.backend.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.dto.PacijentDTO;
import com.backend.springboot.service.PacijentService;


@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/pacijent")
public class PacijentController {

	@Autowired
	private PacijentService pacijentService;
	
//	int id, String ime, String prezime, String mail, String password, String adresa, String grad,
//	String drzava, String brojTelefona, Pol pol, LocalDate datumRodjenja, boolean promenjenaLozinka, int brojPoena, int penali
	
	
	@GetMapping("/{id}")
	public ResponseEntity<PacijentDTO> getOne(@PathVariable Integer id){
		Pacijent p = pacijentService.findOne(id);
		PacijentDTO dto = new PacijentDTO(p);
		
		return new ResponseEntity<PacijentDTO>(dto, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Pacijent> registrujPacijenta(@RequestBody Pacijent pacijentInfo){
		Pacijent pacijent = pacijentService.save(pacijentInfo);
		
		return new ResponseEntity<Pacijent>(pacijent, HttpStatus.OK);
	}
	
	@PutMapping("/penal/{id}")
	public ResponseEntity<String> dodeliPenal(@PathVariable Integer id){
		
		Pacijent p=pacijentService.findOne(id);
		if(p.getPenali()<3) {
			p.setPenali(p.getPenali()+1);
			pacijentService.save(p);
			return new ResponseEntity<String>("Dodeljen penal",HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Vec postoje 3 penala",HttpStatus.OK);
		
	}
	
}
