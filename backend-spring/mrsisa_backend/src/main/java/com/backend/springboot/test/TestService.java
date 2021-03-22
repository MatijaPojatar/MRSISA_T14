package com.backend.springboot.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	public List<Test> findAll() {
		ArrayList<Test> result = new ArrayList<Test>();
		
		result.add(new Test(1, "First"));
		result.add(new Test(2, "Second"));
		
		return result;
	}
}
