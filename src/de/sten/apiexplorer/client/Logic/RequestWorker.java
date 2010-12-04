package de.sten.apiexplorer.client.Logic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.sten.apiexplorer.shared.ClientRequest;
import de.sten.apiexplorer.shared.ServerResponse;

public class RequestWorker {
	
	SimpleEventBus eventbus;
	private final RequestServiceAsync reqservice;
	
	public RequestWorker(SimpleEventBus eventbus) {
		this.eventbus = eventbus;
		reqservice = GWT.create(RequestService.class);
	}
	
	public void doRequest(String method, String host, String path, String headers, String bodyparams){
		System.out.println("doing request");
		ClientRequest cr = buildRequest(method, host, path, headers, bodyparams);
		System.out.println("method: "+cr.getHttpMethod()+"; host: "+cr.getHost()+"; path: "+cr.getPath());
		for (String header : cr.getHeaders()) System.out.println("header: "+header);
		for (String param : cr.getBodyparameters()) System.out.println("param: "+param);
		
		reqservice.doRequest(cr, new AsyncCallback<ServerResponse>() {
			
			public void onSuccess(ServerResponse result) {
				System.out.println("Request Service Success. Message:"+result.getMessage());
				
			}
			
			public void onFailure(Throwable caught) {
				System.out.println("Request Service Failure");
				
			}
		});
		
		
		
	}

	private ClientRequest buildRequest(String method, String host, String path, String headers, String bodyparams){
		
		ClientRequest cr = new ClientRequest();
		
		cr.setHttpMethod(method.trim().toUpperCase());
		cr.setHost(host.trim());
		cr.setPath(path.trim());
		String[] headerarray = headers.split("\n");
		for (String header : headerarray) {
			if (header.trim().replaceAll(" ", "").replaceAll("\n", "").replaceAll("\t", "").length()<1 ){
				System.out.println("noheader");
				continue;
			}
			cr.getHeaders().add(header.trim());
		}
		String[] bparamarray = bodyparams.trim().split("\n");
		for (String bparam : bparamarray) {
			if (bparam.trim().replaceAll(" ", "").replaceAll("\n", "").replaceAll("\t", "").length()<1 ){
				System.out.println("nobparam");
				continue;
			}
			cr.getBodyparameters().add(bparam.trim());
		}
		return cr;
	}
	

}
