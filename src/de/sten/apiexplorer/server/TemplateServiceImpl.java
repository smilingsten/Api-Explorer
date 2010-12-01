package de.sten.apiexplorer.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.sten.apiexplorer.client.TemplateService;

@SuppressWarnings("serial")
public class TemplateServiceImpl extends RemoteServiceServlet implements TemplateService {

   private String readFile(String path) { 
        
        String line = ""; 
        String text = ""; 
              
				try {
					InputStream ips = new FileInputStream(path);
					InputStreamReader ipsr = new InputStreamReader(ips); 
					BufferedReader br = new BufferedReader(ipsr); 
					  while ((line = br.readLine()) != null) { 
	                        text += line + "\n"; 
	                }
				} catch (FileNotFoundException e) {
					System.out.println("InputStreamReader File not Found Exception");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("BufferedReader readline IOException");
					e.printStackTrace();
				} 
				
				
        return text; 
} 
	
	public ArrayList<String> getRequestTemplates() {
		ArrayList<String> templates = new ArrayList<String>();
		File file = new File("./");
		String[] templatefiles = file.list();
		for (String templatefile : templatefiles) {
			if (templatefile.endsWith(".tmpl.xml")) {
				String filecontent = readFile(templatefile);
				templates.add(filecontent);
			}
		}
	return templates;
	}

}
