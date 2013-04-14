package com.nsisc.emconnector.core.web.gwt.client.widgets.support;

import java.io.Serializable;
import java.util.List;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.nsisc.emconnector.core.web.gwt.client.events.FileSubmitListener;
import com.nsisc.emconnector.core.web.gwt.client.exceptions.CommitDataException;
import com.nsisc.emconnector.core.web.gwt.client.exceptions.FormValidationException;
import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;
import com.nsisc.emconnector.core.web.gwt.client.widgets.EditorPanel;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.DataModelWidgetManager;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityEditor;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityField;

public abstract class AbstractEntityEditor<D extends DefaultClientDataModel<? extends Serializable>, M extends DataModelWidgetManager>
		implements EntityEditor<D, M> {

	private EditorPanel component;
	private boolean isNewEntity = true;
	private D dataModel;
	private boolean isDirty = false;
	private M widgetManager;

	private boolean isEditable = true;
	private String actionUrl;

	public AbstractEntityEditor(M widgetManager) {
		this.widgetManager = widgetManager;
		createBaseUIComponent();
		dataModel = instantiateDataModel();
	}

	private void createBaseUIComponent() {
		component = new EditorPanel();
		component.setFrame(true);
		component.setHeading(getTitle());
		component.setLayout(new FlowLayout());

		createUIContent(component);

	}

	public EditorPanel getUIComponent() {
		return component;
	}

	public void setNewEntityCreation(boolean isNewEntity) {
		this.isNewEntity = isNewEntity;
	}

	public boolean isNewEntity() {
		return isNewEntity;
	}

	public D getDataModel() {
		return dataModel;
	}

	public void setDataModel(D dataModel) {
		this.dataModel = dataModel;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void bindDataToForm() {
		List<EntityField> fields = widgetManager.getEntityFields();
		for(EntityField field : fields){
			field.setDataValue((Serializable) dataModel.get(field.getFieldId()));
		}
	}

	public void setEditable(boolean editable) {
		this.isEditable = editable;
		getWidgetManager().disableComponents(!editable);
	}

	public boolean isEditable() {
		return isEditable;
	}

	@SuppressWarnings("rawtypes")
	public void commit(){
		List<EntityField> fields = widgetManager.getEntityFields();
		for(EntityField field : fields){
			dataModel.set(field.getFieldId(), field.getDataValue());
		}
	}

	public boolean isDirty() {
		return isDirty;
	}

	public M getWidgetManager() {
		return widgetManager;
	}

	public void validate() throws FormValidationException {
		validateFieldsOnNull();
	}

	@SuppressWarnings("rawtypes")
	protected void validateFieldsOnNull() throws FormValidationException {
		StringBuilder errorMessage = new StringBuilder();
		
		List<EntityField> fields = widgetManager.getEntityFields();
		for(EntityField field : fields){
			if (field.isEnabled() && !field.isAllowBlank()
					&& (field.getDataValue() == null))
				errorMessage.append(field.getLongName()).append(
						" shouldn't be empty<br/>");
		}
		
		if (errorMessage.length() > 0)
			throw new FormValidationException(errorMessage.toString());
	}

	public void addFileSubmitResultListener(FileSubmitListener listener){
		component.addListener(Events.Submit, listener);
	}
	
	public void removeFileSubmitResultListener(FileSubmitListener listener){
		component.removeListener(Events.Submit, listener);	
	}
	
	public void submitFile(){
		component.submit();
	}
	
	@Override
	public void processFileSubmitHTTPResponse(String response) throws CommitDataException{
		// empty by default
	}
	
	@Override
	public String getActionUrl() {
		return actionUrl;
	}

	@Override
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
		component.setEncoding(Encoding.MULTIPART);
		component.setMethod(Method.POST);
		component.setAction(actionUrl);
	}

	
	public int getHeight() {
		return 450;
	}

	public int getWidth() {
		return 700;
	}

	public int getParentWindowHeight() {
		return getHeight() + 10;
	}

	public int getParentWindowWidth() {
		return getWidth() + 10;
	}

}
