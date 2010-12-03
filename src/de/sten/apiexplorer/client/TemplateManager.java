package de.sten.apiexplorer.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class TemplateManager{
	
	//a list for all the request templates
	private ArrayList<Request_Template> alltemplates;
	private SimpleEventBus eventbus;
	
	public TemplateManager(SimpleEventBus eventbus) {
		this.eventbus = eventbus;
	}
	
	public ArrayList<Request_Template> getAllTemplates(){
		return alltemplates;		
	}

	public void loadTemplatesFromServer() { 
		TemplateServiceAsync templateService = GWT.create(TemplateService.class);
		templateService.getRequestTemplates(new AsyncCallback<ArrayList<String>>() {
			
			public void onSuccess(ArrayList<String> result) {
				TemplateParser parser = new TemplateParser();
				alltemplates = parser.parseTemplates(result);
				eventbus.fireEvent(new MainEvent("TemplateManager", "template_load_success"));
			}
			public void onFailure(Throwable caught) {
				eventbus.fireEvent(new MainEvent("TemplateManager", "template_load_error\nMessage in caught was:\n"+caught.getMessage()));
			}
		});
	}


}
