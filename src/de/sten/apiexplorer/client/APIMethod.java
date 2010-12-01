package de.sten.apiexplorer.client;

import java.util.ArrayList;
//describes a certain method of an api
public class APIMethod {
	
	private String methodname, methodtype, methodpath;
	private ArrayList<NameValuePair> headers;
	private ArrayList<NameValuePair> bodyparameters;
	
	public APIMethod() {
		this.headers = new ArrayList<NameValuePair>();
		this.bodyparameters = new ArrayList<NameValuePair>();
	}
	
	public APIMethod(String methodname, String methodtype, String methodpath, ArrayList<NameValuePair> headers, ArrayList<NameValuePair> bodyparamters) {
		this.methodname = methodname;
		this.methodpath = methodpath;
		this.methodtype = methodtype;
		this.headers = headers;
		this.bodyparameters = bodyparamters;
	}
	
	public APIMethod(String methodname, String methodtype, String methodpath, ArrayList<NameValuePair> headers) {
		this.methodname = methodname;
		this.methodpath = methodpath;
		this.methodtype = methodtype;
		this.headers = headers;
		this.bodyparameters = new ArrayList<NameValuePair>();
		
	}

	public String getMethodname() {
		return methodname;
	}

	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}

	public String getMethodtype() {
		return methodtype;
	}

	public void setMethodtype(String methodtype) {
		this.methodtype = methodtype;
	}

	public String getMethodpath() {
		return methodpath;
	}

	public void setMethodpath(String methodpath) {
		this.methodpath = methodpath;
	}

	public ArrayList<NameValuePair> getHeaders() {
		return headers;
	}

	public void setHeaders(ArrayList<NameValuePair> headers) {
		this.headers = headers;
	}

	public ArrayList<NameValuePair> getBodyparameters() {
		return bodyparameters;
	}

	public void setBodyparameters(ArrayList<NameValuePair> bodyparameters) {
		this.bodyparameters = bodyparameters;
	}

}
