package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.StavkaBodovanja;

public interface StavkaBodovanjaRepository extends JpaRepository<StavkaBodovanja, Integer>{
	
	StavkaBodovanja findOneById(Integer id);
	
	StavkaBodovanja save(StavkaBodovanja s);
	
	List<StavkaBodovanja> findAll();
}
