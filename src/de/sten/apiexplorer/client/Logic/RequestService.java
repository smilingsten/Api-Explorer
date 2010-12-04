package de.sten.apiexplorer.client.Logic;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.sten.apiexplorer.shared.ClientRequest;
import de.sten.apiexplorer.shared.ServerResponse;

@RemoteServiceRelativePath("doRequest")
public interface RequestService extends RemoteService {

	public ServerResponse doRequest(ClientRequest request);
}
