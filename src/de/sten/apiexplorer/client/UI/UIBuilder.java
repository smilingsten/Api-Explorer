package de.sten.apiexplorer.client.UI;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
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

import de.sten.apiexplorer.client.passiveObjects.MainEvent;

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
	DisclosurePanel rawReqpnl;
	DockPanel dockpnl;
	DecoratorPanel decpnl;
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
		ui.rawRequestBox = new TextArea();
		rawReqpnl = new DisclosurePanel("Your raw HTTP request");
		
		toppnl = new HorizontalPanel();
		centerpnl = new VerticalPanel();
		headerpnl = new DisclosurePanel("Headers");
		bodypnl = new DisclosurePanel("Body");
		
		headertabs = new TabPanel();
		bodytabs = new TabPanel();

		ui.apimenu = new APIMenu(eventbus);
		ui.b64menu = new Base64Dialog();
	    decpnl = new DecoratorPanel();
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
		centerpnl.addStyleName("centerpnl");
		toppnl.addStyleName("toppnl");
		ui.hostBox.addStyleName("hostbox");
		ui.pathBox.addStyleName("pathbox");
		Widget[] textareas = {ui.headerReqBox, ui.headerRespBox, ui.bodyReqBox, ui.bodyRespBox};
		for (Widget widget : textareas) {
			widget.addStyleName("txtarea");
			((TextArea) widget).setVisibleLines(10);
		}
		headertabs.addStyleName("headertabs");
		headertabs.selectTab(0);
		bodytabs.addStyleName("bodytabs");
		bodytabs.selectTab(0);
		
		headerpnl.addStyleName("headerpnl");
		headerpnl.setAnimationEnabled(true);
		bodypnl.addStyleName("bodypnl");
		bodypnl.setAnimationEnabled(true);
		dockpnl.addStyleName("dockpnl");
		headerpnl.setOpen(true);
		bodypnl.setOpen(true);
		//ui.apimenu.setStyleName("apimenu");
		ui.apimenu.addStyleName("apimenu");
		ui.rawRequestBox.addStyleName("txtarea");
		rawReqpnl.setAnimationEnabled(true);
		rawReqpnl.addStyleName("rawreqpnl");
		ui.rawRequestBox.setVisibleLines(8);
		rawReqpnl.setOpen(true);
		ui.rootpnl.addStyleName("rootpnl");
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
		
		rawReqpnl.add(ui.rawRequestBox);
		decpnl.add(rawReqpnl);
		//centerpnl.add(decpnl);
		
		dockpnl.add(toppnl, DockPanel.NORTH);
		dockpnl.add(centerpnl, DockPanel.CENTER);
		dockpnl.add(ui.apimenu, DockPanel.WEST);
		dockpnl.add(ui.b64btn, DockPanel.EAST);
		
		ui.rootpnl.add(dockpnl);
	}

}
