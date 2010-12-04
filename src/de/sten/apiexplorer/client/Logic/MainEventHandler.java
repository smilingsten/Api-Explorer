package de.sten.apiexplorer.client.Logic;

import com.google.gwt.event.shared.EventHandler;

import de.sten.apiexplorer.client.passiveObjects.MainEvent;



public interface MainEventHandler extends EventHandler {

	public void onEvent(MainEvent event);
	
}
