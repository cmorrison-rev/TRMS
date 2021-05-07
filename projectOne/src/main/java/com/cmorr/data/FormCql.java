package com.cmorr.data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.cassandra.utils.UUIDGen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.cmorr.beans.Form;
import com.cmorr.beans.Form.Status;
import com.cmorr.beans.Form.Type;
import com.cmorr.utils.CassandraUtil;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class FormCql implements FormDao {
	private CqlSession session = CassandraUtil.getInstance().getSession();
	private static final Logger log = LogManager.getLogger(FormCql.class);
	private static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

	@Override
	public void addForm(Form f) {
		log.trace("Inserting new form into database...");
		f.setFormId(UUIDGen.getTimeUUID());
		f.setCreation(LocalDateTime.ofInstant(Instant.ofEpochMilli(Uuids.unixTimestamp(f.getFormId())),
				ZoneId.systemDefault()));
		if (f.getCreation().until(f.getEventDate(), ChronoUnit.DAYS) <= 14) {
			f.setUrgent(true);
		}
		f.setAmount(f.getAmount() * f.getType().d);
		if (f.getStatus() == null)
			f.setStatus(Status.PENDING);

		String query = new StringBuilder("INSERT INTO forms ")
				.append("(formId, empId, type, status, amount, superApp, dheadApp, "
						+ "bencoApp, creation, eventDate, passed, urgent, comments, attachments) ")
				.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?); ").toString();
		BoundStatement bound = session.prepare(query).bind(f.getFormId(), f.getEmpId(), f.getType().toString(),
				f.getStatus().toString(), f.getAmount(), f.isSuperApp(), f.isDheadApp(), f.isBencoApp(),
				f.getCreation().format(format), f.getEventDate().format(format), f.isPassed(), f.isUrgent(),
				f.getComments(), f.getAttachments());
		session.execute(bound);
		log.trace("Form added successfully!");
	}

	@Override
	public List<Form> getForms() {
		log.trace("Retrieving all forms...");
		List<Form> forms = new ArrayList<Form>();
		String query = "SELECT * FROM forms";
		ResultSet res = session.execute(query);
		res.forEach(data -> {
			Form f = new Form();
			f.setFormId(data.getUuid("formid"));
			f.setEmpId(data.getUuid("empid"));
			f.setType(Type.valueOf(data.getString("type")));
			f.setStatus(Status.valueOf(data.getString("status")));
			f.setAmount(data.getDouble("amount"));
			f.setSuperApp(data.getBoolean("superapp"));
			f.setDheadApp(data.getBoolean("dheadapp"));
			f.setBencoApp(data.getBoolean("bencoapp"));
			f.setCreation(LocalDateTime.parse(data.getString("creation"), format));
			f.setEventDate(LocalDateTime.parse(data.getString("eventDate"), format));
			f.setPassed(data.getBoolean("passed"));
			f.setPassed(data.getBoolean("urgent"));
			f.setComments(data.getString("comments"));
			f.setAttachments(data.getList("attachments", String.class));
			forms.add(f);
		});
		log.trace("Returning forms as list.");
		return forms;
	}

	@Override
	public void approveForm(Form f) {
		
	}

	@Override
	public Form getFormById(UUID formId) {
		log.trace("Retrieving form by form ID: " + formId);
		Form f = null;
		String query = "SELECT * FROM forms WHERE formid = ?;";
		BoundStatement bound = session.prepare(query).bind(formId);
		ResultSet res = session.execute(bound);
		Row data = res.one();
		if (data != null) {
			f = new Form();
			f.setFormId(data.getUuid("formid"));
			f.setEmpId(data.getUuid("empid"));
			f.setType(Type.valueOf(data.getString("type")));
			f.setStatus(Status.valueOf(data.getString("status")));
			f.setAmount(data.getDouble("amount"));
			f.setSuperApp(data.getBoolean("superapp"));
			f.setDheadApp(data.getBoolean("dheadapp"));
			f.setBencoApp(data.getBoolean("bencoapp"));
			f.setCreation(LocalDateTime.parse(data.getString("creation"), format));
			f.setEventDate(LocalDateTime.parse(data.getString("eventDate"), format));
			f.setPassed(data.getBoolean("passed"));
			f.setPassed(data.getBoolean("urgent"));
			f.setComments(data.getString("comments"));
			f.setAttachments(data.getList("attachments", String.class));
		}
		return f;
	}

}
