package de.sten.apiexplorer.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UIBuilder {
	
	public UI buildUI(){
		UI ui = new UI();
		createElements(ui);
		assembleUI(ui);
		applyStyles(ui);
		
		return ui;
	}
	
	HorizontalPanel toppnl;
	VerticalPanel centerpnl;
	DisclosurePanel headerpnl;
	DisclosurePanel bodypnl;
	TabPanel headertabs;
	TabPanel bodytabs;
	DockPanel dockpnl;
	private void createElements(UI ui){
		
		dockpnl = new DockPanel();
		ui.rootpnl = new FlowPanel();
		ui.methodchsr = new ListBox();
		String[] methods = { "GET", "POST", "DELETE" };
		for (String method : methods) ui.methodchsr.addItem(method);
		ui.hostBox = new TextBox();
		ui.pathBox = new TextBox();
		ui.goBtn = new Button("Go!");
		ui.clearBtn = new Button("Clear");
		ui.headerReqBox = new RequestBox();
		ui.headerRespBox = new TextArea();
		ui.bodyReqBox = new RequestBox();
		ui.bodyRespBox = new TextArea();
		
		toppnl = new HorizontalPanel();
		centerpnl = new VerticalPanel();
		headerpnl = new DisclosurePanel("Headers");
		bodypnl = new DisclosurePanel("Body");
		
		headertabs = new TabPanel();
		bodytabs = new TabPanel();

		ui.apimenu = new APIMenu();
	}
	
	private void applyStyles(UI ui){
		centerpnl.setStyleName("centerpnl");
		toppnl.setStyleName("toppnl");
		Widget[] textareas = {ui.headerReqBox, ui.headerRespBox, ui.bodyReqBox, ui.bodyRespBox};
		for (Widget widget : textareas) widget.setStyleName("txtarea");
		headertabs.setStyleName("headertabs");
		headertabs.selectTab(0);
		bodytabs.setStyleName("bodytabs");
		bodytabs.selectTab(0);
		
		headerpnl.setStyleName("headerpnl");
		headerpnl.setAnimationEnabled(true);
		bodypnl.setStyleName("bodypnl");
		bodypnl.setAnimationEnabled(true);
		dockpnl.setStyleName("dockpnl");
		headerpnl.setOpen(true);
		bodypnl.setOpen(true);
		
		ui.rootpnl.setStyleName("rootpnl");
	}
	
	private void assembleUI(UI ui){
		
		toppnl.add(new Label("HTTP Method:"));
		toppnl.add(ui.methodchsr);
		toppnl.add(new Label("Host:"));
		toppnl.add(ui.hostBox);
		toppnl.add(new Label("Path:"));
		toppnl.add(ui.pathBox);
		toppnl.add(ui.goBtn);
		toppnl.add(ui.clearBtn);
		
		
		
		headertabs.add(ui.headerReqBox, "Request");
		headertabs.add(ui.headerRespBox, "Response");
		bodytabs.add(ui.bodyReqBox, "Request");
		bodytabs.add(ui.bodyRespBox, "Response");
		
		headerpnl.add(headertabs);
		centerpnl.add(headerpnl);
		bodypnl.add(bodytabs);
		centerpnl.add(bodypnl);
		
		dockpnl.add(toppnl, DockPanel.NORTH);
		dockpnl.add(centerpnl, DockPanel.CENTER);
		dockpnl.add(ui.apimenu, DockPanel.WEST);
		
		ui.rootpnl.add(dockpnl);
	//	ui.rootpnl.add(centerpnl);
		//ui.rootpnl.add(ui.apimenu);
	}

}
