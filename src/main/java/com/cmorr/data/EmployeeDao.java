package com.cmorr.data;

import java.util.List;
import java.util.UUID;

import com.cmorr.beans.Employee;
import com.cmorr.beans.Employee.Position;

public interface EmployeeDao {
	// add employee
	public void addEmployee(Employee e);

	// get employees
	public List<Employee> getEmployees();

	// get employee by ID
	public Employee getEmployeeById(UUID empId);

	// get employee by position
	public List<Employee> getEmployeesByPosition(Position position);

	// remove employee by ID
	public void removeEmployeeById(UUID empId);
	// remove multiple employees by ID
}
