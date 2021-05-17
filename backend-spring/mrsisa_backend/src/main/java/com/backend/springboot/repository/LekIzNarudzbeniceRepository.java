package com.backend.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.LekIzNarudzbenice;

public interface LekIzNarudzbeniceRepository extends JpaRepository<LekIzNarudzbenice, Integer> {


	public List<LekIzNarudzbenice> findAllByNarudzbenicaId(Integer narudzbenicaId);

}
