package hotel;

import java.util.Map;

public class AgentSignUpHandler extends Handler {

	@Override
	public String doService(Map<String, Object> parameters) {
		
		String username=(String) parameters.get("username");
        String password=(String) parameters.get("password");
                 String phone=(String) parameters.get("phone");
      	       Integer  agentId=app.agentSignUp(username, password, phone);
      	            System.out.println("agent signed up "+agentId);
      	            
      	        String response=agentId.toString(); 
      	        return response;
	}

}
