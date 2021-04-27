package com.backend.springboot.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.backend.springboot.domain.Osoba;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.dto.JwtAuthenticationRequest;
import com.backend.springboot.dto.OsobaTokenState;
import com.backend.springboot.dto.PacijentDTO;
import com.backend.springboot.exception.ResourceConflictException;
import com.backend.springboot.service.OsobaService;
import com.backend.springboot.service.PacijentService;
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
	
	@PostMapping("/login")
	public ResponseEntity<OsobaTokenState> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response){
		//mogucnost za authenticationExc
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getMail(), authenticationRequest.getPassword())); //todo menjati za email
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Osoba osoba = (Osoba)authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(osoba); //todo
		int expiresIn = tokenUtils.getExpiredIn();
		
		return ResponseEntity.ok(new OsobaTokenState(jwt, expiresIn));
	}
	
	
	@PostMapping("/signup")  //ako moze, odvojiti ovo . Proveriti da li je automatski ulogovan, Ako jestem, ovde ostavi, a druge napolje
	public ResponseEntity<Pacijent> registrujPacijenta(@RequestBody PacijentDTO pacijentDTO, UriComponentsBuilder ucBuilder) {
		
		Pacijent existPacijent = this.pacijentService.findByMail(pacijentDTO.getMail());
		
		if(existPacijent != null) {
			throw new ResourceConflictException(pacijentDTO.getId(), "Email vec postoji");
		}
		
		Pacijent pacijent = this.pacijentService.save(new Pacijent(pacijentDTO));
		
		return new ResponseEntity<>(pacijent, HttpStatus.CREATED);
		
	}
	
	
	//registracije ovde napisati MOZDA
	
	
	
	
	
	
	
	
	
	
	
	
	
}
