package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.Savetovanje;

public interface SavetovanjeRepository extends JpaRepository<Savetovanje, Integer> {
	
	public List<Savetovanje> findAllByFarmaceutId(Integer farmaceutId);
	
	public Savetovanje findOneById(Integer id);
	
	public Savetovanje save(Savetovanje s);
	
	

}
