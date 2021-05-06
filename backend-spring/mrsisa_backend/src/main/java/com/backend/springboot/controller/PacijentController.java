package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.dto.LekDTO;
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
	
	@GetMapping("/mail/{mail}")
	public ResponseEntity<PacijentDTO> getOne(@PathVariable String mail){
		Pacijent p = pacijentService.findByMail(mail);
		PacijentDTO dto = new PacijentDTO(p);
		
		return new ResponseEntity<PacijentDTO>(dto, HttpStatus.OK);
	}
	
//	@PostMapping()
//	public ResponseEntity<Pacijent> registrujPacijenta(@RequestBody Pacijent pacijentInfo){
//		Pacijent pacijent = pacijentService.save(pacijentInfo);
//		
//		return new ResponseEntity<Pacijent>(pacijent, HttpStatus.OK);
//	}
	
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
	
	@GetMapping("/all")
	public ResponseEntity<List<PacijentDTO>> getAll(){
		List<Pacijent> pacijenti=pacijentService.findAll();
		ArrayList<PacijentDTO> dtos=new ArrayList<PacijentDTO>();
		for(Pacijent p:pacijenti) {
			dtos.add(new PacijentDTO(p));
		}
		
		return new ResponseEntity<List<PacijentDTO>>(dtos,HttpStatus.OK);
	}
	
	@GetMapping("/alergije/{id}")
	public ResponseEntity<List<LekDTO>> getAlergije(@PathVariable Integer id){
		List<Lek> lekovi=pacijentService.findAllAlergijeById(id);
		ArrayList<LekDTO> dtos=new ArrayList<LekDTO>();
		
		for(Lek l:lekovi) {
			dtos.add(new LekDTO(l));
		}
		
		return new ResponseEntity<List<LekDTO>>(dtos,HttpStatus.OK);
	}
	
}
