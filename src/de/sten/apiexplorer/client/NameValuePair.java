package de.sten.apiexplorer.client;

public class NameValuePair {
	
	private String name, value;
	
	public NameValuePair() {}
	
	public NameValuePair(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}


}
