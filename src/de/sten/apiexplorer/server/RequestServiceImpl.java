package de.sten.apiexplorer.server;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.sten.apiexplorer.client.Logic.RequestService;
import de.sten.apiexplorer.shared.ClientRequest;
import de.sten.apiexplorer.shared.ServerResponse;

public class RequestServiceImpl extends RemoteServiceServlet implements RequestService {

	public ServerResponse doRequest(ClientRequest request) {
		System.out.println("--------------------------");
		System.out.println("this is server working");
		
		HTTPMethod method = HTTPMethod.GET;
		
		if(request.getHttpMethod().equals("GET")) method = HTTPMethod.GET;
		else if(request.getHttpMethod().equals("POST")) method = HTTPMethod.POST;
		else if(request.getHttpMethod().equals("DELETE")) method = HTTPMethod.DELETE;
		String path = request.getPath();
		String host = request.getHost();
		
		
		System.out.println("method is "+method.toString()+"; host is "+host+"; path is "+path);
		ArrayList<NameValuePair> headers = new ArrayList<NameValuePair>();
		for (String header : request.getHeaders()){
			System.out.println("header: "+header);
			String name = header.split(":")[0];
			String value = header.split(":")[1];
			headers.add(new NameValuePair(name, value));
		}
		
		ArrayList<NameValuePair> bodyparams = new ArrayList<NameValuePair>();
		for (String bparam : request.getBodyparameters()){
			System.out.println("header: "+bparam);
			String name = bparam.split(":")[0];
			String value = bparam.split(":")[1];
			bodyparams.add(new NameValuePair(name, value));
		}
		x(method, host, path,headers);

		ServerResponse response = new ServerResponse();
		response.setMessage("Hello from Server");
		
		return response;
	}
	
	
	private void x(HTTPMethod method, String host, String path, ArrayList<NameValuePair> headers){
		
	try {
		URL url = new URL("https://"+host+path);
		
		HTTPRequest httpRequest = new HTTPRequest(url, method);
		for (NameValuePair header : headers) httpRequest.addHeader(new HTTPHeader(header.getName(), header.getValue()));
		

		
		URLFetchService urlFetchService = URLFetchServiceFactory.getURLFetchService();
		HTTPResponse response = urlFetchService.fetch(httpRequest);
		String responsestr = new String(response.getContent());
		
		if (response.getResponseCode() == 200) {
			System.out.println("hat geklappt "+responsestr);
		}
		else {
			System.out.println("hat nich geklappt "+response.getResponseCode());
		}
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

}
