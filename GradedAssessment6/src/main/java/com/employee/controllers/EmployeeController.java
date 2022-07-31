package com.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.payloads.APIResponse;
import com.employee.payloads.EmployeeDTO;
import com.employee.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDto){
		EmployeeDTO createdEmployee = this.employeeService.create(employeeDto);
		return new ResponseEntity<EmployeeDTO>(createdEmployee,HttpStatus.CREATED);
	}
	
	@PutMapping("/{empid}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDto, @PathVariable int empid){
		EmployeeDTO updatedEmployee = this.employeeService.update(employeeDto, empid);
		return new ResponseEntity<EmployeeDTO>(updatedEmployee,HttpStatus.OK);
	}
	
	@DeleteMapping("/{empid}")
	public ResponseEntity<APIResponse> deleteEmployee(@PathVariable int empid){
		this.employeeService.delete(empid);
		return new ResponseEntity<APIResponse>(new APIResponse("Employee deleted successfully", true),HttpStatus.OK);
	}
	
	@GetMapping("/{empid}")
	public ResponseEntity<EmployeeDTO> getById(@PathVariable int empid){
		EmployeeDTO employeeDTO = this.employeeService.getById(empid);
		return new ResponseEntity<EmployeeDTO>(employeeDTO,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
		List<EmployeeDTO> allEmployees = this.employeeService.getAll();
		return new ResponseEntity<List<EmployeeDTO>>(allEmployees,HttpStatus.OK);
	}
	
	@GetMapping("/sort")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(
			@RequestParam(value = "sortBy", defaultValue = "firstName",required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir
			){
		List<EmployeeDTO> allEmployees = this.employeeService.getAll(sortBy,sortDir);
		return new ResponseEntity<List<EmployeeDTO>>(allEmployees,HttpStatus.OK);
	}
	
	@GetMapping("/firstname/{firstname}")
	public ResponseEntity<List<EmployeeDTO>> getEmployeesByFirstname(@PathVariable String firstname){
		List<EmployeeDTO> allEmployeesByName = this.employeeService.getByFirstname(firstname);
		return new ResponseEntity<List<EmployeeDTO>>(allEmployeesByName,HttpStatus.OK);
	}
}
