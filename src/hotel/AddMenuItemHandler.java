package hotel;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class AddMenuItemHandler extends Handler {

	@Override
	public String doService(Map<String,Object> parameters) {
		
		String response="0";
		Gson g = new Gson(); 
		Integer hotelId=Integer.parseInt((String) parameters.get("hotelId"));
		Integer code=Integer.parseInt((String) parameters.get("code"));
		if(code==2)
		{
			Integer itemId=Integer.parseInt((String) parameters.get("itemId"));
			app.getHotel(hotelId).getMenu().remove(itemId);
		}
		else
		{		
		String jsonItem=(String) parameters.get("item");
		Item item =  g.fromJson(jsonItem, Item.class);
		app.getHotel(hotelId).getMenu().put(item.getItemId(),item);
		}
		response ="1";
        return response;
		}

	
}
