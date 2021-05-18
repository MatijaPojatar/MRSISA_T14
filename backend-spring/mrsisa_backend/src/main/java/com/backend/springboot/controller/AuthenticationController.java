package com.backend.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.backend.springboot.domain.Osoba;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.Role;
import com.backend.springboot.dto.JwtAuthenticationRequest;
import com.backend.springboot.dto.OsobaTokenState;
import com.backend.springboot.dto.PacijentDTO;
import com.backend.springboot.exception.ResourceConflictException;
import com.backend.springboot.service.EmailService;
import com.backend.springboot.service.OsobaService;
import com.backend.springboot.service.PacijentService;
import com.backend.springboot.service.RoleService;
import com.backend.springboot.util.TokenUtils;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private RoleService roleService;
	
	
	@PostMapping("/login")
	public ResponseEntity<OsobaTokenState> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) throws Exception{
		//mogucnost za authenticationExc
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getMail(), authenticationRequest.getPassword())); //todo menjati za email
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Osoba osoba = (Osoba)authentication.getPrincipal();
		
		if(!osoba.isEnabled()) {
			throw new Exception();
		}
		
		String jwt = tokenUtils.generateToken(osoba); //todo
		int expiresIn = tokenUtils.getExpiredIn();
		
		return ResponseEntity.ok(new OsobaTokenState(jwt, expiresIn));
	}
	
	
	@PostMapping("/signup")  //ako moze, odvojiti ovo . Proveriti da li je automatski ulogovan, Ako jestem, ovde ostavi, a druge napolje
	public ResponseEntity<Pacijent> registrujPacijenta(@RequestBody PacijentDTO pacijentDTO, UriComponentsBuilder ucBuilder) throws MailException, InterruptedException {
		
		Pacijent existPacijent = this.pacijentService.findByMail(pacijentDTO.getMail());
		
		if(existPacijent != null) {
			throw new ResourceConflictException(pacijentDTO.getId(), "Email vec postoji");
		}
		
		Pacijent novi = new Pacijent(pacijentDTO);
		novi.setPassword(new BCryptPasswordEncoder().encode(novi.getPassword()));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleService.findByName("ROLE_PACIJENT"));
		novi.setRoles(roles);
		
		Pacijent pacijent = this.pacijentService.save(novi);
		
		try {
			emailService.aktivacija(pacijent, "http://localhost:8081/pacijent/aktiviraj/"+pacijent.getId());
		} catch(Exception e){
			System.out.println("Greska prilikom slanja emaila: " + e.getMessage());
		}
		
		return new ResponseEntity<>(pacijent, HttpStatus.CREATED);
		
	}
	
	
	//registracije ovde napisati MOZDA

}
