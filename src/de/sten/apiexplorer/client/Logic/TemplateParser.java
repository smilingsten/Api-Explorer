package de.sten.apiexplorer.client.Logic;
import java.util.ArrayList;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

import de.sten.apiexplorer.client.passiveObjects.APIMethod;
import de.sten.apiexplorer.client.passiveObjects.RequestParameter;
import de.sten.apiexplorer.client.passiveObjects.Request_Template;

public class TemplateParser {
	
	
	
	public ArrayList<Request_Template> parseTemplates(ArrayList<String> templates){
		//Template List to store extracted information
		ArrayList<Request_Template> req_templates = new ArrayList<Request_Template>();
		for (String template : templates) {
			//create xml from string and get rootnode
			Document doc = XMLParser.parse(template);
			Element root = doc.getDocumentElement();

			//get name and host
			String apiname = root.getElementsByTagName("api_name").item(0).getFirstChild().getNodeValue();
			String apihost = root.getElementsByTagName("api_host").item(0).getFirstChild().getNodeValue();
			
			//get methods
			ArrayList<APIMethod> apimethods = readMethods(root);
			
			req_templates.add(new Request_Template(apiname, apihost, apimethods));
		}
		
		return req_templates;
	}

	private ArrayList<APIMethod> readMethods(Element root) {
		//List for all methods of this api
		ArrayList<APIMethod> apimethods = new ArrayList<APIMethod>();
		//loop over all methods
		NodeList methodnodes = root.getElementsByTagName("api_method");
		for (int i = 0; i< methodnodes.getLength(); i++){
			//get method name, restpath and type
			Element currentmethodnode = ((Element) methodnodes.item(i));
			String methodname = (currentmethodnode).getElementsByTagName("method_name").item(0).getFirstChild().getNodeValue();
			String methodtype = (currentmethodnode).getElementsByTagName("http_method_type").item(0).getFirstChild().getNodeValue();
			String path = (currentmethodnode).getElementsByTagName("rest_path").item(0).getFirstChild().getNodeValue();
			
			APIMethod method = new APIMethod();
			method.setMethodname(methodname);
			method.setMethodtype(methodtype);
			method.setMethodpath(path);
				
			method.setParameters(readParameters(currentmethodnode));		
			apimethods.add(method);
		}
		return apimethods;
	}
	
	private ArrayList<RequestParameter> readParameters(Element methodnode) {
		ArrayList<RequestParameter> parameters = new ArrayList<RequestParameter>();
		NodeList headernodes = methodnode.getElementsByTagName("header");
		NodeList parameternodes = methodnode.getElementsByTagName("parameter");
		for (int j = 0; j< (headernodes.getLength()+parameternodes.getLength()); j++){
			
			//get name and value
			Element currentitem;
			boolean isHeader = false;
			if (j<headernodes.getLength()){
				currentitem = (Element) headernodes.item(j);
				isHeader = true;
			}
			else currentitem = (Element) parameternodes.item(j-headernodes.getLength());
			
			String parametername = currentitem.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
			String parametervalue = currentitem.getElementsByTagName("value").item(0).getFirstChild().getNodeValue();
			boolean isMandatory = false;
			if ( ((currentitem).getAttribute("mandatory") !=null) && ((currentitem).getAttribute("mandatory").equals("true") )  ) {		
				isMandatory = true;
			}
			RequestParameter parameter = new RequestParameter();
			parameter.setName(parametername);
			parameter.setValue(parametervalue);
			parameter.setForMethod(methodnode.getElementsByTagName("method_name").item(0).getFirstChild().getNodeValue());
			parameter.setHeaderParameter(isHeader);
			parameter.setMandatory(isMandatory);
			
			parameters.add(parameter);
		}
		
		return parameters;
	}

}
