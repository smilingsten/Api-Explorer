package de.sten.apiexplorer.client;

import com.google.gwt.event.shared.SimpleEventBus;



public class eventbustests_consumer implements MainEventHandler{
	SimpleEventBus eventbus;
	
	public eventbustests_consumer(SimpleEventBus eventbus){
		
		this.eventbus = eventbus;
		eventbus.addHandler(MainEvent.TYPE, this);
		
		
	}

	public void onEvent(MainEvent event) {
		System.out.println("event reveived: "+event.getEventMessage());
		
	}
	
	
	
	

}
