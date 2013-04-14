package com.nsisc.emconnector.core.web.gwt.client.widgets.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.nsisc.emconnector.core.web.gwt.client.CoreUIServiceAsync;
import com.nsisc.emconnector.core.web.gwt.client.events.FileSubmitListener;
import com.nsisc.emconnector.core.web.gwt.client.events.GridDoubleClickListener;
import com.nsisc.emconnector.core.web.gwt.client.events.GridSelectionChangedListener;
import com.nsisc.emconnector.core.web.gwt.client.events.SelChangedEvnt;
import com.nsisc.emconnector.core.web.gwt.client.exceptions.CommitDataException;
import com.nsisc.emconnector.core.web.gwt.client.exceptions.FormValidationException;
import com.nsisc.emconnector.core.web.gwt.client.support.DefaultClientDataModel;
import com.nsisc.emconnector.core.web.gwt.client.support.FilterData;
import com.nsisc.emconnector.core.web.gwt.client.support.fetching.GridDataFetchingConfig;
import com.nsisc.emconnector.core.web.gwt.client.support.fetching.GridDataFetchingResult;
import com.nsisc.emconnector.core.web.gwt.client.widgets.AlertDialog;
import com.nsisc.emconnector.core.web.gwt.client.widgets.UIContainer;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.DataModelWidgetManager;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityBrowser;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityEditor;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityField;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityFilter;
import com.nsisc.emconnector.core.web.gwt.client.widgets.entitymanage.EntityViewer;

