package de.sten.apiexplorer.client;

import java.util.ArrayList;
// a template for requests to a certain api
public class Request_Template {
	//elements of an api
	private String apiname, apihost;
	private ArrayList<APIMethod> api_methods;

	public Request_Template() {
	this.api_methods = new ArrayList<APIMethod>();	
	}
	
	public Request_Template(String apiname, String apihost, ArrayList<APIMethod> api_methods) {
		
		this.apiname = apiname;
		this.apihost = apihost;
		this.api_methods = api_methods;	
		}

	public String getApiname() {
		return apiname;
	}

	public void setApiname(String apiname) {
		this.apiname = apiname;
	}

	public String getApihost() {
		return apihost;
	}

	public void setApihost(String apihost) {
		this.apihost = apihost;
	}

	public ArrayList<APIMethod> getApi_methods() {
		return api_methods;
	}

	public void setApi_methods(ArrayList<APIMethod> apiMethods) {
		api_methods = apiMethods;
	}
	
	

}
