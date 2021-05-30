package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.Apoteka;


public interface ApotekaRepository extends JpaRepository<Apoteka, Integer> {
	List<Apoteka> findAll();
	
	Apoteka findOneById(Integer id);
	
	List<Apoteka> findByNazivIgnoringCaseAndGradIgnoringCase(String naziv, String grad);
	
	List<Apoteka> findByNazivIgnoringCase(String naziv);
	
	List<Apoteka> findByGradIgnoringCase(String grad);
}
