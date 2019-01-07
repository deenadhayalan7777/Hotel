package hotel;

import java.util.Map;

public class HotelLoginHandler extends Handler{

	@Override
	public String doService(Map<String,Object> parameters) {
		 String username=(String) parameters.get("username");
         String password=(String) parameters.get("password");
      
      	          
      	 Integer  userId=app.hotelLogin(username, password);
      	  System.out.println("hotel logged" + userId);
      	 return userId.toString(); 
		
	}

}
