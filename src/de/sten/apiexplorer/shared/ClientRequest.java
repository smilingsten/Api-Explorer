package de.sten.apiexplorer.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientRequest implements Serializable {
	
	String httpMethod, host, path;
	ArrayList<String> headers;
	ArrayList<String> bodyparameters;
	
	public ClientRequest() {
		headers = new ArrayList<String>();
		bodyparameters = new ArrayList<String>();
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public ArrayList<String> getHeaders() {
		return headers;
	}
	public void setHeaders(ArrayList<String> headers) {
		this.headers = headers;
	}
	public ArrayList<String> getBodyparameters() {
		return bodyparameters;
	}
	public void setBodyparameters(ArrayList<String> bodyparameters) {
		this.bodyparameters = bodyparameters;
	}
	
	
	

}
