package com.employee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUsername(String username);
}
