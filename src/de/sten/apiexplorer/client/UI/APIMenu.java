package de.sten.apiexplorer.client.UI;

import java.util.ArrayList;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

import de.sten.apiexplorer.client.passiveObjects.APIMethod;
import de.sten.apiexplorer.client.passiveObjects.MainEvent;
import de.sten.apiexplorer.client.passiveObjects.RequestParameter;
import de.sten.apiexplorer.client.passiveObjects.Request_Template;

public class APIMenu extends DecoratedStackPanel {
	
	private ArrayList<String> apis;
	private SimpleEventBus eventbus;
	
	public APIMenu(SimpleEventBus eventbus) {
		this.eventbus = eventbus;
		apis = new ArrayList<String>();
	}
	
	private void onclick(SelectionEvent<TreeItem> event){
		String selectedApi = apis.get(this.getSelectedIndex());
		ArrayList<TreeItem> clickPath = new ArrayList<TreeItem>();
		getClickPath(event.getSelectedItem(), clickPath);
		
		String eventmessage = selectedApi;
		for (int i = clickPath.size()-1; i>=0; i--) {
			eventmessage+=":"+clickPath.get(i).getText();
		}	
		eventbus.fireEvent(new MainEvent("ApiMenu", eventmessage));
	}
	void getClickPath(TreeItem selectedItem, ArrayList<TreeItem> clickpath){
		clickpath.add(selectedItem);
		
		if (selectedItem.getParentItem() !=null) {
			TreeItem tmp = selectedItem.getParentItem();
			getClickPath(tmp, clickpath);	
		}	
	}

	public void fillMenu(ArrayList<Request_Template> templates) {
		if (templates == null) {
			System.out.println("templates = null");
			return;
		}

		for (Request_Template requestTemplate : templates) {
			String apiname = requestTemplate.getApiname();
			apis.add(apiname);
			Tree apitree = new Tree();
			apitree.setAnimationEnabled(true);
			this.add(apitree, apiname);
			apitree.addSelectionHandler(new SelectionHandler<TreeItem>() {
				
				public void onSelection(SelectionEvent<TreeItem> event) {
					onclick(event);	
				}
			});
			
			
			fillMethods(apitree, requestTemplate);

		}

	}

	private void fillMethods(Tree apitree, Request_Template requestTemplate) {
		ArrayList<APIMethod> methods = requestTemplate.getApi_methods();
		for (APIMethod apiMethod : methods) {
			TreeItem ti_methodname = new TreeItem(apiMethod.getMethodname());
			apitree.addItem(ti_methodname);
			ti_methodname.addItem(new TreeItem("preselect mandatory"));
			ArrayList<RequestParameter> parameters = apiMethod.getParameters();
			TreeItem ti_headers = new TreeItem("Header Parameters");
			TreeItem ti_parameters = new TreeItem("POST Body Parameters");
			for (RequestParameter parameter : parameters) {
				TreeItem ti_parameter = new TreeItem(parameter.getName());
				if (parameter.isHeaderParameter() == true) ti_headers.addItem(ti_parameter);
				else ti_parameters.addItem(ti_parameter);
			}
			if (ti_headers.getChildCount()>0) ti_methodname.addItem(ti_headers);
			if (ti_parameters.getChildCount()>0) ti_methodname.addItem(ti_parameters);
		}
	}

}


