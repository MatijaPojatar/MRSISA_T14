package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.ZalbaNaApoteku;

public interface ZalbaNaApotekuRepository extends JpaRepository<ZalbaNaApoteku, Integer> {

	List<ZalbaNaApoteku> findAll();
	
	ZalbaNaApoteku findOneById(Integer id);
	
	List<ZalbaNaApoteku> findByObradjenaFalse();
	
	List<ZalbaNaApoteku> findAllByPacijentId(Integer id);
	
	List<ZalbaNaApoteku> findByObradjenaTrueAndAdministratorId(Integer id);
}

