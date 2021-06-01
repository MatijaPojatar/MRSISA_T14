package com.backend.springboot.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Pregled;
import com.backend.springboot.repository.PregledRepository;

@Service
public class IzvestajApotekaService {

	@Autowired
	private PregledRepository pregledRep;
	
	public Collection<Integer> odrzaniPreglediUIntervalu(LocalDateTime pocetak, LocalDateTime kraj, Integer apotekaId){
		
		ArrayList<Pregled> pregledi = (ArrayList<Pregled>) pregledRep.findInRangeIzvrseniApoteka(pocetak, kraj, apotekaId);
		ArrayList<Integer> rezultat = new ArrayList<Integer>();
		
		Map<LocalDate, Integer> preglediMap = new HashMap<LocalDate, Integer>();
		
		LocalDate datumPocetak = pocetak.toLocalDate();
		LocalDate datumKraj = kraj.toLocalDate();
		
		while(datumPocetak.isBefore(datumKraj)) {
			preglediMap.put(datumPocetak, 0);
			datumPocetak = datumPocetak.plusDays(1);
			
		}
		
		for (Pregled p: pregledi) {
			LocalDate key = p.getPocetak().toLocalDate();
			Integer broj = preglediMap.get(key);
			preglediMap.put(key, broj + 1);
		}
		
		SortedSet<LocalDate> keys = new TreeSet<>(preglediMap.keySet());
		for (LocalDate key : keys) { 
		   Integer value = preglediMap.get(key);
		   rezultat.add(value);
		}
		return rezultat;
	}
	
	
}
