package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.LekERecepta;

public interface LekEReceptaRepository extends JpaRepository<LekERecepta, Integer>{
	
	public List<LekERecepta> findAllByEreceptId(Integer id);

}
