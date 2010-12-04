package de.sten.apiexplorer.client.passiveObjects;

@SuppressWarnings("serial")
public class TemplateLoadException extends Exception {
	
	private String message;
	private String info;
	private Throwable cause;
	
	public TemplateLoadException(String message, String info, Throwable cause) {
		this.message = message;
		this.info = info;
		this.cause = cause;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

}
