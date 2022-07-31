package com.employee.services;

import java.util.List;

import com.employee.model.Role;

public interface RoleService {

	public Role create(Role role);
	
	public List<Role> getAll();
}
