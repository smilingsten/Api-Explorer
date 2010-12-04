package de.sten.apiexplorer.client.passiveObjects;

import com.google.gwt.event.shared.GwtEvent;

import de.sten.apiexplorer.client.Logic.MainEventHandler;


public class MainEvent extends GwtEvent<MainEventHandler> {
	
	public static GwtEvent.Type<MainEventHandler> TYPE = new Type<MainEventHandler>();
	
	private String eventSource;
	private String eventMessage;
	
	public MainEvent(String eventSource ,String eventmessage) {
		
		this.eventSource = eventSource;
		this.eventMessage = eventmessage;
	}
	
	protected void dispatch(MainEventHandler handler) {
		handler.onEvent(this);
		
	}

	public Type <MainEventHandler> getAssociatedType() {
		return TYPE;
	}


	public String getEventSource() {
		return eventSource;
	}
	
	public String getEventMessage() {
		return eventMessage;
	}

	public void setEventMessage(String eventmessage) {
		this.eventMessage = eventmessage;
	}
}
