package com.cmorr.controllers;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cmorr.beans.Employee;
import com.cmorr.services.EmployeeService;
import com.cmorr.services.EmployeeServiceImpl;

import io.javalin.http.Context;

public class EmployeeController {
	private static EmployeeService eserv = new EmployeeServiceImpl();
	private static final Logger log = LogManager.getLogger(EmployeeController.class);

	public static void login(Context ctx) {
		log.trace("Logging in as " + ctx.pathParam("empId"));
		if (ctx.sessionAttribute("Employee") != null) {
			ctx.status(409);
			log.error("Login conflict, please logout of current user before logging in again.");
			return;
		}
		Employee e = eserv.getEmployeeById(UUID.fromString(ctx.pathParam("empId")));
		System.out.println(e);
		try {
			e.getEmpId();
		} catch (Exception x) {
			log.debug("User does not exist, please register first.");
			ctx.status(204);
			x.printStackTrace();
			return;
		}
		log.debug("Login Successful!");
		ctx.sessionAttribute("Employee", e);
		log.trace("Now logged in as " + e.getfName() + e.getlName());
		ctx.json(e);
	}

	public static void logout(Context ctx) {
		if (ctx.sessionAttribute("Employee") == null) {
			log.debug("No one is currently logged in, I can't be anymore logged outed.");
			ctx.status(204);
			return;
		}
		log.trace("Logging out, adios amigo!");
		ctx.req.getSession().invalidate();
	}

	public static void addEmployee(Context ctx) {
		log.trace("Adding employee...");
		Employee e = ctx.bodyAsClass(Employee.class);
		Employee eLog = ctx.sessionAttribute("Employee");
		if (eLog.getPosition().equals(Employee.Position.EMPLOYEE) || eLog == null) {
			log.warn("I can't let you do that, Dave");
			ctx.status(401);
			return;
		} else {

			try {
				eserv.addEmployee(e);
			} catch (Exception x) {
				log.error("Employee addition failed...");
				ctx.status(409);
				//x.printStackTrace();
				return;
			}
			log.trace("Employee addition successful!");
			ctx.json(e);
		}
	}

	public static void getEmployees(Context ctx) {
		if (ctx.sessionAttribute("Employee") == null) {
			log.warn("You must be logged in to view employees");
			ctx.status(401);
			return;
		} else {
			log.trace("No user specified. Returning all users...");
			ctx.json(eserv.getEmployees());
		}
	}

	public static void getEmployeeById(Context ctx) {
		UUID empId = UUID.fromString(ctx.pathParam("empId"));
		if (ctx.sessionAttribute("Employee") == null) {
			log.warn("You must be logged in to view employees");
			ctx.status(401);
			return;
		} else {
			log.trace("Retrieving user by UUID: " + ctx.pathParam("empId"));
			if (ctx.pathParam("empId") == null) {
				log.trace("No user found. Please use valid Employee UUID");
				ctx.status(400);
				return;
			} else {
				try {
					ctx.json(eserv.getEmployeeById(empId));
					log.trace("User found!");
				} catch (Exception x) {
					log.error("Exception thrown, invalid employee ID");
					// e.printStackTrace();
					ctx.status(400);
					return;
				}
			}
		}
	}

	public static void removeEmployeeById(Context ctx) {
		Employee eLog = ctx.sessionAttribute("Employee");
		UUID empId = UUID.fromString(ctx.pathParam("empId"));
		log.trace("Removing user by UUID: " + empId);

		if (!eLog.getPosition().equals(Employee.Position.DEPT_HEAD) || eLog == null) {
			log.warn("I can't let you do that, Dave");
			ctx.status(401);
			return;
		} else {

			if (ctx.pathParam("empId") == null) {
				log.trace("No user found. Please use valid Employee UUID");
				ctx.status(400);
				return;
			} else {
				try {
					eserv.removeEmployeeById(empId);
				} catch (Exception x) {
					log.error("Exception thrown, invalid employee ID");
					// e.printStackTrace();
					ctx.status(400);
					return;
				}
				log.trace("Removing user by UUID: " + ctx.pathParam("empId"));
			}
		}
	}

}
