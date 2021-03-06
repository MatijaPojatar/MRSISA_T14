package com.backend.springboot.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springboot.domain.Kategorija;
import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.Pregled;
import com.backend.springboot.domain.Savetovanje;
import com.backend.springboot.dto.KategorijaDTO;
import com.backend.springboot.dto.LekDTO;
import com.backend.springboot.dto.PacijentDTO;
import com.backend.springboot.dto.PregledDTO;
import com.backend.springboot.service.KategorijaService;
import com.backend.springboot.service.PacijentService;
import com.backend.springboot.service.PregledService;
import com.backend.springboot.service.SavetovanjeService;


@CrossOrigin(origins = {"http://localhost:8081" }) 
@RestController
@RequestMapping("/pacijent")
public class PacijentController {

	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private KategorijaService kategorijaService;
	
	@Autowired
	private SavetovanjeService savetovanjeService;
	
	@Autowired
	private PregledService pregledService;
	
//	int id, String ime, String prezime, String mail, String password, String adresa, String grad,
//	String drzava, String brojTelefona, Pol pol, LocalDate datumRodjenja, boolean promenjenaLozinka, int brojPoena, int penali
	
	
	@GetMapping("/poeni/{id}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<Integer> getPoeni(@PathVariable Integer id){
		Pacijent pacijent = pacijentService.findOne(id);
		Integer br = pacijent.getBrojPoena();
		if(br == null) {
			br = 0;
		}
		return new ResponseEntity<Integer>(br, HttpStatus.OK);
	}
	
	@GetMapping("/kategorija/{id}")
	@PreAuthorize("hasRole('PACIJENT')")
	public ResponseEntity<KategorijaDTO> getKat(@PathVariable Integer id){
		Pacijent pacijent = pacijentService.findOne(id);
		Integer br = pacijent.getBrojPoena();
		if(br == null) {
			br = 0;
		}
		Kategorija k = kategorijaService.getByBrojPoena(br);
		
		return new ResponseEntity<KategorijaDTO>(new KategorijaDTO(k), HttpStatus.OK);
	}
	
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN_APOTEKE','ADMIN_SISTEMA','FARMACEUT','DERMATOLOG', 'PACIJENT')") 
	public ResponseEntity<PacijentDTO> getOne(@PathVariable Integer id){
		Pacijent p = pacijentService.findOne(id);
		PacijentDTO dto = new PacijentDTO(p);
		
		return new ResponseEntity<PacijentDTO>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<PacijentDTO>> getAll(){
		List<Pacijent> pacijenti=pacijentService.findAll();
		List<PacijentDTO> dtos=new ArrayList<PacijentDTO>();
		for(Pacijent p:pacijenti) {
			dtos.add(new PacijentDTO(p));
		}
		
		return new ResponseEntity<List<PacijentDTO>>(dtos, HttpStatus.OK);
	}
	
	
	@GetMapping("/mail/{mail}")
	@PreAuthorize("hasAnyRole('ADMIN_APOTEKE','ADMIN_SISTEMA','FARMACEUT','DERMATOLOG')") 
	public ResponseEntity<PacijentDTO> getOne(@PathVariable String mail){
		Pacijent p = pacijentService.findByMail(mail);
		PacijentDTO dto = new PacijentDTO(p);
		
		return new ResponseEntity<PacijentDTO>(dto, HttpStatus.OK);
	}
	

	@PutMapping("/penal/{id}")
	@PreAuthorize("hasAnyRole('DERMATOLOG','FARMACEUT')")
	public ResponseEntity<String> dodeliPenal(@PathVariable Integer id){
		
		Pacijent p=pacijentService.findOne(id);
		if(p.getPenali()<3) {
			p.setPenali(p.getPenali()+1);
			pacijentService.save(p);
			return new ResponseEntity<String>("Dodeljen penal",HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Vec postoje 3 penala",HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	@GetMapping("/penali/{id}")
	@PreAuthorize("hasAnyRole('PACIJENT')")
	public ResponseEntity<Integer> getPenaliPacijenta(@PathVariable Integer id){
		Pacijent p = pacijentService.findOne(id);
		
		try {
			return new ResponseEntity<Integer>(p.getPenali(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(-1, HttpStatus.NOT_FOUND);
		}		
	}
	
	@PutMapping("/aktivacija/{id}")
	public ResponseEntity<String> aktivirajProfil(@PathVariable Integer id){
		Pacijent p = pacijentService.findOne(id);
		
		if(!p.isEnabled()) {
			p.setEnabled(true);
			pacijentService.save(p);
			return new ResponseEntity<String>("Uspe??no ste aktivirali profil "+ p.getIme() + " " + p.getPrezime() + ".", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Profil korisnika "+ p.getIme() + " " + p.getPrezime() + " je ve?? aktiviran.", HttpStatus.OK);
	}
	
	/*@GetMapping("/all")
	public ResponseEntity<List<PacijentDTO>> getAll(){
		List<Pacijent> pacijenti=pacijentService.findAll();
		ArrayList<PacijentDTO> dtos=new ArrayList<PacijentDTO>();
		for(Pacijent p:pacijenti) {
			dtos.add(new PacijentDTO(p));
		}
		
		return new ResponseEntity<List<PacijentDTO>>(dtos,HttpStatus.OK);
	}*/
	
	@GetMapping("/alergije/{id}")
	@PreAuthorize("hasAnyRole('DERMATOLOG','FARMACEUT','PACIJENT')")
	public ResponseEntity<List<LekDTO>> getAlergije(@PathVariable Integer id){
		List<Lek> lekovi=pacijentService.findAllAlergijeById(id);
		ArrayList<LekDTO> dtos=new ArrayList<LekDTO>();
		
		for(Lek l:lekovi) {
			dtos.add(new LekDTO(l));
		}
		
		return new ResponseEntity<List<LekDTO>>(dtos,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('PACIJENT')")
	@PutMapping("/dodaj/alergije/{id}")
	public ResponseEntity<Boolean> dodajAlergije(@PathVariable Integer id, @RequestBody List<Lek> alergije){
		pacijentService.setAlergije(id, alergije);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@PostMapping("/proveri_termin/{id}")
	@PreAuthorize("hasAnyRole('DERMATOLOG','FARMACEUT','PACIJENT')")
	public ResponseEntity<Boolean> proveriTermin(@PathVariable Integer id,@RequestBody PregledDTO pregled){
		LocalDateTime pocetak=pregled.getStart().plusHours(2);
		LocalDateTime kraj=pregled.getEnd().plusHours(2);
		
		List<Pregled> checkListP=pregledService.findAllInRangeForPacijent(id,pocetak,kraj);
		if(checkListP.size()!=0) {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		
		List<Savetovanje> checkListS=savetovanjeService.findAllInRangeForPacijent(id,pocetak,kraj);
		if(checkListS.size()!=0) {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
	@PutMapping("/pretplata/{id}/{apotekaId}")
	public ResponseEntity<Boolean> pretplati(@PathVariable Integer id, @PathVariable Integer apotekaId){
		pacijentService.pretplati(id, apotekaId);
		
		
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
}
