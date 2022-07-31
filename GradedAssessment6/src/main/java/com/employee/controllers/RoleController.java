package com.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Role;
import com.employee.payloads.UserDTO;
import com.employee.services.RoleService;
import com.employee.services.UserService;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	

	@PostMapping("/")
	public ResponseEntity<Role> create(@RequestBody Role role){
		role.setName("ROLE_"+role.getName());
		Role createdRole = this.roleService.create(role);
		return new ResponseEntity<Role>(createdRole,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Role>> getAll(){
		List<Role> all = this.roleService.getAll();
		return new ResponseEntity<List<Role>>(all,HttpStatus.OK);
	}
}
