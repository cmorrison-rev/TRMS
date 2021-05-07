package com.cmorr.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.cassandra.utils.UUIDGen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cmorr.beans.Employee;
import com.cmorr.beans.Employee.Position;
import com.cmorr.utils.CassandraUtil;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class EmployeeCql implements EmployeeDao {
	private static final Logger log = LogManager.getLogger(EmployeeCql.class);
	private CqlSession session = CassandraUtil.getInstance().getSession();
	// private static Integer numCount = 0;

	@Override
	public void addEmployee(Employee e) {
		log.trace("Inserting new employee to database...");
		e.setEmpId(UUIDGen.getTimeUUID());
		String query = new StringBuilder("INSERT INTO employees ")
				.append("(empId, fName, lName, superId, dept, position, totalBudget, pendingBudget, approvedBudget) ")
				.append("VALUES (?,?,?,?,?,?,?,?,?); ").toString();
		BoundStatement bound = session.prepare(query).bind(e.getEmpId(), e.getfName(), e.getlName(), e.getSuperId(),
				e.getDept(), e.getPosition().toString(), 1000.00);
		session.execute(bound);
		log.trace("Employee " + e.getfName() + " " + e.getlName() + "inserted.");
	}

	/*
	 * @Override public String generateId(Employee e) {
	 * log.trace("Generating employee ID for " + e.getfName() + " " + e.getlName() +
	 * "..."); String pre = e.getfName().substring(0,3); String suf =
	 * e.getlName().substring(0,4); String num = null;
	 * switch(numCount.toString().length()){ case 1: num = ("00" +
	 * numCount.toString()); break; case 2: num = ("0" + numCount.toString());
	 * break; case 3: num = numCount.toString(); break; } numCount++; String empId =
	 * pre + suf + num; log.trace("ID: " + empId + " generated."); return empId; }
	 */

	@Override
	public List<Employee> getEmployees() {
		log.trace("Retrieving all employees from database...");
		List<Employee> employees = new ArrayList<Employee>();
		String query = "SELECT * FROM employees";
		ResultSet res = session.execute(query);
		res.forEach(data -> {
			Employee e = new Employee();
			e.setfName(data.getString("fname"));
			e.setlName(data.getString("lname"));
			e.setEmpId(data.getUuid("empid"));
			e.setSuperId(data.getString("superid"));
			e.setDept(data.getString("dept"));
			e.setPosition(Position.valueOf(data.getString("position")));
			e.setTotalBudget(data.getDouble("totalbudget"));
			e.setPendingBudget(data.getDouble("pendingbudget"));
			e.setApprovedBudget(data.getDouble("approvedbudget"));
			employees.add(e);
		});
		log.trace("Returning employees as list.");
		return employees;
	}

	@Override
	public Employee getEmployeeById(UUID empId) {
		log.trace("Retrieving employee by ID: " + empId);
		Employee e = null;
		String query = "SELECT * FROM employees WHERE empid = ?;";
		BoundStatement bound = session.prepare(query).bind(empId);
		ResultSet res = session.execute(bound);
		Row data = res.one();
		if (data != null) {
			e = new Employee();
			e.setEmpId(data.getUuid("empid"));
			e.setfName(data.getString("fname"));
			e.setlName(data.getString("lname"));
			e.setSuperId(data.getString("superid"));
			e.setDept(data.getString("dept"));
			e.setPosition(Position.valueOf(data.getString("position")));
			e.setTotalBudget(data.getDouble("totalbudget"));
			e.setPendingBudget(data.getDouble("pendingbudget"));
			e.setApprovedBudget(data.getDouble("approvedbudget"));
		}
		return e;
	}

	@Override
	public List<Employee> getEmployeesByPosition(Position position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeEmployeeById(UUID empId) {
		log.trace("Removing employee by ID: " + empId);
		if (empId != null) {
			String query = "DELETE FROM employees WHERE empid = ?;";
			BoundStatement bound = session.prepare(query).bind(empId);
			session.execute(bound);
		}
		log.trace("Employee: " + empId + " successfully deleted.");

	}

}
