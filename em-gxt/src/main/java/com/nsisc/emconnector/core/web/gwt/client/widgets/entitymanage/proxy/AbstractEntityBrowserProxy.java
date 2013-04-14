package com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.proxy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.nsisc.emconnector.core.web.gwt.client.events.GridDoubleClickListener;
import com.nsisc.emconnector.core.web.gwt.client.events.GridSelectionChangedListener;
import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;
import com.nsisc.emconnector.core.web.gwt.client.support.FilterData;
import com.nsisc.emconnector.core.web.gwt.client.widgets.UIContainer;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.DataModelWidgetManager;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityBrowser;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityEditor;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityFilter;

public abstract class AbstractEntityBrowserProxy<F extends FilterData, M extends DataModelWidgetManager, D extends DefaultClientDataModel<? extends Serializable>, E extends EntityBrowser<UIContainer, D, F, M>>
		implements EntityBrowser<UIContainer, D, F, M>,
		EnitityComponentProxy<E> {

	private List<GridDoubleClickListener<D>> doubleClicklistenerList = new ArrayList<GridDoubleClickListener<D>>();
	private E instance;

	public String getTitle() {
		return internalGetRealObject().getTitle();
	}

	public void refresh() {
		internalGetRealObject().refresh();
	}

	public void showFilterDialog() {
		internalGetRealObject().showFilterDialog();
	}

	public UIContainer getUIComponent() {
		return internalGetRealObject().getUIComponent();
	}

	public void delete(List<D> entity) {
		internalGetRealObject().delete(entity);
	}

	public void showEditor(D entity) {
		internalGetRealObject().showEditor(entity);
	}

	public int getHeight() {
		return internalGetRealObject().getHeight();
	}

	public int getWidth() {
		return internalGetRealObject().getWidth();
	}

	public void addSelectionChangedListener(
			final GridSelectionChangedListener<D> listener) {
		internalGetRealObject().addSelectionChangedListener(listener);
	}

	public void addDoubleClickListener(final GridDoubleClickListener<D> listener) {
		doubleClicklistenerList.add(listener);
    }

	public EntityEditor<D, M> getEditor() {
		return internalGetRealObject().getEditor();
	}

	public EntityFilter<F> getFilter() {
		return internalGetRealObject().getFilter();
	}

	public int getButtons() {
		return internalGetRealObject().getButtons();
	}

	public void setButtons(int buttons) {
		internalGetRealObject().setButtons(buttons);
	}

	public void showEditorOnView(D entity) {
		internalGetRealObject().showEditorOnView(entity);
	}

	private E internalGetRealObject() {
		if (instance == null){
			instance = getRealObject();
			for(GridDoubleClickListener<D> l : doubleClicklistenerList)
				instance.addDoubleClickListener(l);
		}
		return instance;
	}

	public abstract E getRealObject();

}
