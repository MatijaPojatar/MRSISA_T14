package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.springboot.domain.Narudzbenica;

@Repository
public interface NarudzbenicaRepository extends JpaRepository<Narudzbenica, Integer> {

	Narudzbenica save(Narudzbenica n);
	
	Narudzbenica findOneById(Integer id);
	
}
