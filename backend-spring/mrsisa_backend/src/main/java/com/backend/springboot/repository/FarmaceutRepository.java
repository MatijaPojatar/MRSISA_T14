package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.Farmaceut;

public interface FarmaceutRepository extends JpaRepository<Farmaceut, Integer> {

	public List<Farmaceut> findAll();
}
