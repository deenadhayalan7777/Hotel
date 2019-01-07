package hotel;

import java.util.Map;

public class UserLoginHandler extends Handler {

	@Override
	public String doService(Map<String,Object> parameters) {
		 String username=(String) parameters.get("username");
         String password=(String) parameters.get("password");
      
      	          
      	 Integer  userId=app.userLogin(username, password);
      	  System.out.println("user logged" + userId);
      	String  response=userId.toString();  
      	return response;
		
	}

}
