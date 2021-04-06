package com.backend.springboot.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.Apoteka;
import com.backend.springboot.domain.Savetovanje;


public interface ApotekaRepository extends JpaRepository<Apoteka, Integer> {
	List<Apoteka> findAll();
	
	
	Apoteka addApoteka(Apoteka apoteka);
	
	Apoteka findOne(String sifra);
	
	void delete(String sifra);
}
