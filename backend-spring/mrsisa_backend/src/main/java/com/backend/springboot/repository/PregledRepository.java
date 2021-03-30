package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.Pregled;

public interface PregledRepository extends JpaRepository<Pregled, Integer> {
	
	public List<Pregled> findAllByDermatologId(Integer dermatologId);

}
