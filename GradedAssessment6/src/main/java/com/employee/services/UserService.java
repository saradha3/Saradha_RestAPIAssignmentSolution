package com.employee.services;

import java.util.List;

import com.employee.payloads.UserDTO;

public interface UserService {

	public UserDTO create(UserDTO userDto);
	
	public List<UserDTO> getAll();
}
