package hotel;

import java.util.Map;

import com.google.gson.Gson;

public class RateOrderHandler extends Handler{

	@Override
	public String doService(Map<String, Object> parameters) {
		
		String response="0";
		Gson g = new Gson(); 
		Integer userId=Integer.parseInt((String) parameters.get("userId"));
		User user=app.getUser(userId);
	    String jsonItem=(String) parameters.get("order");
		Order order =  g.fromJson(jsonItem, Order.class);
		Hotel hotel=app.getHotel(order.getHotelId());
		int orderId=order.getOrderId();
		hotel.getOrders().get(orderId).setRating(order.getRating());
		user.getCurrentOrders().remove(orderId);
		user.getMyOrders().put(orderId, order);
		 hotel.calculateRating();
		return response;
	}

}