public abstract class AbstractEntityBrowser<K extends Serializable, F extends FilterData, D extends DefaultClientDataModel<K>, M extends DataModelWidgetManager, A extends CoreUIServiceAsync<K, D, F>>
		implements EntityBrowser<UIContainer, D, F, M> {

	private static final int MAX_TABLE_WIDTH = 1800;
	private static final int DEFAULT_PAGINATION_LIMIT = 75;
	private static final int DEFAULT_HEIGHT = 550;

	private CoreUIServiceAsync<K, D, F> serviceAsync;
	private ColumnModel columnModel;
	private UIContainer contentPanel;
	private EntityEditor<D, M> editor;
	private EntityViewer<UIContainer, D> viewer = null;
	private EntityFilter<F> filter;
	protected List<ColumnConfig> columnsConfigs = new ArrayList<ColumnConfig>();
	private Grid<D> grid;
	private List<D> selections = new ArrayList<D>();
	private BasePagingLoader<PagingLoadResult<D>> loader;
	private PagingToolBar bottomToolBar;
	private Window editorWindow;
	private Window filterWindow;
	private boolean isPaintWasCalled = false;
	private int buttons = 0;
	private Button dialogOkButton;

	public AbstractEntityBrowser(EntityEditor<D, M> editor, Object serviceAsync) {
		init(null, editor, null, this.buttons, serviceAsync);
	}

	public AbstractEntityBrowser(EntityFilter<F> filter,
			EntityEditor<D, M> editor, int buttons, Object serviceAsync) {
		init(filter, editor, null, buttons, serviceAsync);
	}

	public AbstractEntityBrowser(EntityFilter<F> filter,
			EntityEditor<D, M> editor, EntityViewer<UIContainer, D> eViewer,
			int buttons, Object serviceAsync) {
		init(filter, editor, eViewer, buttons, serviceAsync);
	}

	@SuppressWarnings("unchecked")
	private void init(EntityFilter<F> filter, EntityEditor<D, M> ed,
			EntityViewer<UIContainer, D> eViewer, int buttons,
			Object serviceAsync) {
		this.buttons = buttons;
		this.filter = filter;
		this.viewer = eViewer;
		this.serviceAsync = (CoreUIServiceAsync<K, D, F>) serviceAsync;
		this.editor = ed;
		dialogOkButton = new Button("OK");
		dialogOkButton.setEnabled(true);
		dialogOkButton
				.addSelectionListener(new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {
						dialogOkButton.setEnabled(false);
						doSave();
					}

				});
		createUIComponent();

		if (editor.getActionUrl() != null)
			editor.addFileSubmitResultListener(new FileSubmitListener() {

				@Override
				public void handleEvent(String response) {
					try {
						editor.processFileSubmitHTTPResponse(response);
						storeData(editor.getDataModel());
					} catch (CommitDataException e) {
						dialogOkButton.setEnabled(true);
						AlertDialog.showError("Error submit file on server", e);
					}

				}
			});
	}

	private void createUIComponent() {
		List<ColumnConfig> colConf = getColumnsConfig();
		columnModel = new ColumnModel(colConf);

		contentPanel = new UIContainer();
		contentPanel.setBodyBorder(false);
		contentPanel.setHeading(getTitle());
		contentPanel.setButtonAlign(HorizontalAlignment.CENTER);
		contentPanel.setLayout(new FitLayout());
		contentPanel.setWidth(countTableWidth(colConf));
		contentPanel.setHeight(getHeight());

		// grid
		grid = new Grid<D>(new ListStore<D>(), columnModel);
		grid.setStyleAttribute("borderTop", "none");
		grid.setBorders(true);
		// grid.setAutoExpandColumn(ClientDataModel.ID_COLUMN_NAME);
		grid.getSelectionModel().addListener(Events.SelectionChange,
				new Listener<SelectionChangedEvent<D>>() {
					public void handleEvent(SelectionChangedEvent<D> be) {
						if (be.getSelection().size() > 0) {
							selections = be.getSelection();
						}
					}
				});
	}

	private void paint() {
		isPaintWasCalled = true;
		createGrid();
		createToolBars();
	}

	private void createGrid() {
		RpcProxy<PagingLoadResult<D>> proxy = new RpcProxy<PagingLoadResult<D>>() {

			@Override
			public void load(Object loadConfig,
					final AsyncCallback<PagingLoadResult<D>> callback) {
				final PagingLoadConfig config = (PagingLoadConfig) loadConfig;
				GridDataFetchingConfig fetchConfig = new GridDataFetchingConfig();
				fetchConfig.setLimit(config.getLimit());
				fetchConfig.setOffset(config.getOffset());
				// TODO: !!!
				// fetchConfig.setSortDir(config.getSortInfo().getSortDir());
				fetchConfig.setSortFieldName(config.getSortInfo()
						.getSortField());

				F fd = null;
				if (filter != null)
					fd = filter.getFilterData();
				serviceAsync.getPagedDataModels(fetchConfig, fd,
						new AsyncCallback<GridDataFetchingResult<D>>() {

							public void onFailure(Throwable th) {
								AlertDialog.showError(
										"Getting data from server error", th);
								callback.onFailure(th);
							}

							public void onSuccess(
									GridDataFetchingResult<D> result) {
								BasePagingLoadResult<D> padding = new BasePagingLoadResult<D>(
										result.getData(), result.getOffset(),
										result.getLimit());
								callback.onSuccess(padding);
							}

						});
			}
		};

		// loader
		loader = new BasePagingLoader<PagingLoadResult<D>>(proxy);
		loader.setRemoteSort(sortDataOnServer());

		loader.load(0, getPaginationLimit());

		ListStore<D> store = new ListStore<D>(loader);

		grid.reconfigure(store, columnModel);

		grid.setLoadMask(true);
		grid.setBorders(true);

		// add to panel
		contentPanel.add(grid);
	}

	private int countTableWidth(List<ColumnConfig> colConf) {
		int width = 100;
		for (ColumnConfig cc : colConf)
			width += cc.getWidth();
		if (width > MAX_TABLE_WIDTH)
			width = MAX_TABLE_WIDTH;
		return width;
	}

	private void createToolBars() {
		createTopToolBar();
		createBottomToolBar();
	}

	private void createBottomToolBar() {
		bottomToolBar = new PagingToolBar(getPaginationLimit());
		bottomToolBar.bind(loader);
		contentPanel.setBottomComponent(bottomToolBar);
	}

	private void createTopToolBar() {
		ToolBar topToolBar = new ToolBar();
		if ((buttons | NEW_BUTTON) == buttons) {
			Button newB = new Button("New");
			newB.addSelectionListener(new SelectionListener<ButtonEvent>() {

				public void componentSelected(ButtonEvent ce) {
					showEditor(null);
				}

			});
			topToolBar.add(newB);
			topToolBar.add(new SeparatorToolItem());
		}

		if ((buttons | EDIT_BUTTON) == buttons) {
			Button editB = new Button("Edit");
			topToolBar.add(editB);
			editB.addSelectionListener(new SelectionListener<ButtonEvent>() {

				public void componentSelected(ButtonEvent ce) {
					if (selections.size() > 0)
						showEditor(selections.get(0));
				}

			});
			topToolBar.add(editB);
			topToolBar.add(new SeparatorToolItem());
		}

		if ((buttons | DELETE_BUTTON) == buttons) {
			Button delB = new Button("Delete");
			topToolBar.add(delB);
			delB.addSelectionListener(new SelectionListener<ButtonEvent>() {

				public void componentSelected(ButtonEvent ce) {
					delete(selections);
				}

			});
			topToolBar.add(delB);
			topToolBar.add(new SeparatorToolItem());
		}

		if (filter != null && ((buttons | FILTER_BUTTON) == buttons)) {
			Button filterB = new Button("Filter");
			filterB.addSelectionListener(new SelectionListener<ButtonEvent>() {

				public void componentSelected(ButtonEvent ce) {
					showFilterDialog();
				}

			});
			topToolBar.add(filterB);
		}

		contentPanel.setTopComponent(topToolBar);
	}

	private void storeData(D gridDataModel) {
		serviceAsync.saveDataModel(gridDataModel, new AsyncCallback<Void>() {

			public void onFailure(Throwable th) {
				dialogOkButton.setEnabled(true);
				AlertDialog.showError("Saving data error", th);
			}

			public void onSuccess(Void arg0) {
				dialogOkButton.setEnabled(true);
				editorWindow.hide();
				refresh();
			}

		});
	}

	public UIContainer getUIComponent() {
		if (!isPaintWasCalled)
			paint();
		return contentPanel;
	}

	private void showViewer(D dm) {
		viewer.setDataModel(dm);
		final Window window = new Window();
		window.setSize(viewer.getWidth(), viewer.getHeight());
		window.setPlain(true);
		window.setLayout(new FitLayout());
		viewer.createUIContent();
		window.add(viewer.getUIComponent());
		window.setAutoHeight(true);
		// window.setAutoWidth(true);
		window.setModal(true);
		window.setButtonAlign(HorizontalAlignment.CENTER);

		window.addButton(new Button("Close",
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {
						window.hide();
					}

				}));

		window.show();
	}

	public void showEditorOnView(D entity) {
		if (viewer != null)
			showViewer(entity);
		else
			showEditor(entity, false);
	}

	public void showEditor(D entity) {
		showEditor(entity, true);
	}

	private void showEditor(D baseModel, boolean isEditable) {
		final EntityEditor<D, M> editor = getEditor();
		boolean isNew = (baseModel == null);
		editor.setNewEntityCreation(isNew);
		if (isNew && (baseModel == null))
			baseModel = editor.instantiateDataModel();
		else
			editor.setEditable(isEditable);
		editor.setDataModel(baseModel);
		editor.bindDataToForm();

		FormPanel w = (FormPanel) editor.getUIComponent();

		editorWindow = new Window();
		editorWindow.setSize(editor.getParentWindowWidth(),
				editor.getParentWindowHeight());
		editorWindow.setPlain(true);
		editorWindow.setLayout(new FitLayout());

		editorWindow.add(w);
		editorWindow.setModal(true);
		editorWindow.setButtonAlign(HorizontalAlignment.CENTER);
		if (isEditable)
			editorWindow.addButton(dialogOkButton);
		editorWindow.addButton(new Button("Cancel",
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {
						editorWindow.hide();
					}

				}));
		editorWindow.show();

	}

	protected void doSave() {
		doTwoStepSave();
	}

	private void doTwoStepSave() {
		try {
			editor.validate();
			editor.commit();
			if (editor.getActionUrl() != null)
				editor.submitFile();
			else
				storeData(editor.getDataModel());

		} catch (FormValidationException e) {
			dialogOkButton.setEnabled(true);
			AlertDialog.showError("Form not filled well", e);
		}
	}

	public void showFilterDialog() {
		FormPanel w = (FormPanel) filter.getUIComponent();

		filterWindow = new Window();
		filterWindow.setSize(filter.getWidth() + 10, filter.getHeight() + 10);
		filterWindow.setPlain(true);
		filterWindow.setLayout(new FitLayout());

		filterWindow.add(w);
		/*
		 * filterWindow.setAutoHeight(true); filterWindow.setAutoWidth(true);
		 */
		filterWindow.setModal(true);
		filterWindow.setButtonAlign(HorizontalAlignment.CENTER);
		filterWindow.addButton(new Button("Find",
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {
						try {
							filter.commit();
							refresh();
							filterWindow.hide();
						} catch (FormValidationException e) {
							AlertDialog.showError(
									"Filter form not filled well</br>", e);
						}
					}

				}));
		filterWindow.addButton(new Button("Cancel",
				new SelectionListener<ButtonEvent>() {

					@Override
					public void componentSelected(ButtonEvent ce) {
						filterWindow.hide();
					}

				}));
		filterWindow.show();
	}

	public void delete(final List<D> models) {

		if (models != null && models.size() > 0) {
			serviceAsync.deleteDataModel(models.get(0).getPrimaryKey(),
					new AsyncCallback<Void>() {

						public void onFailure(Throwable th) {
							AlertDialog.showError("Delete error", th);
						}

						public void onSuccess(Void arg0) {
							models.remove(0);
							delete(models);
							refresh();
						}

					});
		}
	}

	public void addSelectionChangedListener(
			final GridSelectionChangedListener<D> listener) {
		grid.getSelectionModel().addListener(Events.SelectionChange,
				new Listener<SelectionChangedEvent<D>>() {
					public void handleEvent(SelectionChangedEvent<D> be) {
						listener.selectionChanged(new SelChangedEvnt<D>(be
								.getSelectionProvider(), be.getSelection()) {
						});
					}
				});
	}

	public void addDoubleClickListener(final GridDoubleClickListener<D> listener) {
		grid.addListener(Events.RowDoubleClick, new Listener<GridEvent<D>>() {

			public void handleEvent(GridEvent<D> ge) {
				listener.handleEvent(ge);
			}

		});
	}

	public EntityEditor<D, M> getEditor() {
		return editor;
	}

	public EntityViewer<UIContainer, D> getViewer() {
		return viewer;
	}

	public EntityFilter<F> getFilter() {
		return filter;
	}

	public CoreUIServiceAsync<K, D, F> getRCPService() {
		return serviceAsync;
	}

	public void refresh() {
		if (bottomToolBar != null)
			bottomToolBar.refresh();
	}

	protected int getPaginationLimit() {
		return DEFAULT_PAGINATION_LIMIT;
	}

	protected boolean sortDataOnServer() {
		return false;
	}

	public int getHeight() {
		return DEFAULT_HEIGHT;
	}

	public int getWidth() {
		return contentPanel.getWidth();
	}

	public int getButtons() {
		return buttons;
	}

	public void setButtons(int buttons) {
		this.buttons = buttons;
	}

	public List<ColumnConfig> getColumnsConfig() {
		if (columnsConfigs.size() == 0) {
			M wm = getEditor().getWidgetManager();
			for (@SuppressWarnings("rawtypes")
			EntityField field : wm.getEntityFields()) {
				if (field.isRowHeader())
					columnsConfigs.add(field.getColumnMetaData());
			}
		}
		return columnsConfigs;
	}
}
