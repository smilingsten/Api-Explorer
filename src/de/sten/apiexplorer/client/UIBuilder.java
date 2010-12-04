package de.sten.apiexplorer.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UIBuilder {
	
	private SimpleEventBus eventbus;
	UI ui;
	public UI buildUI (SimpleEventBus eventbus){
		this.eventbus = eventbus;
		ui = new UI();
		createElements();
		assembleUI();
		applyStyles();
		addHandlers();
		
		return ui;
	}
	
	HorizontalPanel toppnl;
	VerticalPanel centerpnl;
	DisclosurePanel headerpnl;
	DisclosurePanel bodypnl;
	TabPanel headertabs;
	TabPanel bodytabs;
	DockPanel dockpnl;
	private void createElements(){
		
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
		ui.b64btn = new Button("B64");
		
		toppnl = new HorizontalPanel();
		centerpnl = new VerticalPanel();
		headerpnl = new DisclosurePanel("Headers");
		bodypnl = new DisclosurePanel("Body");
		
		headertabs = new TabPanel();
		bodytabs = new TabPanel();

		ui.apimenu = new APIMenu(eventbus);
		ui.b64menu = new Base64Dialog();
	}
	
	private void addHandlers(){
		ui.clearBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ui.clearAll();		
			}
		});
		ui.goBtn.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				eventbus.fireEvent(new MainEvent("UI", "go"));
				
			}
		});
		
		ui.b64btn.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				
				ui.b64menu.show();
				ui.b64menu.center();
				
				
			}
		});
	}
	
	private void applyStyles(){
		centerpnl.setStyleName("centerpnl");
		toppnl.setStyleName("toppnl");
		ui.hostBox.setStyleName("hostbox");
		ui.pathBox.setStyleName("pathbox");
		Widget[] textareas = {ui.headerReqBox, ui.headerRespBox, ui.bodyReqBox, ui.bodyRespBox};
		for (Widget widget : textareas) {
			widget.setStyleName("txtarea");
			((TextArea) widget).setVisibleLines(10);
		}
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
		//ui.apimenu.setStyleName("apimenu");
		ui.apimenu.addStyleName("apimenu");
	
		
		ui.rootpnl.setStyleName("rootpnl");
	}
	
	private void assembleUI(){
		
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
		dockpnl.add(ui.b64btn, DockPanel.EAST);
		
		ui.rootpnl.add(dockpnl);
	}

}
