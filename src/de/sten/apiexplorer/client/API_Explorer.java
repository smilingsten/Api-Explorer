package de.sten.apiexplorer.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class API_Explorer implements EntryPoint, MainEventHandler {

	ArrayList<Request_Template> templates;
	TemplateManager templatemngr;
	UI ui;
	public void onModuleLoad() {
		//create an eventbus to pass events around and add this class as a handler
		SimpleEventBus eventbus = new SimpleEventBus();
		eventbus.addHandler(MainEvent.TYPE, this);
		
		//load api templates from server and save them for this instance
		templatemngr = new TemplateManager(eventbus);
		templatemngr.loadTemplatesFromServer();
		//create a new UI
		UIBuilder uibuilder = new UIBuilder();
		ui = uibuilder.buildUI(eventbus);
		
		
		//add the UI to the website
		RootPanel rpnl = RootPanel.get("rootContainer");
		rpnl.add(ui.rootpnl);
	
	}

	
	//handling of all events concerning the main program
	public void onEvent(MainEvent event) {
		
		if (event.getEventSource().equals("ApiMenu")){
		//	System.out.println("--------------");
			//System.out.println("ApiMenus says: "+event.getEventMessage());
			ui.completeFormWithClickPath(event.getEventMessage(), templates);
			
		}
		
		else if (event.getEventSource().equals("TemplateManager")){
		//	System.out.println("--------------");
		//	System.out.println("Templatemanager says: ");
			if (event.getEventMessage().equals("template_load_success")){
				templates = templatemngr.getAllTemplates();
			//	System.out.println("loaded "+templates.size()+ " api templates :-)");
				ui.apimenu.fillMenu(templates);
				return;
			}
				System.out.println(event.getEventMessage());
				return;
		}
		
		
		
	}
	
	
	

}
