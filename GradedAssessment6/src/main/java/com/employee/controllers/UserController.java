package com.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.payloads.UserDTO;
import com.employee.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/")
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDto){
		userDto.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
		UserDTO createdUser = this.userService.create(userDto);
		return new ResponseEntity<UserDTO>(createdUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAll(){
		List<UserDTO> all = this.userService.getAll();
		
		SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		return new ResponseEntity<List<UserDTO>>(all,HttpStatus.OK);
	}
}
