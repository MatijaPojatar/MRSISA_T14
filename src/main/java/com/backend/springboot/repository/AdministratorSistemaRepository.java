package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.AdministratorSistema;

public interface AdministratorSistemaRepository extends JpaRepository<AdministratorSistema, Integer>{
	public List<AdministratorSistema> findAll();
	
	public AdministratorSistema findOneById(Integer id);

	public AdministratorSistema findOneByMail(String mail);
}
