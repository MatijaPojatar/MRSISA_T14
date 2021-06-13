package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.Dermatolog;
import com.backend.springboot.domain.Farmaceut;

public interface DermatologRepository extends JpaRepository<Dermatolog, Integer> {
	
	public List<Dermatolog> findAll();
	
	public Dermatolog findOneById(Integer id);

	public Dermatolog findOneByMail(String mail);
	

}
