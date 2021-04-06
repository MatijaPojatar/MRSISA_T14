package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.springboot.domain.Pacijent;

@Repository
public interface PacijentRepository extends JpaRepository<Pacijent, Integer> {
	
	Pacijent save(Pacijent p);
	
	Pacijent findOneById(Integer id);
	
}
