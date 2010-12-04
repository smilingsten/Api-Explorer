package de.sten.apiexplorer.client.passiveObjects;

public class RequestParameter {
	
	private String forMethod;
	private boolean isHeaderParameter;
	private boolean mandatory;
	private String name;
	private String value;
	
	
	
	public String getForMethod() {
		return forMethod;
	}
	public void setForMethod(String forMethod) {
		this.forMethod = forMethod;
	}
	public boolean isHeaderParameter() {
		return isHeaderParameter;
	}
	public void setHeaderParameter(boolean isHeaderParameter) {
		this.isHeaderParameter = isHeaderParameter;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	

}
