package com.cmorr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cmorr.controllers.EmployeeController;
import com.cmorr.controllers.FormController;
import com.cmorr.utils.KeyspaceSetup;
import io.javalin.Javalin;

public class DriverTRMS {

	private static final Logger log = LogManager.getLogger(DriverTRMS.class);

	public static void main(String[] args) {
		// KeyspaceSetup.dropTable("employees");
		// KeyspaceSetup.dropTable("forms");
		KeyspaceSetup.dbtest();
		KeyspaceSetup.employeesTable();
		KeyspaceSetup.formsTable();

		javalin();

	}

	public static void javalin() {
		log.trace("Reticulating splines...");

		Javalin app = Javalin.create().start(3000);

		// Employee Controller
		app.put("/employees", EmployeeController::addEmployee);
		app.get("/employees", EmployeeController::getEmployees);
		app.get("/employees/:empId", EmployeeController::getEmployeeById);
		app.post("/employees/:empId/login", EmployeeController::login);
		app.delete("/employees/logout", EmployeeController::logout);
		app.delete("/employees/:empId", EmployeeController::removeEmployeeById);

		// Form Controller
		app.put("/forms/add", FormController::addForm);
		app.get("/forms", FormController::getForms);
		app.put("/employees/forms/attachments/:key", FormController::uploadFile);
		app.get("/employees/forms/attachments/:key", FormController::getFile);

	}
}
