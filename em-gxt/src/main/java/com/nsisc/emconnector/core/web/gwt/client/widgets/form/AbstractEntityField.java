package com.nsisc.emconnector.core.web.gwt.client.widgets.form;

import java.io.Serializable;

import com.extjs.gxt.ui.client.widget.form.Field;
import com.nsisc.emconnector.core.web.gwt.client.widgets.ColumnMetaData;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityField;


@SuppressWarnings("rawtypes")
public abstract class AbstractEntityField<F extends Serializable, W extends Field>
		implements EntityField<F, W> {

	private static final int DEFAULT_COLUMN_WIDTH = 75;
	
	private ColumnMetaData columnMetaDataDelegat;
	private W editorFieldWidgetDelegat;
	private boolean isAllowBlank = true;

	private boolean isRowHeader = true;

	public AbstractEntityField(W editorField) {
		editorFieldWidgetDelegat = editorField;
		columnMetaDataDelegat = new ColumnMetaData();
		columnMetaDataDelegat.setWidth(DEFAULT_COLUMN_WIDTH);
	}
	
	public AbstractEntityField(W editorField, String fieldId, String longName, String shortName, int columnWidth, boolean allowBlanks){
		this(editorField);
		columnMetaDataDelegat.setId(fieldId);
		editorFieldWidgetDelegat.setFieldLabel(longName);
		columnMetaDataDelegat.setHeader(shortName);
		columnMetaDataDelegat.setWidth(columnWidth);
		this.isAllowBlank = allowBlanks;
	}
	
	public AbstractEntityField(W editorField, String fieldId, String shortName, int columnWidth){
		this(editorField, fieldId, shortName, shortName, columnWidth, true);
	}
	
	public AbstractEntityField(W editorField, String fieldId, String shortName){
		this(editorField, fieldId, shortName, DEFAULT_COLUMN_WIDTH);
	}

	public W getEditorFieldWidget() {
		return editorFieldWidgetDelegat;
	}

	public ColumnMetaData getColumnMetaData() {
		return columnMetaDataDelegat;
	}

	public boolean isEnabled() {
		return editorFieldWidgetDelegat.isEnabled();
	}

	public int getWidthInEditor() {
		return editorFieldWidgetDelegat.getWidth();
	}

	public int getWidthInTable() {
		return columnMetaDataDelegat.getWidth();
	}

	public String getLongName() {
		return editorFieldWidgetDelegat.getFieldLabel();
	}

	public String getShortName() {
		return columnMetaDataDelegat.getHeader();
	}

	public String getFieldId() {
		return columnMetaDataDelegat.getId();
	}

	public boolean isAllowBlank() {
		return isAllowBlank;
	}

	public void setAllowBlank(boolean isAllowBlank) {
		this.isAllowBlank = isAllowBlank;
	}

	public void setEnabled(boolean isEnabled) {
		editorFieldWidgetDelegat.setEnabled(isEnabled);
	}

	public void setWidthInEditor(int width) {
		editorFieldWidgetDelegat.setWidth(width);
	}

	public void setLongName(String longName) {
		editorFieldWidgetDelegat.setFieldLabel(longName);
	}

	public void setShortName(String shortName) {
		columnMetaDataDelegat.setHeader(shortName);
	}

	public void setFieldId(String fieldId) {
		columnMetaDataDelegat.setId(fieldId);
	}

	public void setColumnWidth(int columnWidth) {
		columnMetaDataDelegat.setWidth(columnWidth);
	}

	@SuppressWarnings("unchecked")
	public F getDataValue() {
		return (F) editorFieldWidgetDelegat.getValue();
	}

	@SuppressWarnings("unchecked")
	public void setDataValue(F dataValue) {
		editorFieldWidgetDelegat.setValue(dataValue);
	}
	
	public boolean isRowHeader(){
		return isRowHeader ;
	}
	
	public void setRowHeader(boolean isRowHeader){
		this.isRowHeader = isRowHeader;
	}

}
