package com.backend.springboot.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.Lek;


public interface LekRepository extends JpaRepository<Lek, Integer> {
	
	List<Lek> findAll();
	
	//List<Lek> findAllByLekId(Integer id);
	
	Lek save(Lek lek);
	
	Lek findOneById(Integer id);
	
	
}
