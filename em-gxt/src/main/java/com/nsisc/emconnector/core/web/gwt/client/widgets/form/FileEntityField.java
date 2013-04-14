package com.nsisc.emconnector.core.web.gwt.client.widgets.form;

import com.extjs.gxt.ui.client.widget.form.FileUploadField;

public class FileEntityField extends AbstractEntityField<String, FileUploadField> {

	
	public FileEntityField() {
		super(new FileUploadField());
	}
	
	public FileEntityField(String fieldId, String shortName){
		super(new FileUploadField(), fieldId, shortName);
		getEditorFieldWidget().setName(fieldId);
	}
	
}
