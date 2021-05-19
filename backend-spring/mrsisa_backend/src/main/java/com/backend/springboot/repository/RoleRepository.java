package com.backend.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springboot.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	Role findOneByName(String name);

	Role findOneById(Integer id);
	
	
}
