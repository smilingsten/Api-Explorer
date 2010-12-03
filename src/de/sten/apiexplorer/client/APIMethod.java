package de.sten.apiexplorer.client;

import java.util.ArrayList;
//describes a certain method of an api
public class APIMethod {
	
	private String methodname, methodtype, methodpath;
	private ArrayList<RequestParameter> parameters;
	
	public APIMethod() {
		this.parameters = new ArrayList<RequestParameter>();
	}

	public String getParameterValue(String parametername, boolean isHeader){
		for (RequestParameter parameter : parameters) {
			if ( (parameter.getName().equals(parametername)) && parameter.isHeaderParameter() == isHeader ) return parameter.getValue();
		}
		return "Error, Parameter not found";
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

	public ArrayList<RequestParameter> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<RequestParameter> parameters) {
		this.parameters = parameters;
	}
	
	
}
