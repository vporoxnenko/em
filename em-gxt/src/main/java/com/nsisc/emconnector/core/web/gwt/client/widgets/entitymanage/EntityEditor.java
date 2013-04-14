package com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage;

import java.io.Serializable;

import com.nsisc.emconnector.core.web.gwt.client.events.FileSubmitListener;
import com.nsisc.emconnector.core.web.gwt.client.exceptions.CommitDataException;
import com.nsisc.emconnector.core.web.gwt.client.exceptions.FormValidationException;
import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;
import com.nsisc.emconnector.core.web.gwt.client.widgets.EditorPanel;
import com.nsisc.emconnector.core.web.gwt.client.widgets.EntityUIComponent;


public interface EntityEditor<D extends DefaultClientDataModel<? extends Serializable>, M extends DataModelWidgetManager> extends EntityUIComponent<EditorPanel>  {

	D getDataModel();
	
	void setDataModel(D entity);
	
	/**
	 * Commit form values to Client data model
	 */
	void commit();
	
	void setEditable(boolean isEditable);
	
	boolean isEditable();
	
	/**
	 * Map Client data model to form
	 */
	void bindDataToForm();
	
	/**
	 * Create form UI content
	 * 
	 * @param parent
	 */
	void createUIContent(EditorPanel parent);
	
	void setNewEntityCreation(boolean isNewEntity);
	
	boolean isNewEntity();
	
	boolean isDirty();
	
	M getWidgetManager();
	
	/**
	 * Validation method.
	 * Called before data send on server
	 * 
	 * @throws FormValidationException
	 */
	void validate() throws FormValidationException;
	
	D instantiateDataModel();
	
	int getParentWindowHeight();
	
	int getParentWindowWidth();

	String getActionUrl();
	
	void setActionUrl(String action);
	
	void addFileSubmitResultListener(FileSubmitListener listener);
	
	void removeFileSubmitResultListener(FileSubmitListener listener);

	/**
	 * This method used to submit files on server
	 * Called on  save operation if HTTP action string was set 
	 */
	void submitFile();

	/**
	 * Override this method in yours form, when required to process http response after file submit
	 * Throw CommitDataException when response is invalid
	 * @param response
	 * @throws CommitDataException
	 */
	void processFileSubmitHTTPResponse(String response) throws CommitDataException;
}
