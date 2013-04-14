package com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage;

import java.io.Serializable;

import com.google.gwt.user.client.ui.Widget;
import com.nsisc.emconnector.core.web.gwt.client.widgets.ColumnMetaData;


public interface EntityField<F extends Serializable, W extends Widget> {

	String getShortName();
	
	String getLongName();
	
	W getEditorFieldWidget();
	
	String getFieldId();
	
	boolean isEnabled();

	int getWidthInEditor();
	
	int getWidthInTable();
	
	ColumnMetaData getColumnMetaData();
	
	F getDataValue();
	
	boolean isAllowBlank();
	
	boolean isRowHeader();

	void setAllowBlank(boolean isAllowBlank);
	
	void setDataValue(F dataValue);

	void setEnabled(boolean isEnabled);

	void setWidthInEditor(int width);

	void setLongName(String longName);

	void setShortName(String shortName);

	void setFieldId(String fieldId);
	
	void setColumnWidth(int columnWidth);
	
	void setRowHeader(boolean isRowHeader);

}
