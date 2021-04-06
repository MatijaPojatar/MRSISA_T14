package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.Narudzbenica;

public interface NarudzbenicaRepository extends JpaRepository<Narudzbenica, Integer> {

}
