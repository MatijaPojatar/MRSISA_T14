package com.backend.springboot.repository;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.springboot.domain.Lek;
import com.backend.springboot.domain.Pacijent;

@Repository
public interface PacijentRepository extends JpaRepository<Pacijent, Integer> {
	
	Pacijent save(Pacijent p);
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
	Pacijent findOneById(Integer id);
	
	Pacijent findOneByMail(String mail);
	
	@Query("select p.alergije from Pacijent p where p.id=:id")
	public List<Lek> findAllAlergijeById(@Param("id")Integer id);
	
}
