package com.nsisc.emconnector.core.web.gwt.client.widgets.form;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;

@SuppressWarnings("rawtypes")
public class EnumeratedEntityField<T extends Enum> extends
		AbstractEntityField<T, ComboBox<ValueWrapper<T>>> {

	private T[] enumerates;
	private ListStore<ValueWrapper<T>> store;

	public EnumeratedEntityField(String fieldId, String shortName,
			T[] enumerates) {
		this(enumerates);
		setFieldId(fieldId);
		setShortName(shortName);
		setLongName(shortName);
	}

	public EnumeratedEntityField(T[] enumerates) {
		super(new ComboBox<ValueWrapper<T>>());
		this.enumerates = enumerates;
		getEditorFieldWidget().setEmptyText("Select value...");  
		getEditorFieldWidget().setDisplayField(ValueWrapper.VALUE_KEY);  
		updateCombo();
		getEditorFieldWidget().setTypeAhead(true);  
		getEditorFieldWidget().setTriggerAction(TriggerAction.ALL);  
	}

	@Override
	public T getDataValue() {
		return getEditorFieldWidget().getValue().getBaseValue();
	}

	@Override
	public void setDataValue(T dataValue) {
		if (dataValue != null && store != null)
			getEditorFieldWidget().setValue(store.getAt(dataValue.ordinal()));
		else
			getEditorFieldWidget().clearSelections();
	}

	public T[] getEnumerates() {
		return enumerates;
	}

	public void setEnumerates(T[] enumerates) {
		this.enumerates = enumerates;
		updateCombo();
	}

	private void updateCombo() {
		if (enumerates != null) {
			store = new ListStore<ValueWrapper<T>>();
			for (int i = 0; i < enumerates.length; i++)
				store.add(new ValueWrapper<T>(enumerates[i]));
			getEditorFieldWidget().setStore(store);
		}
	}

}
