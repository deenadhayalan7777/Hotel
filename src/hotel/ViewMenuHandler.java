package hotel;

import java.io.IOException;
import java.util.Map;

import com.google.gson.Gson;


public class ViewMenuHandler extends Handler  {

	@Override
	public String doService(Map<String,Object> parameters) {
		int hotelId= Integer.parseInt((String) parameters.get("hotelId")) ;
        Gson gson=new Gson();
       String response=gson.toJson(app.getHotel(hotelId).getMenu());
       return response;
	}
}
