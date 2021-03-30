package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.Farmaceut;

public interface FarmaceutRepository extends JpaRepository<Farmaceut, Integer> {

}
