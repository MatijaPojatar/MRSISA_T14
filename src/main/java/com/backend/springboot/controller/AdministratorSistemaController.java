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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.backend.springboot.domain.AdministratorApoteke;
import com.backend.springboot.domain.AdministratorSistema;
import com.backend.springboot.service.EmailService;
import com.backend.springboot.domain.Role;
import com.backend.springboot.dto.AdministratorSistemaDTO;
import com.backend.springboot.exception.ResourceConflictException;
import com.backend.springboot.service.AdministratorSistemaService;
import com.backend.springboot.service.RoleService;

@CrossOrigin(origins = {"http://localhost:8081" })
@RestController
@RequestMapping("/adminSistema")
public class AdministratorSistemaController {
	
	@Autowired
	private AdministratorSistemaService service;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/{id}")
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
			System.out.println("Greška prilikom slanja emaila: " +e.getMessage());
		}
		
		return new ResponseEntity<AdministratorSistemaDTO>(new AdministratorSistemaDTO(admin), HttpStatus.CREATED);
	}

	
	
}
