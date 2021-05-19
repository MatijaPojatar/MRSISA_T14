package com.backend.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springboot.domain.Role;
import com.backend.springboot.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

    public Role findById(Integer id) {
    	Role auth = this.roleRepository.findOneById(id);
    	return auth;
    }

    public Role findByName(String name) {
    	Role role = this.roleRepository.findOneByName(name);
    	return role;
    }

}
