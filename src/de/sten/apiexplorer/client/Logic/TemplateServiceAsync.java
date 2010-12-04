package de.sten.apiexplorer.client.Logic;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface TemplateServiceAsync {

	void getRequestTemplates(AsyncCallback<ArrayList<String>> callback);

}
