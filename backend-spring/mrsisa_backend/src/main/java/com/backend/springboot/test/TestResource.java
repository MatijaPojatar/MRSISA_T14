package com.backend.springboot.test;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:8081" })
@RestController
@RequestMapping("/api/tests")
public class TestResource {
	
	@Autowired
	private TestService testService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Test>> getTests() {
		Collection<Test> tests = testService.findAll();
		
		return new ResponseEntity<Collection<Test>>(tests, HttpStatus.OK);
	}
	
	
}
