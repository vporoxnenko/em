package com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.nsisc.emconnector.core.web.gwt.client.events.GridDoubleClickListener;
import com.nsisc.emconnector.core.web.gwt.client.events.GridSelectionChangedListener;
import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;
import com.nsisc.emconnector.core.web.gwt.client.support.FilterData;
import com.nsisc.emconnector.core.web.gwt.client.widgets.EntityUIComponent;


public interface EntityBrowser<V extends Widget, D extends DefaultClientDataModel<? extends Serializable>, F extends FilterData, M extends DataModelWidgetManager>
	extends EntityUIComponent<V> {

    static final int NEW_BUTTON = 1;
    static final int EDIT_BUTTON = 2;
    static final int DELETE_BUTTON = 4;
    static final int FILTER_BUTTON = 8;

    void showEditor(D entity);
    
    void showEditorOnView(D entity);
    
    void showFilterDialog();

    void delete(List<D> entity);

    void refresh();

    void setButtons(int buttons);
    
    int getButtons();

    void addSelectionChangedListener(GridSelectionChangedListener<D> listener);

    void addDoubleClickListener(GridDoubleClickListener<D> listener);

    EntityEditor<D, M> getEditor();

    EntityFilter<F> getFilter();
}
