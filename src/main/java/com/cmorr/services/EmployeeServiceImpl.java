package com.cmorr.services;

import java.util.List;
import java.util.UUID;

import com.cmorr.beans.Employee;
import com.cmorr.data.EmployeeCql;
import com.cmorr.data.EmployeeDao;

public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao edao = new EmployeeCql();

	@Override
	public void addEmployee(Employee e) {
		edao.addEmployee(e);
	}

	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = edao.getEmployees();
		return employees;
	}

	@Override
	public Employee getEmployeeById(UUID empId) {
		Employee e = edao.getEmployeeById(empId);
		return e;
	}

	@Override
	public void removeEmployeeById(UUID empId) {
		edao.removeEmployeeById(empId);
	}

}
