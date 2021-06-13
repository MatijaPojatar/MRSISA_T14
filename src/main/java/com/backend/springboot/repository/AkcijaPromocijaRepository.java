package com.backend.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.AkcijaPromocija;

public interface AkcijaPromocijaRepository extends JpaRepository<AkcijaPromocija, Integer> {
	List<AkcijaPromocija> findAllByApotekaId(Integer id);
}
