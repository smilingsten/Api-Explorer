package de.sten.apiexplorer.shared;

import java.io.Serializable;

public class ServerResponse implements Serializable{
	
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
