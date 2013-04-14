package com.nsisc.emconnector.core.web.gwt.client.widgets.form;

import java.io.Serializable;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.TriggerField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.nsisc.emconnector.core.web.gwt.client.events.GridDoubleClickListener;
import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;
import com.nsisc.emconnector.core.web.gwt.client.support.FilterData;
import com.nsisc.emconnector.core.web.gwt.client.widgets.EntityFieldWidget;
import com.nsisc.emconnector.core.web.gwt.client.widgets.UIContainer;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.DataModelWidgetManager;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityBrowser;

public class BrowserEntityFieldWidget<D extends DefaultClientDataModel<? extends Serializable>, E extends EntityBrowser<? extends UIContainer, D, ? extends FilterData, ? extends DataModelWidgetManager>>
		extends TriggerField<D> implements EntityFieldWidget<D> {

	private class WinBrowser extends Window{
		private E eb;
		private D selectedModel;
		private boolean initialized;
		
		public WinBrowser(E eb){
			initialized = false;
			this.eb = eb;
			setPlain(true);
			setLayout(new FitLayout());

			setModal(true);
			addListener(Events.Hide, new Listener<WindowEvent>() {

				public void handleEvent(WindowEvent be) {
					dataModel = selectedModel;
					selectedModel = null;
					updateFieldDisplayValue();
				}
			});
			
			eb.addDoubleClickListener(new GridDoubleClickListener<D>() {
				public void handleEvent(GridEvent<D> ge) {
					selectedModel = ge.getModel();
					if(isVisible())
						hide();
				}

			});
		}
		
		public E getEntityBrowser() {
			return eb;
		}

		public void showBrowser() {
			initWindowIfRequired();
			eb.refresh();
			show();			
		}

		private void initWindowIfRequired() {
			if(!initialized){
				setWidth(eb.getWidth());
				setHeight(eb.getHeight());
				add(eb.getUIComponent());
				initialized = true;
			}
		}
	}
	
	private D dataModel;
	private boolean wasRendered = false;
	private WinBrowser window;

	/**
	 * Creates a new date field.
	 */
	public BrowserEntityFieldWidget(E entityBrowser) {
		autoValidate = false;
		setTriggerStyle("x-form-search-trigger");
		
		setEntityBrowser(entityBrowser);
	}

	public E getEntityBrowser() {
		return window.getEntityBrowser();
	}

	public void setEntityBrowser(E entityBrowser) {
		window = new WinBrowser(entityBrowser);
	}

	@Override
	protected void afterRender() {
		super.afterRender();
		updateFieldDisplayValue();
		wasRendered = true;
	}

	private void updateFieldDisplayValue() {
		getInputEl().disable();
		setEditable(false);
		if (dataModel != null)
			getInputEl().setValue(dataModel.toString());
		else
			getInputEl().setValue("");
	}

	public D getDataModel() {
		if (getInputEl() != null && getInputEl().getValue() != null
				&& getInputEl().getValue().length() == 0)
			dataModel = null;
		return dataModel;
	}

	public void setDataModel(D dataModel) {
		this.dataModel = dataModel;
		if (wasRendered)
			updateFieldDisplayValue();
	}

	protected void expand() {
		window.showBrowser();
	}

	protected void onDown(FieldEvent fe) {
		fe.cancelBubble();
		expand();
	}

	@Override
	protected void onTriggerClick(ComponentEvent ce) {
		super.onTriggerClick(ce);
		if (isReadOnly()) {
			return;
		}

		expand();

		getInputEl().focus();
	}

}
