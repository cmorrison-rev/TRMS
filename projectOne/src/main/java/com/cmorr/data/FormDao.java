package com.cmorr.data;

import java.util.List;
import java.util.UUID;

import com.cmorr.beans.Form;

public interface FormDao {
	// add form
	void addForm(Form f);
	// update form
	// approve form
	void approveForm(Form f);
	// remove form
	// get form by ID
	Form getFormById(UUID formId);
	// get form by Type
	// get form by approval status

	List<Form> getForms();
}
