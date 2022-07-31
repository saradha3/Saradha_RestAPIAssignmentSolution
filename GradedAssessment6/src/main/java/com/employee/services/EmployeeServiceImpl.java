package com.employee.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.employee.exceptions.ResourceNotFoundException;
import com.employee.model.Employee;
import com.employee.payloads.EmployeeDTO;
import com.employee.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public EmployeeDTO create(EmployeeDTO employeeDto) {
		// TODO Auto-generated method stub
		Employee employee = this.mapper.map(employeeDto, Employee.class);
		Employee createdEmployee = this.employeeRepository.save(employee);
		return this.mapper.map(createdEmployee, EmployeeDTO.class);
	}

	@Override
	public EmployeeDTO update(EmployeeDTO employeeDto, int empid) {
		// TODO Auto-generated method stub
		Employee emp = this.employeeRepository.findById(empid).orElseThrow(()-> new ResourceNotFoundException("Employee with ID "+empid+ " is not found!"));
		emp.setFirstName(employeeDto.getFirstName());
		emp.setLastName(employeeDto.getLastName());
		emp.setEmail(employeeDto.getEmail());
		Employee updatedEmployee = this.employeeRepository.save(emp);
		return this.mapper.map(updatedEmployee, EmployeeDTO.class);
	}

	@Override
	public void delete(int empid) {
		// TODO Auto-generated method stub
		Employee emp = this.employeeRepository.findById(empid).orElseThrow(()-> new ResourceNotFoundException("Employee with ID "+empid+ " is not found!"));
		this.employeeRepository.delete(emp);

	}

	@Override
	public EmployeeDTO getById(int empid) {
		// TODO Auto-generated method stub
		Employee emp = this.employeeRepository.findById(empid).orElseThrow(()-> new ResourceNotFoundException("Employee with ID "+empid+ " is not found!"));
		return this.mapper.map(emp, EmployeeDTO.class);
	}

	@Override
	public List<EmployeeDTO> getAll(String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = null;
		if(sortDir.trim().toLowerCase().equals("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable pageable = PageRequest.of(0, 10, sort);
		Page<Employee> allPages = this.employeeRepository.findAll(pageable);
		List<Employee> allEmployees = allPages.getContent();
		List<EmployeeDTO> allDtos = allEmployees.stream().map(emp -> this.mapper.map(emp, EmployeeDTO.class)).collect(Collectors.toList());
		return allDtos;
	}

	@Override
	public List<EmployeeDTO> getByFirstname(String firstname) {
		// TODO Auto-generated method stub
		List<Employee> employees = this.employeeRepository.findByFirstName(firstname);
		List<EmployeeDTO> employeesDto = employees.stream().map(emp -> this.mapper.map(emp, EmployeeDTO.class)).collect(Collectors.toList());
		return employeesDto;
	}

	@Override
	public List<EmployeeDTO> getAll() {
		// TODO Auto-generated method stub
		List<Employee> allEmployees = this.employeeRepository.findAll();
		List<EmployeeDTO> allDtos = allEmployees.stream().map(emp -> this.mapper.map(emp, EmployeeDTO.class)).collect(Collectors.toList());
		return allDtos;
		
	}
	
	

}
