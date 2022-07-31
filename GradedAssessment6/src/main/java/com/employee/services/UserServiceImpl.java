package com.employee.services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.exceptions.ResourceNotFoundException;
import com.employee.model.Role;
import com.employee.model.User;
import com.employee.payloads.UserDTO;
import com.employee.repositories.RoleRepository;
import com.employee.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDTO create(UserDTO userDto) {
		// TODO Auto-generated method stub
		User user = this.mapper.map(userDto, User.class);
		Set<Role> roles = userDto.getRoles();
		Set<Role> newRoles = new HashSet<Role>();
		Iterator<Role> iterate = roles.iterator();
		while(iterate.hasNext()) {
			Role role = iterate.next();
			role=this.roleRepository.findByName("ROLE_"+role.getName());
			if(role == null) {
				throw new ResourceNotFoundException("Role "+role.getName()+ " is not found");
			}
			else {
				newRoles.add(role);
			}
		}
		user.setRoles(newRoles);
		User savedUser = this.userRepository.save(user);
		return this.mapper.map(savedUser, UserDTO.class);
	}

	@Override
	public List<UserDTO> getAll() {
		// TODO Auto-generated method stub
		List<User> all = this.userRepository.findAll();
		List<UserDTO> allDtos = all.stream().map(user -> this.mapper.map(user, UserDTO.class)).collect(Collectors.toList());
		return allDtos;
	}

}
