package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.AdministratorApoteke;

public interface AdministratorApotekeRepository extends JpaRepository<AdministratorApoteke, Integer> {

	public List<AdministratorApoteke> findAll();
	
	public AdministratorApoteke findOneById(Integer id);
}
