package hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MakeOrderHandler extends Handler {

	

	@Override
	public String doService(Map<String,Object> parameters) {
		
		Gson gson=new Gson();
		int rating=0;
		int hotelId= Integer.parseInt((String) parameters.get("hotelId"));
		Hotel hotel=app.getHotel(hotelId);
		if(hotel.isOpen())
		{   int total= Integer.parseInt((String) parameters.get("total")) ;
			int discount= Integer.parseInt((String) parameters.get("discount")) ;
			int userId= Integer.parseInt((String) parameters.get("userId")) ;
			rating= Integer.parseInt((String) parameters.get("rating"));
			String itemlist=(String) parameters.get("itemslist");
	        List<ItemQuantity> items = gson.fromJson(itemlist, new TypeToken<ArrayList<ItemQuantity>>(){}.getType());
	    	User user=app.getUser(userId);
	    	
	    	int orderId;
	    	synchronized(this) {
	    	orderId=app.getOrderCount()+1;
	    	app.setOrderCount(orderId);
	    	}
	    	
	        Order order=new Order(orderId,items,total,user.getPhone(),discount,hotelId,userId);
	        order.setStatus(1);
	        user.getCurrentOrders().put(orderId,order);
	        hotel.getCurrentOrders().put(orderId,order);
	       
	      
        return "1";
		}
		return "0";
		
	}
}