package com.AngularRestApi.controller;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.AngularRestApi.model.Employee;
import com.AngularRestApi.service.EmployeeService;

@RestController
@RequestMapping
public class EmployeeResource {

	private EmployeeService employeeservice;
	
	public EmployeeResource(EmployeeService employeeService) {
		this.employeeservice=employeeService;
	}
	
	@RequestMapping(value="emplyee",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getAllemployees(){
		return employeeservice.findAll();
	}
	
	@RequestMapping(value="employee",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)throws URISyntaxException{
		try {
			Employee result = employeeservice.save(employee);
			return ResponseEntity.created(new URI("api/employee/"+result.getId())).body(result);
		}catch(Exception e) {
			return new ResponseEntity<Employee>(HttpStatus.CONFLICT);
		}
		
	}
	
	@RequestMapping(value="employee",method=RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> UpdateEmployee(@RequestBody Employee employee)throws URISyntaxException{
		if(employee.getId()==null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	}
		try {
			Employee result = employeeservice.save(employee);
			return ResponseEntity.created(new URI("api/employee/"+result.getId())).body(result);
		}catch(Exception e) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
			
		}
	}
	
	@RequestMapping(value="/employee/{id}",method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer id){
		employeeservice.delete(id);
		return ResponseEntity.ok().build();
		}
		
}
