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

import com.backend.springboot.domain.AdministratorApoteke;
import com.backend.springboot.domain.AdministratorSistema;
import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.Dobavljac;
import com.backend.springboot.domain.Osoba;
import com.backend.springboot.domain.Pacijent;
import com.backend.springboot.domain.Role;
import com.backend.springboot.dto.AdministratorApotekeDTO;
import com.backend.springboot.dto.AdministratorSistemaDTO;
import com.backend.springboot.dto.DermatologDTO;
import com.backend.springboot.dto.DobavljacDTO;
import com.backend.springboot.dto.JwtAuthenticationRequest;
import com.backend.springboot.dto.OsobaTokenState;
import com.backend.springboot.dto.PacijentDTO;
import com.backend.springboot.exception.ResourceConflictException;
import com.backend.springboot.service.AdministratorApotekeService;
import com.backend.springboot.service.AdministratorSistemaService;
import com.backend.springboot.service.DermatologService;
import com.backend.springboot.service.DobavljacService;
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
	
	@Autowired 
	private DobavljacService dobavljacService;
	
	@Autowired 
	private DermatologService dermatologService;
	
	@Autowired
	private AdministratorApotekeService adminApotekeService;
	
	@Autowired
	private AdministratorSistemaService adminSistemaService;
	
	
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
		
		Pacijent existPacijent = this.pacijentService.findByMail(pacijentDTO.getMail()); //promeniti na dto?
		
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
	
	//admin sistema
	@PostMapping("/asSignup")
	public ResponseEntity<AdministratorSistemaDTO> registrujAdminaSistema(@RequestBody AdministratorSistemaDTO asDTO, UriComponentsBuilder ucBuilder){
		AdministratorSistema existAdmin = this.adminSistemaService.findByMail(asDTO.getMail());
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
		AdministratorSistema admin = this.adminSistemaService.save(novi);
		
		try {
			emailService.regByAdmin(admin.getMail());
		}catch(Exception e) {
			System.out.println("Greška prilikom slanja emaila: " +e.getMessage());
		}
		
		return new ResponseEntity<AdministratorSistemaDTO>(new AdministratorSistemaDTO(admin), HttpStatus.CREATED);
	}
	
	
	//admin apoteke
	@PostMapping("/aapSignup")
	public ResponseEntity<AdministratorApotekeDTO> registrujAdminaApoteke(@RequestBody AdministratorApotekeDTO adminApotekeDTO, UriComponentsBuilder ucBuilder){
		
		AdministratorApoteke existAdmin = this.adminApotekeService.findByMail(adminApotekeDTO.getMail());
		if(existAdmin != null) {
			throw new ResourceConflictException(adminApotekeDTO.getId(), "Email vec postoji"); //apoteka gde radi?
		}
		
		AdministratorApoteke novi = new AdministratorApoteke(adminApotekeDTO);
		novi.setPassword("12345678");
		novi.setEnabled(true);
		novi.setPassword(new BCryptPasswordEncoder().encode(novi.getPassword()));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleService.findByName("ROLE_ADMIN_APOTEKE"));
		novi.setRoles(roles);
		novi.setPromenjenaLozinka(false);
		AdministratorApoteke admin = this.adminApotekeService.save(novi);
		
		try {
			emailService.regByAdmin(admin.getMail());
		}catch(Exception e) {
			System.out.println("Greška prilikom slanja emaila: " +e.getMessage());
		}
		
		return new ResponseEntity<AdministratorApotekeDTO>(new AdministratorApotekeDTO(admin), HttpStatus.CREATED);
	} 
	
	//dermatolog
	@PostMapping("/dermSignup")
	public ResponseEntity<DermatologDTO> registrujDermatologa(@RequestBody DermatologDTO dermatologDTO, UriComponentsBuilder ucBuilder){
		
		Dermatolog existDermatolog = this.dermatologService.findByMail(dermatologDTO.getMail());
		if(existDermatolog != null) {
			throw new ResourceConflictException(dermatologDTO.getId(), "Email vec postoji");
		}
		
		Dermatolog novi = new Dermatolog(dermatologDTO);
		novi.setPassword("12345678");
		novi.setEnabled(true);
		novi.setPassword(new BCryptPasswordEncoder().encode(novi.getPassword()));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleService.findByName("ROLE_DERMATOLOG"));
		novi.setRoles(roles);
		novi.setPromenjenaLozinka(false);
		Dermatolog dermatolog = this.dermatologService.save(novi);
		
		try {
			emailService.regByAdmin(dermatolog.getMail());
		}catch(Exception e) {
			System.out.println("Greška prilikom slanja emaila: " +e.getMessage());
		}
		
		return new ResponseEntity<DermatologDTO>(new DermatologDTO(dermatolog), HttpStatus.CREATED);
	} 
	//dobavljac
	@PostMapping("/dobSignup")
	public ResponseEntity<DobavljacDTO> registrujDobavljaca(@RequestBody DobavljacDTO dobavljacDTO, UriComponentsBuilder ucBuilder){
		
		Dobavljac existDobavljac = this.dobavljacService.findByMail(dobavljacDTO.getMail());
		if(existDobavljac != null) {
			throw new ResourceConflictException(dobavljacDTO.getId(), "Email vec postoji");
		}
		
		Dobavljac novi = new Dobavljac(dobavljacDTO);
		novi.setPassword("12345678");
		novi.setEnabled(true);
		novi.setPassword(new BCryptPasswordEncoder().encode(novi.getPassword()));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleService.findByName("ROLE_DOBAVLJAC"));
		novi.setRoles(roles);
		novi.setPromenjenaLozinka(false);
		Dobavljac dobavljac = this.dobavljacService.save(novi);
		
		try {
			emailService.regByAdmin(dobavljac.getMail());
		}catch(Exception e) {
			System.out.println("Greška prilikom slanja emaila: " +e.getMessage());
		}
		
		return new ResponseEntity<DobavljacDTO>(new DobavljacDTO(dobavljac), HttpStatus.CREATED);
	} 
	
	

}
