package hotel;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Map;


public class UserClient extends Client {

	private static InputHandler input;
	
	public static ItemQuantity addItem(Map<Integer,Item> items)
	{
		
		System.out.println("Enter itemId ");
		int itemId=input.getIntegerInput(1,items.size());
		System.out.println("Enter quantity");
		int quantity=input.getIntegerInput();
		Item item=items.get(itemId);
		int subtotal=quantity*item.getPrice();
		return new ItemQuantity(itemId,quantity,subtotal,item.getName(),item.getPrice());
	
	}
	
	public Map<Integer,Order> getMyOrders(int userId)
	{
		return server.getClientOrders(userId);
	}
	public Map<Integer,Order> getCurrentOrders(int userId)
	{
		return server.getClientCurrentOrders(userId);
	}
	
	public void rateOrder(Order order)
	{
		 server.rateOrder(id,order);
	}
	
	public static void main(String[] args) {
		
	    input=new InputHandler();
		UserClient userClient=new UserClient();
        Service service=Service.getInstance();
		userClient.loginToServer("user");
		
		if(userClient.isLogged)
		{
			System.out.println("1.Hotels 2.MyOrders 3.CheckOrderStatus 4.Exit");
			int userId=userClient.id;
			int choice=input.getIntegerInput(1, 4);
			while(choice<4)
			{switch(choice)
			 {
			  case C.HOTELS:{
				            
				             Map<Integer,HotelDetail> map = userClient.getHotelList();
				             service.displayHotelList(map);
				             
				             System.out.println("Enter hotel Id");
				             int hotelId=input.getIntegerInput();
				             System.out.println("1.Make Order 2. Leave hotel and back");
				             int ch=input.getIntegerInput(1,3);
				             switch(ch)
				             {
				           
				             case C.MAKE_ORDER:{
				            	                 Map<Integer,Item> menu =userClient.getMenu(hotelId);
				            	                 service.displayMenu(menu);
				            	                 Discount discount=userClient.getDiscount(hotelId);
				            					 int c=1,total=0;
				            					 HotelDetail hdetail=userClient.getHotelDetail(hotelId);
				            					 if(hdetail.isOpen())
					            					 { List<ItemQuantity> itemslist=new ArrayList<ItemQuantity>();
					            					 while(c==1)
					            				     {   
					            				    	 boolean isItemRepeated=false;
					            				    	 ItemQuantity iq=addItem(menu);
					            				    	 
					            				         for(int i=0;i< itemslist.size();i++)
					            				         {
					            				        	 if(itemslist.get(i).getItemId()==iq.getItemId())
					            				        	 {
					            				        		 ItemQuantity itemInList=itemslist.get(i);
					            				        		 itemInList.setQuantity(iq.getQuantity()+itemInList.getQuantity());
					            				        		 total-=itemInList.getSubtotal();
					            				        		 itemInList.setSubtotal(iq.getSubtotal()+itemInList.getSubtotal());
					            				        		 iq=itemInList;
					            				        		 isItemRepeated=true;
					            				        		 break;
					            				        	 }
					            				         }
					            				        
					            				         if(!isItemRepeated)
					            				    	 itemslist.add(iq);
					            				    	 iq.setSubtotal(discount.applyItemDiscount(iq));
					            				    	 total+=iq.getSubtotal();
					            				    	
					            				    	 service.viewOrder(itemslist);
					            				    	 System.out.println("Enter choice \n1.Add more items \t 2.Place Order \t3.Hold Order");
					            				    	 c=input.getIntegerInput(1,3);
					            				    	 
					            				     }
					            					 
					            					 int d=total;	 
				            					     total=discount.applyTotalDiscount(total);
				            					     total=discount.applyTimeDiscount(new Date(), total);
				            					     d=d-total;
				            					     int rating=0;
					            					 userClient.makeOrder(hotelId, itemslist, total, d, rating);
	                                                 System.out.println("Order Placed Successfully");
					            					 }
				            					 else
				            					 {
				            						 System.out.println("Hotel is Closed ");
				            					 }
				            					 break;
				                               }
				             case C.LEAVE_AND_BACK:{
				            	                    break;
				                                   }
				             }
				             break;
			               }
			 case C.MYORDERS:{
									 Map<Integer,Order> ordersmap = userClient.getMyOrders(userId);
						             List<Order> orders=new ArrayList<Order>(ordersmap.values());
						             service.displayOrders(orders);
						             System.out.println("Enter SNO to view the order 0 to back");
						             int i=input.getIntegerInput(0,orders.size());
						         
						             while(i!=0)
						             {
						            	 Order order=orders.get(i-1);
						            	 service.viewOrder( order, userClient.getHotelList());
						            	 System.out.println("Enter SNO to view the order 0 to back");
							              i=input.getIntegerInput(0,orders.size());
						             }
						           break;  
				                 
			                 }
			 case 3:{int i=1;
					 do{Map<Integer,Order> ordersmap = userClient.getCurrentOrders(userId);
		             List<Order> orders=new ArrayList<Order>(ordersmap.values());
		             service.displayOrders(orders);
		             System.out.println("Enter SNO to view the order status 0 to back");
		                i=input.getIntegerInput(0,orders.size());
		               if(i!=0) 
		               { Order order=orders.get(i-1);
		                 service.displayStatus(order);
		                if(order.getStatus()==C.DELIVERED)
		                {
		                	
		                	int rating=0;
	   					     System.out.println("Enter 1.Rate your Order 2.Skip");
	   					     int ratechoice=input.getIntegerInput(1,2);
	   					     if(ratechoice==1)
	   					     { System.out.println("Enter your rating 1 to 5");
	   					      rating=input.getIntegerInput(1,5);
	   					     }
   					     order.setRating(rating);
		                 userClient.rateOrder(order);
		               }
		            	 
		               } }while(i!=0);
	             
                    break;
                  }
			}
			System.out.println("1.Hotels 2.MyOrders 3.CheckOrderStatus 4.Exit");
			choice=input.getIntegerInput(1, 4);
		}
	 }
		
	}

}
