package com.employee.services;

import java.util.List;

import com.employee.payloads.EmployeeDTO;

public interface EmployeeService {

	public EmployeeDTO create(EmployeeDTO employeeDto);
	
	public EmployeeDTO update(EmployeeDTO employeeDto, int empid);
	
	public void delete(int empid);
	
	public EmployeeDTO getById(int empid);
	
	public List<EmployeeDTO> getAll(String sortBy,String sortDir);
	public List<EmployeeDTO> getAll();
	
	public List<EmployeeDTO> getByFirstname(String firstname);
	
}
