package com.cmorr.utils;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

public class KeyspaceSetup {
	private static CqlSession session = CassandraUtil.getInstance().getSession();

	public static void employeesTable() {
		StringBuilder empTable = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append("employees (")
				.append("empId uuid,").append("fName varchar,").append("lName varchar,").append("superId varchar,")
				.append("dept varchar,").append("position varchar,").append("totalBudget double,")
				.append("pendingBudget double,").append("approvedBudget double,")
				.append("PRIMARY KEY (empId, dept, position))");
		CassandraUtil.getInstance().getSession().execute(empTable.toString());
	}

	public static void formsTable() {
		StringBuilder formTable = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append("forms (")
				.append("formId uuid,").append("empId uuid,").append("type varchar,").append("status varchar,")
				.append("amount double,").append("superApp boolean,").append("dheadApp boolean,")
				.append("bencoApp boolean,").append("creation varchar,").append("eventDate varchar,")
				.append("passed boolean,").append("urgent boolean,").append("comments varchar,")
				.append("attachments list<text>,").append("PRIMARY KEY (formId, empId, type, status))");
		CassandraUtil.getInstance().getSession().execute(formTable.toString());
	}

	public static void dropTable(String table) {
		String query = ("DROP TABLE IF EXISTS " + table + ";");
		session.execute(query);
	}

	public static void dbtest() {
		StringBuilder sb = new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
				.append("ReactiveTRMS with replication = {")
				.append("'class':'SimpleStrategy','replication_factor':1};");
		DriverConfigLoader loader = DriverConfigLoader.fromClasspath("application.conf");
		CqlSession.builder().withConfigLoader(loader).build().execute(sb.toString());
	}

}
