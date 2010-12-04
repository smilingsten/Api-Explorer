package de.sten.apiexplorer.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;

import de.sten.apiexplorer.client.Logic.MainEventHandler;
import de.sten.apiexplorer.client.Logic.RequestWorker;
import de.sten.apiexplorer.client.Logic.TemplateManager;
import de.sten.apiexplorer.client.UI.UI;
import de.sten.apiexplorer.client.UI.UIBuilder;
import de.sten.apiexplorer.client.passiveObjects.MainEvent;
import de.sten.apiexplorer.client.passiveObjects.Request_Template;

public class API_Explorer implements EntryPoint, MainEventHandler {

	ArrayList<Request_Template> templates;
	TemplateManager templatemngr;
	UI ui;
	RequestWorker reqworker;
	public void onModuleLoad() {
		//create an eventbus to pass events around and add this class as a handler
		SimpleEventBus eventbus = new SimpleEventBus();
		eventbus.addHandler(MainEvent.TYPE, this);
		reqworker = new RequestWorker(eventbus);
		
		//load api templates from server and save them for this instance
		templatemngr = new TemplateManager(eventbus);
		templatemngr.loadTemplatesFromServer();
		//create a new UI
		UIBuilder uibuilder = new UIBuilder();
		ui = uibuilder.buildUI(eventbus);
		
		
		//add the UI to the website
		RootPanel rpnl = RootPanel.get("rootContainer");
		rpnl.add(ui.rootpnl);
		System.out.println("LOADED...");	
	}

	
	//handling of all events concerning the main program
	public void onEvent(MainEvent event) {
		
		if (event.getEventSource().equals("ApiMenu")){
			ui.completeFormWithClickPath(event.getEventMessage(), templates);
			return;
			
		}
		
		 if (event.getEventSource().equals("TemplateManager")){
			if (event.getEventMessage().equals("template_load_success")){
				templates = templatemngr.getAllTemplates();
				ui.apimenu.fillMenu(templates);
				return;
			}
				System.out.println(event.getEventMessage());
				return;
		}
		 if (event.getEventSource().equals("UI")){
			
			if (event.getEventMessage().equals("go")){
				String method=ui.methodchsr.getItemText(ui.methodchsr.getSelectedIndex());
				String host = ui.hostBox.getText();
				String path = ui.pathBox.getText();
				String headers = ui.headerReqBox.getText();
				System.out.println("headersize is "+headers.length());
				String bodyparams = ui.bodyReqBox.getText();
				System.out.println("bodyparamsize is "+bodyparams.length());
				
				reqworker.doRequest(method, host, path, headers, bodyparams);
				return;
			}
		}
	}
}
