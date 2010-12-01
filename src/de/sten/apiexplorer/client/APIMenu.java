package de.sten.apiexplorer.client;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

public class APIMenu extends DecoratedStackPanel {

	public void fillMenu(ArrayList<Request_Template> templates) {
		if (templates == null) {
			System.out.println("templates = null");
			return;
		}

		for (Request_Template requestTemplate : templates) {
			String apiname = requestTemplate.getApiname();
			Tree apitree = new Tree();
			this.add(apitree, apiname);

			ArrayList<APIMethod> methods = requestTemplate.getApi_methods();
			for (APIMethod apiMethod : methods) {

				TreeItem ti_methodname = new TreeItem(apiMethod.getMethodname());
				apitree.addItem(ti_methodname);
				ti_methodname.addItem(new TreeItem("preselect all needed"));
				ArrayList<NameValuePair> headers = apiMethod.getHeaders();
				TreeItem ti_headers = new TreeItem("Header Parameters");

				if (headers.size() > 0) {
					ti_methodname.addItem(ti_headers);
					for (NameValuePair nameValuePair : headers) {
						ti_headers.addItem(new TreeItem(nameValuePair.getName()));

					}
				}

				ArrayList<NameValuePair> parameters = apiMethod.getBodyparameters();
				TreeItem ti_parameters = new TreeItem("POST Body Parameters");
				if (parameters.size() > 0) {
					ti_methodname.addItem(ti_parameters);
					for (NameValuePair nameValuePair : parameters) {
						ti_parameters.addItem(new TreeItem(nameValuePair.getName()));

					}
				}

			}
		}

	}

}
