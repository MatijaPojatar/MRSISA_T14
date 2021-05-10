package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.LekUIzvestaju;

public interface LekUIzvestajuRepository extends JpaRepository<LekUIzvestaju, Integer>{
	
	public List<LekUIzvestaju> findAllByTerminId(Integer terminId);
}
