package com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.proxy;

import java.io.Serializable;

import com.nsisc.emconnector.core.web.gwt.client.events.FileSubmitListener;
import com.nsisc.emconnector.core.web.gwt.client.exceptions.CommitDataException;
import com.nsisc.emconnector.core.web.gwt.client.exceptions.FormValidationException;
import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;
import com.nsisc.emconnector.core.web.gwt.client.widgets.EditorPanel;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.DataModelWidgetManager;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityEditor;

public abstract class AbstractEntityEditorProxy<D extends DefaultClientDataModel<? extends Serializable>, M extends DataModelWidgetManager>
		implements EntityEditor<D, M>,
		EnitityComponentProxy<EntityEditor<D, M>> {

	private EntityEditor<D, M> editor;
	private D entity;
	private boolean isNewEntity = false;
	private boolean isEditable = true;

	public void bindDataToForm() {
		getRealObject().bindDataToForm();
	}

	public void commit(){
		getRealObject().commit();
	}

	public void createUIContent(EditorPanel parent) {
		getRealObject().createUIContent(parent);
	}

	public D getDataModel() {
		return getRealObject().getDataModel();
	}

	public M getWidgetManager() {
		return getRealObject().getWidgetManager();
	}

	public boolean isDirty() {
		return getRealObject().isDirty();
	}

	public boolean isNewEntity() {
		return getRealObject().isNewEntity();
	}

	public void setDataModel(D entity) {
		this.entity = entity;
	}

	public void setNewEntityCreation(boolean isNewEntity) {
		this.isNewEntity = isNewEntity;
	}

	public EditorPanel getUIComponent() {
		return getRealObject().getUIComponent();
	}

	public D instantiateDataModel() {
		return getRealObject().instantiateDataModel();
	}

	public void validate() throws FormValidationException {
		getRealObject().validate();
	}

	public int getHeight() {
		return getRealObject().getHeight();
	}

	public int getWidth() {
		return getRealObject().getWidth();
	}

	public int getParentWindowHeight() {
		return getRealObject().getParentWindowHeight();
	}

	public int getParentWindowWidth() {
		return getRealObject().getParentWindowWidth();
	}

	public String getTitle() {
		return getRealObject().getTitle();
	}

	public EntityEditor<D, M> getRealObject() {
		if (editor == null) {
			editor = instantiateEditor();
		}
		editor.setDataModel(entity);
		editor.setNewEntityCreation(isNewEntity);
		editor.setEditable(isEditable);
		return editor;
	}

	public void setEditable(boolean editable) {
		this.isEditable = editable;
	}

	public boolean isEditable() {
		return isEditable;
	}
	
	public void addFileSubmitResultListener(FileSubmitListener listener){
		getRealObject().addFileSubmitResultListener(listener);
	}
	
	public void removeFileSubmitResultListener(FileSubmitListener listener){
		getRealObject().removeFileSubmitResultListener(listener);
	}
	
	@Override
	public void submitFile() {
		getRealObject().submitFile();
	}
	
	@Override
	public void processFileSubmitHTTPResponse(String response) throws CommitDataException{
		getRealObject().processFileSubmitHTTPResponse(response);
	}
	
	@Override
	public String getActionUrl() {
		return getRealObject().getActionUrl();
	}

	@Override
	public void setActionUrl(String action) {
		getRealObject().setActionUrl(action);
	}

	public abstract EntityEditor<D, M> instantiateEditor();
}
