package de.sten.apiexplorer.client;

import com.google.gwt.event.shared.GwtEvent;


public class MainEvent extends GwtEvent<MainEventHandler> {
	
	public static GwtEvent.Type<MainEventHandler> TYPE = new Type<MainEventHandler>();
	
	private String eventMessage;
	
	public MainEvent(String eventmessage) {
		
		this.eventMessage = eventmessage;
	}
	
	protected void dispatch(MainEventHandler handler) {
		handler.onEvent(this);
		
	}

	public Type <MainEventHandler> getAssociatedType() {
		return TYPE;
	}


	public String getEventMessage() {
		return eventMessage;
	}

	public void setEventMessage(String eventmessage) {
		this.eventMessage = eventmessage;
	}
}
