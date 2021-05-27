package com.cmorr.controllers;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cmorr.beans.Employee;
import com.cmorr.beans.Form;
import com.cmorr.beans.Form.Status;
import com.cmorr.beans.Form.Type;
import com.cmorr.services.FormService;
import com.cmorr.services.FormServiceImpl;
import com.cmorr.utils.S3Util;

import io.javalin.http.Context;

public class FormController {
	private static FormService fserv = new FormServiceImpl();
	private static final Logger log = LogManager.getLogger(FormController.class);
	private static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

	public static void addForm(Context ctx) {
		Employee eLog = ctx.sessionAttribute("Employee");
		if (ctx.sessionAttribute("Employee") == null) {
			log.warn("You must be logged in to add a new form");
			ctx.status(401);
			return;
		} else {
			log.trace("Adding new form for " + eLog.getfName() + " " + eLog.getlName());
			Form f = new Form();
			f.setEmpId(eLog.getEmpId());
			f.setType(Type.valueOf(ctx.formParam("type")));
			f.setStatus(Status.valueOf(ctx.formParam("status")));
			f.setAmount(Double.parseDouble(ctx.formParam("amount")));
			f.setEventDate(LocalDateTime.parse(ctx.formParam("eventDate"), format));
			try {
				fserv.addForm(f);
			} catch (Exception x) {
				log.error("Form addition failed...");
				ctx.status(409);
				x.printStackTrace();
				return;
			}
			log.trace("Form addition successful!");
			ctx.json(f);
		}
	}

	public static void getForms(Context ctx) {
		log.trace("No form specified. Returning all forms...");
		ctx.json(fserv.getForms());
	}

	public static void uploadFile(Context ctx) {
		String name = new StringBuilder(ctx.pathParam("key")).toString();
		byte[] bytes = ctx.bodyAsBytes();
		try {
			S3Util.getInstance().uploadToBucket(name, bytes);
		} catch (Exception e) {
			ctx.status(500);
		}
		ctx.status(204);
	}

	public static void getFile(Context ctx) {
		String name = new StringBuilder(ctx.pathParam("key")).toString();
		try {
			InputStream s = S3Util.getInstance().getObject(name);
			ctx.result(s);
		} catch (Exception e) {
			ctx.status(500);
		}
	}
}
