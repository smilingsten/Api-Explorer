package de.sten.apiexplorer.client.UI;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;

import de.sten.apiexplorer.client.passiveObjects.APIMethod;
import de.sten.apiexplorer.client.passiveObjects.RequestParameter;
import de.sten.apiexplorer.client.passiveObjects.Request_Template;

public class UI {
	
	public FlowPanel rootpnl;
	
	public ListBox methodchsr;
	public TextBox hostBox;
	public TextBox pathBox;
	 Button goBtn;
	 Button clearBtn;
	 public RequestBox headerReqBox;
	 TextArea headerRespBox;
	 public RequestBox bodyReqBox;
	 TextArea bodyRespBox;
	public APIMenu apimenu;
	 Button b64btn;
	 Base64Dialog b64menu;
	 
	 private APIMethod currentlySelectedApiMethod = null;
	 
	 
	 public void completeFormWithClickPath (String clickPath, ArrayList<Request_Template> templates){
		 
		 String[] clickpath = clickPath.split(":");
		 Request_Template api = extractApiFromClickPath(clickpath, templates);
		
		 if (api.equals(null)){
			 System.out.println("Eroor!!! couldn complete form, no api found for clickpath");
			 return;
		 }
		
		 APIMethod method = extractMethodFromClickPath(clickpath[1], api);
		 
		 if (method.equals(null)){
			 System.out.println("Error!!! couldn complete form, no method found for clickpath in api "+api.getApiname());
			 return;
		 }
		 
		 if (currentlySelectedApiMethod==null ||  !(currentlySelectedApiMethod.equals(method))) preselectMethod(api, method);
		 currentlySelectedApiMethod=method;
		 if(clickpath.length < 3) return;
		 String parametertype = clickpath[2];
		 boolean isHeader = false;
		if (parametertype.equals("preselect mandatory")) {
			preselectMandatoryParameters(method);
			return;
		}
		if (parametertype.equals("Header Parameters")) isHeader=true;
		if(clickpath.length < 4) return;
		String parametername = clickpath[3];
		preselectParameter(method, parametername, isHeader);
		
		
		 
	 }
	 
	 public void preselectMandatoryParameters(APIMethod method){
		 headerReqBox.setText("");
		 bodyReqBox.setText("");
		 for(RequestParameter parameter : method.getParameters()){
			
				if(parameter.isMandatory()) {
					preselectParameter(method, parameter.getName(), parameter.isHeaderParameter());
				}
			}
		 
	 }
	 
	 private void preselectParameter(APIMethod method, String parametername, boolean isHeader){
		
		 String value=method.getParameterValue(parametername, isHeader);
		 
		TextArea box2write;
		 if (isHeader) {
			 box2write = headerReqBox;
		 }
		 else {box2write = bodyReqBox;}
		 
		 String currenttext = ""+box2write.getText();
		 String regex=parametername+":.*\n";
		 currenttext = currenttext.replaceAll(regex, "");
		 String newHeader = parametername+": "+value;
		 newHeader = newHeader.replaceAll("\n", " ").replaceAll("\r", " ");
	 	 currenttext+=newHeader+"\n";
	     box2write.setText(currenttext);
		 
	 }
	 
	 private void preselectMethod(Request_Template api, APIMethod method){
		 clearAll();
		for (int i = 0; i < methodchsr.getItemCount(); i++){
			if (method.getMethodtype().equals(methodchsr.getItemText(i)  )) methodchsr.setSelectedIndex(i);
		}
		hostBox.setText(api.getApihost());
		pathBox.setText(method.getMethodpath());
		
	 }
	 
	 public void clearAll(){
		 methodchsr.setSelectedIndex(0);
		 hostBox.setText("");
		 pathBox.setText("");
		 headerReqBox.setText("");
		 headerRespBox.setText("");
		 bodyReqBox.setText("");
		 bodyRespBox.setText("");
		 currentlySelectedApiMethod = null;
	 }
	 
	 private APIMethod extractMethodFromClickPath (String methodname, Request_Template api){
		 ArrayList<APIMethod> methods = api.getApi_methods();
		 APIMethod method = null;
		 for (APIMethod apimethod : methods) {
				if(apimethod.getMethodname().equals(methodname)){
					method = apimethod;
				}
			}
		 
		 return method;
	 }
	 
	 private Request_Template extractApiFromClickPath (String[] clickpath, ArrayList<Request_Template> templates){
		 Request_Template api = null;
		 for (Request_Template apitemplate : templates) {
				if(apitemplate.getApiname().equals(clickpath[0])){
					api = apitemplate;
				}
			}
		 return api;
	 }
}
