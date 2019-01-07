package hotel;


import java.util.Map;

import com.google.gson.Gson;



public class HotelDiscountHandler extends Handler{
	
	

	@Override
	public String doService(Map<String, Object> parameters) {
		
		int hotelId= Integer.parseInt((String) parameters.get("hotelId")) ;
		Discount discount=app.getHotel(hotelId).getDiscount();
		int code= Integer.parseInt((String) parameters.get("code")) ;
       
      
      	
       switch(code)
       {
        case C.TOTAL:   int totalPercentage=Integer.parseInt((String) parameters.get("totaldp")); 
             int baseTotal=Integer.parseInt((String) parameters.get("btotal")) ;
             discount.setBaseTotal(baseTotal);
             discount.setTotalPercentage(totalPercentage);
             break;
        case C.TIME:
             int timePercentage=Integer.parseInt((String) parameters.get("timedp"));
             int toTime=Integer.parseInt((String) parameters.get("fromtime")) ;
             int fromTime=Integer.parseInt((String) parameters.get("totime")) ;
             discount.setFromTime(fromTime);
             discount.setTimePercentage(timePercentage);
             discount.setToTime(toTime);
             break;  
        case C.ITEM:
              	int itemId=Integer.parseInt((String) parameters.get("itemId")) ;
              	int itemCount=Integer.parseInt((String) parameters.get("count")) ;
              	int discountPercentage=Integer.parseInt((String) parameters.get("itemdp")) ;
              	discount.getDiscountItems().put(itemId, new DiscountItem(itemId,itemCount,discountPercentage));
                break;
        case C.MEMBERSHIP:
			                 int membershipPercentage=Integer.parseInt((String) parameters.get("memberdp")) ;
			                  int membershipPoint=Integer.parseInt((String) parameters.get("point")) ;
			                discount.setMembershipPercentage(membershipPercentage);
		                    discount.setMembershipPoint(membershipPoint);
		                    break;
        case C.GET_DISCOUNT:
        	Gson gson=new Gson();
            String response=gson.toJson(discount);
            return response;
          
       }
        
         
        
       String response="1";
		return response;
	}
}
