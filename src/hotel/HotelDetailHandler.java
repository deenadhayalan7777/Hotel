package hotel;

import java.util.Map;

import com.google.gson.Gson;

public class HotelDetailHandler extends Handler {

	@Override
	public String doService(Map<String, Object> parameters) {
		
		int hotelId= Integer.parseInt((String) parameters.get("hotelId")) ;
		int code= Integer.parseInt((String) parameters.get("code")) ;
		if(code==2)
		 {int status= Integer.parseInt((String) parameters.get("status")) ;
		  if(status==1)
			  app.getHotel(hotelId).setOpen(true);
		  else
			  app.getHotel(hotelId).setOpen(false); 
		  return "1";
		 }
		Gson gson=new Gson();
		
		  String response=gson.toJson(app.getHotelList().get(hotelId));
		  return response;
		
		
	}

}
