package de.sten.apiexplorer.client.Logic;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.sten.apiexplorer.shared.ClientRequest;
import de.sten.apiexplorer.shared.ServerResponse;

public interface RequestServiceAsync {

	void doRequest(ClientRequest request, AsyncCallback<ServerResponse> callback);

}
