package com.AngularRestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AngularRestApi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer >{

	Employee findByName(String name);
}
