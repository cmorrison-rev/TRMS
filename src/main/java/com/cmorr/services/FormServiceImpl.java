package com.cmorr.services;

import java.util.List;

import com.cmorr.beans.Form;
import com.cmorr.data.FormCql;
import com.cmorr.data.FormDao;

public class FormServiceImpl implements FormService {
	private FormDao fdao = new FormCql();

	@Override
	public void addForm(Form f) {
		fdao.addForm(f);
	}

	@Override
	public List<Form> getForms() {
		List<Form> forms = fdao.getForms();
		return forms;
	}

}
