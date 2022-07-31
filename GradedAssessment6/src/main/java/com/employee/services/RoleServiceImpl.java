package com.employee.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.model.Role;
import com.employee.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role create(Role role) {
		// TODO Auto-generated method stub
		Role savedRole = this.roleRepository.save(role);
		return savedRole;
	}

	@Override
	public List<Role> getAll() {
		// TODO Auto-generated method stub
		List<Role> all = this.roleRepository.findAll();
		return all;
	}

}
