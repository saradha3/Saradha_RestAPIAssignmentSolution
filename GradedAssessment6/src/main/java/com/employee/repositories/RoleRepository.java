package com.employee.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	public Role findByName(String name);
}
