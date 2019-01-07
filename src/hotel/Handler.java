package hotel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public abstract class Handler implements HttpHandler{
	
	public Application app;
	public Service service;
	public Gson gson;
	
	public Handler()
	{
		app=Application.getInstance();
		gson=new Gson();
	}
	@Override
	public void handle(HttpExchange he) throws IOException {
		
		Map<String,Object> parameters=new HashMap<String, Object>();
		
		InputStreamReader isr;
		try {
			isr = new InputStreamReader(he.getRequestBody(), "utf-8");
			  BufferedReader br = new BufferedReader(isr);
		        String query = br.readLine();
		        parseQuery(query,parameters);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		String response=doService(parameters);
		app.saveInFile();
		
		 try {
				he.sendResponseHeaders(200, response.length());
				 OutputStream os = he.getResponseBody();
			       os.write(response.getBytes());
			       os.close();
			      } catch (IOException e) {
				
				e.printStackTrace();
			}
		
	}
	public abstract String doService(Map<String,Object> parameters);
	
	
	public void parseQuery(String query,Map<String,Object> parameters)  {

    	         if (query != null) {
    	                 String pairs[] = query.split("[&]");
    	                 for (String pair : pairs) {
    	                          String param[] = pair.split("[=]");
    	                          String key = null;
    	                          String value = null;
    	                          
    	                          try {
    	                        	  if (param.length > 0) {  
									key = URLDecoder.decode(param[0], 
									  	System.getProperty("file.encoding"));}
									if (param.length > 1) {
 	                                   value = URLDecoder.decode(param[1], 
 	                                   System.getProperty("file.encoding"));
 	                          

								}} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
    	                         
    	                                   parameters.put(key, value);
    	                          
    	                 }
    	         }
    	}
	

}
