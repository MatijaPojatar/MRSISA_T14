package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.backend.springboot.domain.AdministratorApoteke;
import com.backend.springboot.domain.AdministratorSistema;
import com.backend.springboot.domain.Kategorija;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.service.EmailService;
import com.backend.springboot.service.KategorijaService;
import com.backend.springboot.domain.Role;
import com.backend.springboot.domain.StavkaBodovanja;
import com.backend.springboot.domain.TipStavkeBodovanja;
import com.backend.springboot.dto.AdministratorSistemaDTO;
import com.backend.springboot.dto.KategorijaDTO;
import com.backend.springboot.dto.StavkaBodovanjaDTO;
import com.backend.springboot.exception.ResourceConflictException;
import com.backend.springboot.service.AdministratorSistemaService;
import com.backend.springboot.service.RoleService;
import com.backend.springboot.service.StavkaBodovanjaService;
import com.backend.springboot.service.PacijentService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/adminSistema")
public class AdministratorSistemaController {
	
	@Autowired
	private KategorijaService kategorijaService;
	
	@Autowired
	private StavkaBodovanjaService stavkeService;
	
	@Autowired
	private AdministratorSistemaService service;
	
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/bodovanje/{tip}/{id}")
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	public ResponseEntity<StavkaBodovanjaDTO> getBodovanje(@PathVariable String tip, @PathVariable Integer id){

		
		TipStavkeBodovanja tipp = TipStavkeBodovanja.SAVETOVANJE;
		if(tip.toLowerCase().equals("pregled")) {
			tipp = TipStavkeBodovanja.PREGLED;
		}else if(tip.toLowerCase().equals("lek")) {
			tipp = TipStavkeBodovanja.LEK;
		}
		
		
		StavkaBodovanja s = stavkeService.find(tipp, id);
		
		if(s == null) {
			s = new StavkaBodovanja();
			s.setBrojPoena(0);
			s.setIdLeka(0);
			s.setTip(tipp);
		}
		
		return new ResponseEntity<StavkaBodovanjaDTO>(new StavkaBodovanjaDTO(s), HttpStatus.OK);
	}
	
	
	@GetMapping("/kategorija")
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	public ResponseEntity<List<KategorijaDTO>> getKat(){

		List<Kategorija> sve = kategorijaService.findAll();
		List<KategorijaDTO> dtos = new ArrayList<KategorijaDTO>();
		for(Kategorija k : sve) {
			dtos.add(new KategorijaDTO(k));
		}
		
		return new ResponseEntity<List<KategorijaDTO>>(dtos, HttpStatus.OK);
	}
	
	@DeleteMapping("/kategorija/{id}")
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	public ResponseEntity<String> delKat(@PathVariable Integer id){

		kategorijaService.deleteById(id);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('PACIJENT')")
	@GetMapping("/kategorija/pacijent/{id}")
	public ResponseEntity<KategorijaDTO> getKat(@PathVariable Integer id){

		Pacijent pac = pacijentService.findOne(id);
		
		Kategorija kat = kategorijaService.getByBrojPoena(pac.getBrojPoena());
		
		return new ResponseEntity<KategorijaDTO>(new KategorijaDTO(kat),HttpStatus.OK);
	}
	
	
	@PostMapping("/kategorija")
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	public ResponseEntity<KategorijaDTO> createKat(@RequestBody KategorijaDTO dto){
		
		Kategorija kat = new Kategorija(dto);
	    kat = kategorijaService.save(kat);
		
		return new ResponseEntity<KategorijaDTO>(new KategorijaDTO(kat), HttpStatus.OK);
	}
	
	@PostMapping("/stavkaBodovanja")
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	public ResponseEntity<StavkaBodovanjaDTO> createStavka(@RequestBody StavkaBodovanjaDTO dto){
		
		if(!dto.getTip().equals(TipStavkeBodovanja.LEK)) {
			stavkeService.deleteByType(dto.getTip());
		}
		
		
		StavkaBodovanja s = stavkeService.save(new StavkaBodovanja(dto));
		
		return new ResponseEntity<StavkaBodovanjaDTO>(new StavkaBodovanjaDTO(s), HttpStatus.OK);
	}
	
	@PutMapping("/stavkaBodovanja")
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	public ResponseEntity<StavkaBodovanjaDTO> updateStavka(@RequestBody StavkaBodovanjaDTO dto){
		StavkaBodovanja s = stavkeService.save(new StavkaBodovanja(dto));
		
		return new ResponseEntity<StavkaBodovanjaDTO>(new StavkaBodovanjaDTO(s), HttpStatus.OK);
	}
	
	@GetMapping("/poeni/{tip}/{id}")
	public ResponseEntity<Integer> getPopust(@PathVariable String tip, @PathVariable Integer id){
		StavkaBodovanja s = stavkeService.find(TipStavkeBodovanja.valueOf(tip.toUpperCase()), id);
		return new ResponseEntity<Integer>(s.getBrojPoena(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	public ResponseEntity<AdministratorSistemaDTO> getOne(@PathVariable Integer id) {
		AdministratorSistema a = service.findOne(id);
		AdministratorSistemaDTO dto = new AdministratorSistemaDTO(a);
		
		return new ResponseEntity<AdministratorSistemaDTO>(dto, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@PostMapping("/asSignup")
	public ResponseEntity<AdministratorSistemaDTO> registrujAdminaSistema(@RequestBody AdministratorSistemaDTO asDTO, UriComponentsBuilder ucBuilder){
		AdministratorSistema existAdmin = this.service.findByMail(asDTO.getMail());
		if(existAdmin != null) {
			throw new ResourceConflictException(asDTO.getId(), "Email vec postoji");
		}
		
		AdministratorSistema novi = new AdministratorSistema(asDTO);
		novi.setPassword("12345678");
		novi.setEnabled(true);
		novi.setPassword(new BCryptPasswordEncoder().encode(novi.getPassword()));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleService.findByName("ROLE_ADMIN_SISTEMA"));
		novi.setRoles(roles);
		novi.setPromenjenaLozinka(false);
		AdministratorSistema admin = this.service.save(novi);
		
		try {
			emailService.regByAdmin(admin.getMail());
		}catch(Exception e) {
			System.out.println("Gre??ka prilikom slanja emaila: " +e.getMessage());
		}
		
		return new ResponseEntity<AdministratorSistemaDTO>(new AdministratorSistemaDTO(admin), HttpStatus.CREATED);
	}

	
	
}
