package de.sten.apiexplorer.client.Logic;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("requesttemplates")
public interface TemplateService extends RemoteService {

	public ArrayList<String> getRequestTemplates();
	
}
