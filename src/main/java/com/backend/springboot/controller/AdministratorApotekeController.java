package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.backend.springboot.domain.AdministratorApoteke;
import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.Farmaceut;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.Role;
import com.backend.springboot.dto.AdministratorApotekeDTO;
import com.backend.springboot.dto.FarmaceutDTO;
import com.backend.springboot.exception.ResourceConflictException;
import com.backend.springboot.service.AdministratorApotekeService;
import com.backend.springboot.service.ApotekaService;
import com.backend.springboot.service.EmailService;
import com.backend.springboot.service.RoleService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/adminApoteke")
public class AdministratorApotekeController {

	@Autowired
	private AdministratorApotekeService service;
	
	@Autowired
	private ApotekaService apotekaService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private EmailService emailService;
	
	//ADMIN_APOTEKE
	@PreAuthorize("hasRole('ADMIN_APOTEKE')")
	@GetMapping("/{id}")
	public ResponseEntity<AdministratorApotekeDTO> getOne(@PathVariable Integer id){
		AdministratorApoteke a=service.findOne(id);
		AdministratorApotekeDTO dto=new AdministratorApotekeDTO(a);
		
		return new ResponseEntity<AdministratorApotekeDTO>(dto,HttpStatus.OK);
	}
	
	//ADMIN_APOTEKE
	@PreAuthorize("hasRole('ADMIN_APOTEKE')")
	@PutMapping("/save/{id}")
	public ResponseEntity<String> saveOne(@PathVariable Integer id,@RequestBody AdministratorApotekeDTO dto){
		AdministratorApoteke a=service.findOne(id);
		a.setAdresa(dto.getAdresa());
		a.setBrojTelefona(dto.getBrojTelefona());
		a.setDatumRodjenja(dto.getDatumRodjenja());
		a.setDrzava(dto.getDrzava());
		a.setGrad(dto.getGrad());
		a.setIme(dto.getIme());
		a.setPol(dto.getPol());
		a.setPrezime(dto.getPrezime());
		
		service.save(a);
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN_SISTEMA')")
	@PostMapping("/aapSignup")
	public ResponseEntity<AdministratorApotekeDTO> registrujAdminaApoteke(@RequestBody AdministratorApotekeDTO adminApotekeDTO, UriComponentsBuilder ucBuilder){
		
		AdministratorApoteke existAdmin = this.service.findByMail(adminApotekeDTO.getMail());
		if(existAdmin != null) {
			throw new ResourceConflictException(adminApotekeDTO.getId(), "Email vec postoji"); //apoteka gde radi?
		}
		
		AdministratorApoteke novi = new AdministratorApoteke(adminApotekeDTO);
		novi.setApoteka(apotekaService.findOne(adminApotekeDTO.getApotekaId()));
		novi.setPassword("12345678");
		novi.setEnabled(true);
		novi.setPassword(new BCryptPasswordEncoder().encode(novi.getPassword()));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleService.findByName("ROLE_ADMIN_APOTEKE"));
		novi.setRoles(roles);
		novi.setPromenjenaLozinka(false);
		AdministratorApoteke admin = this.service.save(novi);
		
		try {
			emailService.regByAdmin(admin.getMail());
		}catch(Exception e) {
			System.out.println("Greška prilikom slanja emaila: " +e.getMessage());
		}
		
		return new ResponseEntity<AdministratorApotekeDTO>(new AdministratorApotekeDTO(admin), HttpStatus.CREATED);
	} 
	
	

}
