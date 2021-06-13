package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.Osoba;


public interface OsobaRepository extends JpaRepository<Osoba, Integer> {
	List<Osoba> findAll();
	
	Osoba save(Osoba osoba);
	
	Osoba findOneByMail(String mail);
	
	Osoba findOneById(Integer id);
	
}
