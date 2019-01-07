package hotel;

import java.util.Map;

import org.json.JSONObject;

import com.google.gson.Gson;

public class HotelListHandler extends Handler{

	@Override
	public String doService(Map<String,Object> parameters) {
		
		Gson gson=new Gson();
		
	  String response=gson.toJson(app.getHotelList());
	  return response;
	}

	
}
