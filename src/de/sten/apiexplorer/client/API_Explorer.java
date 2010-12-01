package de.sten.apiexplorer.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class API_Explorer implements EntryPoint, MainEventHandler {

	ArrayList<Request_Template> templates;
	TemplateManager templatemngr;
	
	UI ui;
	public void onModuleLoad() {
		//load api templates from server and save them for this instance
		templatemngr = new TemplateManager(this);
		templatemngr.loadTemplatesFromServer();
		//create a new UI
		UIBuilder uibuilder = new UIBuilder();
		ui = uibuilder.buildUI();
		
		
		
		//add the UI to the website
		RootPanel rpnl = RootPanel.get("rootContainer");
		rpnl.add(ui.rootpnl);
	
	}

	
	//handling of all events concerning the main program
	public void onEvent(MainEvent event) {
		if (event.getEventMessage().equals("template_load_success")){
			templates = templatemngr.getAllTemplates();
			System.out.println("loaded "+templates.size()+ " api templates");
			ui.apimenu.fillMenu(templates);
			return;
		}
		if (event.getEventMessage().startsWith("template_load_error\nMessage in caught was:\n")){
			System.out.println(event.getEventMessage());
			return;
		}
		
		
	}
	

	

}
