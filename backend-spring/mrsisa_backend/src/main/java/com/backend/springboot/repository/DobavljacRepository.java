package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.Dobavljac;

public interface DobavljacRepository extends JpaRepository<Dobavljac, Integer> {
	
	public List<Dobavljac> findAll();
	
	public Dobavljac findOneById(Integer id);

}
