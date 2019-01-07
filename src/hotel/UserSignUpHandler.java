package hotel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class UserSignUpHandler extends Handler  {

	

	@Override
	public String doService(Map<String,Object> parameters) {
		
		 String username=(String) parameters.get("username");
         String password=(String) parameters.get("password");
                  String phone=(String) parameters.get("phone");
       	       Integer  userId=app.userSignUp(username, password, phone);
       	            System.out.println("user signed up "+userId);
       	            
       	        String response=userId.toString(); 
       	        return response;
        
         }
        
       
		
	}
	


	
