package com.cmorr.services;

import java.util.List;

import com.cmorr.beans.Form;

public interface FormService {

	void addForm(Form f);

	List<Form> getForms();

}
