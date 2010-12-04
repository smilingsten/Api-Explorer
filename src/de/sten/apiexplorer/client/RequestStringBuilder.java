package de.sten.apiexplorer.client;

public class RequestStringBuilder {
	
	public String buildRequestString(String method, String host, String path, String headers, String bodyparams){
	
		String requeststring =method+" "+path+" HTTP/1.1\nHost: "+host+"\n";
		System.out.println("reqstring is "+requeststring);
		
		return requeststring;
	}

}
