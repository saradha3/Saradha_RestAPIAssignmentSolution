package com.employee.payloads;

import java.util.HashSet;
import java.util.Set;

import com.employee.model.Role;

public class UserDTO {

	private int id;
	
	private String username;
	private String password;
	
	private Set<Role> roles = new HashSet<Role>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
}
