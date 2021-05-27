package com.cmorr.services;

import java.util.List;
import java.util.UUID;

import com.cmorr.beans.Employee;

public interface EmployeeService {
	// addEmployee
	void addEmployee(Employee e);

	// getEmployees
	List<Employee> getEmployees();

	Employee getEmployeeById(UUID empId);

	void removeEmployeeById(UUID empId);
}
