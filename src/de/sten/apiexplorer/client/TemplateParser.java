package de.sten.apiexplorer.client;
import java.util.ArrayList;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

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
			String methodname = ((Element) methodnodes.item(i)).getElementsByTagName("method_name").item(0).getFirstChild().getNodeValue();
			String methodtype = ((Element) methodnodes.item(i)).getElementsByTagName("http_method_type").item(0).getFirstChild().getNodeValue();
			String path = ((Element) methodnodes.item(i)).getElementsByTagName("rest_path").item(0).getFirstChild().getNodeValue();
			
			APIMethod method = new APIMethod();
			method.setMethodname(methodname);
			method.setMethodtype(methodtype);
			method.setMethodpath(path);
			
			Element currentmethodnode = ((Element) methodnodes.item(i));	
			method.setHeaders(readHeaders(currentmethodnode));
			method.setBodyparameters(readParameters(currentmethodnode));		
			apimethods.add(method);
		}
		
		return apimethods;
	}
	
	private ArrayList<NameValuePair> readHeaders(Element methodnode){
		//List for all headers of this method
		ArrayList<NameValuePair> methodheaders = new ArrayList<NameValuePair>();
		//loop over all request headers
		NodeList headernodes = methodnode.getElementsByTagName("header");
		for (int j = 0; j< headernodes.getLength(); j++){
			//get name and value
			String headername = ((Element) headernodes.item(j)).getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
			String headervalue = ((Element) headernodes.item(j)).getElementsByTagName("value").item(0).getFirstChild().getNodeValue();
			methodheaders.add(new NameValuePair(headername, headervalue));
		}
		return methodheaders;
		
	}
	
	private ArrayList<NameValuePair> readParameters(Element methodnode){
		//List for all parameters of this method
		ArrayList<NameValuePair> methodparameters = new ArrayList<NameValuePair>();
		//loop over all post paramters
		NodeList paramternodes = methodnode.getElementsByTagName("parameter");
		for (int j = 0; j< paramternodes.getLength(); j++){
			//get name and value
			String parametername = ((Element) paramternodes.item(j)).getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
			String parametervalue = ((Element) paramternodes.item(j)).getElementsByTagName("value").item(0).getFirstChild().getNodeValue();
			methodparameters.add(new NameValuePair(parametername, parametervalue));
		}
		return methodparameters;
		
	}
}
