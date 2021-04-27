package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.ZalbaNaDermatologa;

public interface ZalbaNaDermatologaRepository extends JpaRepository<ZalbaNaDermatologa, Integer>{
	
	List<ZalbaNaDermatologa> findAll();
	
	ZalbaNaDermatologa findOneById(Integer id);
	
}
