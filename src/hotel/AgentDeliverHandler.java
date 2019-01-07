package hotel;

import java.util.Map;

import com.google.gson.Gson;

public class AgentDeliverHandler extends Handler{

	@Override
	public String doService(Map<String, Object> parameters) {
		
		String response="0";
		Gson g = new Gson(); 
		Integer agentId=Integer.parseInt((String) parameters.get("agentId"));
		if(app.getAgent(agentId)==null)
			System.out.println(" agent is null in assignment");
		Agent agent=app.getAgent(agentId);
		Integer code=Integer.parseInt((String) parameters.get("code"));
		String jsonItem=(String) parameters.get("order");
		Order order =  g.fromJson(jsonItem, Order.class);
		Hotel hotel=app.getHotel(order.getHotelId());
		User user=app.getUser(order.getUserId());
		int orderId=order.getOrderId();
		if(code==1)
		{
			
			if(agent.getCurrentOrders().size()<=C.ORDER_LIMIT)
			{
				
				
				order.setAgentId(agentId);
				order.setStatus(2);
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hotel.getCurrentOrders().remove(orderId);
				hotel.getOrders().put(orderId, order);
				user.getCurrentOrders().get(orderId).setAgentId(agentId);
				user.getCurrentOrders().get(orderId).setStatus(2);
				agent.getCurrentOrders().put(orderId, order);
				response ="1";
			}
		
		}
		else
		{		
		    agent.getCurrentOrders().remove(orderId);
		    agent.getMyOrders().put(orderId, order);
		    hotel.getOrders().get(orderId).setStatus(3);
		    user.getCurrentOrders().get(orderId).setStatus(3);
		  
		}
		
		
        return response;
	}

}
