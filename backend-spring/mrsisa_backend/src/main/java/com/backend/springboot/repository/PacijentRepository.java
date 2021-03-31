package com.backend.springboot.repository;

import org.springframework.stereotype.Repository;

import com.backend.springboot.domain.Pacijent;

@Repository
public interface PacijentRepository {
	Pacijent registrujPacijenta(Pacijent pacijent);
}
