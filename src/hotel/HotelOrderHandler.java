package hotel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

public class HotelOrderHandler extends Handler {

	
	@Override
	public String doService(Map<String,Object> parameters) {
		
		ServerHelper helper=ServerHelper.getInstance();
		int code= Integer.parseInt((String) parameters.get("code")) ;
		Integer hotelId= Integer.parseInt((String) parameters.get("hotelId")) ;
		Set<Order> orderList = new HashSet<Order>(app.getHotel(hotelId).getOrders().values());
	        
	        System.out.println("code is" +code);
	        
	       switch(code)
	        {
	        case C.BASED_ON_DATE:{
	        	int option=Integer.parseInt((String) parameters.get("option"));
	        	SimpleDateFormat ft = 
					      new SimpleDateFormat ("yyyy-MM-dd");
	        	String dateString=(String) parameters.get("date1");
	        	Date date1=null,date2=null;
				try {
					date1 = ft.parse(dateString);
					if(option==3)
					{ dateString=(String) parameters.get("date2");
	        	     date2=ft.parse(dateString);
					}
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
	        	
				
				orderList=helper.getOrderOnDate(option, orderList, date1, date2);
	       	           
	                     }
	        case C.BASED_ON_ITEM:{
	        	
		        	int itemId=Integer.parseInt((String) parameters.get("itemId"));
		     	    orderList=helper.displayOrderOnItem(itemId, orderList);
		       	            
	       	            break;
	                      }
	        case C.BASED_ON_TOTAL:{
	        	
	        	int option=Integer.parseInt((String) parameters.get("option"));
	        	int total=Integer.parseInt((String) parameters.get("total"));
                orderList=helper.displayOrderOnTotal( total, option, orderList);
   	           
   	            break;
	        
	         }
	        }
	        
	        Gson gson=new Gson();
	        String response=gson.toJson(orderList);
	        return response;
		
	}
}
