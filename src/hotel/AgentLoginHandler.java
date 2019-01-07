package hotel;

import java.util.Map;

public class AgentLoginHandler extends Handler{

	@Override
	public String doService(Map<String, Object> parameters) {
		
		String username=(String) parameters.get("username");
        String password=(String) parameters.get("password");
     
     	          
     	 Integer  agentId=app.agentLogin(username, password);
     	  System.out.println("agent logged" + agentId);
     	String  response=agentId.toString();  
     	return response;
	}

}
