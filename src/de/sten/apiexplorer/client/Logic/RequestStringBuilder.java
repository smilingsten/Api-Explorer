package de.sten.apiexplorer.client.Logic;

public class RequestStringBuilder {
	
	public String buildRequestString(String method, String host, String path, String headers, String bodyparams){
	
		String requeststring =method+" "+path+" HTTP/1.1\nHost: "+host+"\n";
		requeststring+=headers;
		System.out.println("reqstring is "+requeststring);
		
		
		return requeststring;
	}

}
