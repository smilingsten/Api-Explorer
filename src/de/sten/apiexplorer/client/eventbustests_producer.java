package de.sten.apiexplorer.client;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.SimpleEventBus;

public class eventbustests_producer {
	
	SimpleEventBus eventbus;
	public eventbustests_producer(SimpleEventBus eventbus) {
		this.eventbus = eventbus; 
		eventHappened(new MainEvent("", "eventbus_producer was contructed and used given eventbus successfully"));
	}
	
	void eventHappened(GwtEvent<MainEventHandler> event){
		eventbus.fireEvent(event);
	}

}
