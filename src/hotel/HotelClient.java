package hotel;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Set;



import com.google.gson.Gson;

public class HotelClient extends Client {
	
	
	private static Gson gson;
	private static Service service;
	
	public Set<Order> getOrders(int hotelId,int option,String date1,String date2)
	  {
		 return server.getOrders(hotelId, option, date1, date2);
	  }
	  
	  public Set<Order> getOrders(int hotelId,int option,int total)
	  {
		  return server.getOrders(hotelId, option, total);
	  }
	  
	  public Set<Order> getOrders(int hotelId,int itemId)
	  {
		  return server.getOrders(hotelId, itemId);
	  }
	  
	

	public static void main(String[] args) {
		
		service=Service.getInstance();
		InputHandler input=new InputHandler();
		gson=new Gson();
		HotelClient hotelClient=new HotelClient();
		hotelClient.loginToServer("hotel");
		if(hotelClient.isLogged)
		{
			int hotelId=hotelClient.id;
			HotelDetail hdetail=hotelClient.getHotelDetail(hotelId);
			System.out.println("1.Add Menu Item 2.List Order 3.Discount 4.Open/Close 5.Exit");
			int choice=input.getIntegerInput(1, 4);
			while(choice<5)
			{
			switch(choice)
			{
			 case C.ADD_MENU:{
				   Map<Integer,Item> menu=hotelClient.getMenu(hotelId);
				 service.displayMenu(menu);
				    
					System.out.println("Enter 1.Add item 2.Remove item 0 to back");
					int ch=input.getIntegerInput(0,2);
					while(ch!=0)
						{
						if(ch==1)
						{
						System.out.println("Enter ItemId");
						int itemId=input.getIntegerInput();
						System.out.println("Enter Name");
						String name=input.getStringInput();
						System.out.println("Enter price");
						int price=input.getIntegerInput();
						Item item=new Item(itemId,name,price);
						hotelClient.addItem(item,hotelId);
						System.out.println("Item added successfully");
						}
						else
						{
							System.out.println("Enter itemId");
							int itemId=input.getIntegerInput();
							hotelClient.removeItem(itemId,hotelId);
						}
						service.displayMenu(hotelClient.getMenu(hotelId));
						System.out.println("Enter 1 to add items and 0 to back");
					    ch=input.getIntegerInput(0,1);
				         }
					   break;       
			               }
			 case C.LIST_ORDER:{
				  
				 Set<Order> orderList = null;
				 
				 int cn=0;
                 List<Set<Order>> constraints=new ArrayList<Set<Order>>();
                 List<Integer> operator=new ArrayList<Integer>();
				  do{System.out.println("Based on \n1.Date\n2.Total\n3.Items");   
                    cn = input.getIntegerInput(1,3);
                   
   			    
				     switch(cn)
				     {
				     
				     case C.BASED_ON_DATE:System.out.println("\n1.After\n2.Before\n3.Between");
				            int option=input.getIntegerInput(1,3);
				            String date1=input.getDateInput().toString(),date2=null;
				            System.out.println("Enter Date ");
				           
				            if(option==3)
				            {
				            	System.out.println("Enter Date ");
				            	date2=input.getDateInput().toString();
					            
					        }
				            orderList=hotelClient.getOrders(hotelId, option, date1, date2);
				            break;
				     case C.BASED_ON_TOTAL:
				    	    System.out.println("Enter option 1.greater than 2.Less than 3.equal"); 
				            int op=input.getIntegerInput(1,3);
				            System.out.println("Enter Total");
				            int total=input.getIntegerInput();
				            orderList=hotelClient.getOrders(hotelId, op, total);
				           
				            break;
				     case C.BASED_ON_ITEM:
				    	    service.displayMenu(hotelClient.getMenu(hotelId));
				    	    System.out.println("Enter item id"); 
				    	    int itemId=input.getIntegerInput();
				    	    orderList=hotelClient.getOrders(hotelId, itemId);
				    	    
				            break;
				     }
				     
				     System.out.println("1.ADD constraint(AND) 2.OR Constraint 3.Display Result");
				     cn=input.getIntegerInput(1,3);
				     if(cn==1)
				     {
				    	operator.add(new Integer(1)) ;
				     }
				     if(cn==2)
				     {
				    	operator.add(new Integer(2)) ;
				     }
				     constraints.add(orderList);
                }while(cn<3);
      	      Constraint evaluator=new Constraint();
      	      orderList=evaluator.evaluate(operator,constraints);
			   List<Order> orders=new ArrayList(orderList);     
			  service.displayOrders(orders);
			  System.out.println("Enter orderId to view the order 0 to back");
	             int orderId=input.getIntegerInput(0,orders.size());
	         
	             while(orderId!=0)
	             {
	            	 Order order=orders.get(orderId-1);
	            	 service.viewOrder( order, hotelClient.getHotelList());
	            	 System.out.println("Enter orderId to view the order 0 to back");
		              orderId=input.getIntegerInput(0,orders.size());
	             }
			  break;
			          }
			 case C.MODIFY_DISCOUNT:{
				 
				 int option;
				 Discount discount;
		         do{
		        	discount=hotelClient.getDiscount(hotelId);
		        	System.out.println(discount);
		        	
		          System.out.println("To modify \n1.Total Discount \n2.Time Discount \n3.Item Discount \n4.Membership Discount \n5.Back");
		         option=input.getIntegerInput(1,5);
		         
   	             
   
		         switch(option)
		         {
		         case C.TOTAL:System.out.println("Enter total percentage discount  ");
		               int totalPercentage=input.getIntegerInput(0,100);
		               System.out.println("Enter total amount");
		               int baseTotal=input.getIntegerInput(0,10000);
		               hotelClient.setDiscountTotal(totalPercentage, baseTotal);
		               
		               break;
		         case C.TIME:System.out.println("Enter time percentage discount ");
	                   int timePercentage=input.getIntegerInput(0,100);
	                   System.out.println("Enter from hour");
	                   int toTime=input.getIntegerInput(0,24);
	                   System.out.println("Enter to hour");
	  			       int fromTime=input.getIntegerInput(0,24);
	  			       hotelClient.setDiscountTime(timePercentage, toTime, fromTime);

	                   break;  
		         case C.ITEM:System.out.println("Hotel Menu");
		                service.displayMenu(hotelClient.getMenu(hotelId));
		                System.out.println("Discount Menu");
		                service.displayDiscountMenu(hotelClient.getMenu(hotelId), discount);
		                System.out.println("1.Add item discount or 0 to Back");
		                int ch=input.getIntegerInput(0,1);
		                while(ch!=0)
		                {
		                	System.out.println("Enter itemId ");
		                	int itemId=input.getIntegerInput(1,100);
		                	System.out.println("Enter item count");
		                	int itemCount=input.getIntegerInput(0,100);
		                	System.out.println("Enter item discount percentage");
		                	int discountPercentage=input.getIntegerInput(0,100);
		                	 hotelClient.setDiscountItem(discountPercentage, itemId, itemCount);

		                	System.out.println("1.Add item discount and 0 to Back");
			                ch=input.getIntegerInput(0,1);
		                }
		                break;
		         case C.MEMBERSHIP:System.out.println("Enter membership  percentage discount  ");
					                 int membershipPercentage=input.getIntegerInput(0,100); 
					                 System.out.println("Enter membership point");
					                int membershipPoint=input.getIntegerInput(0,100);
					                hotelClient.setDiscountMembership(membershipPercentage, membershipPoint);
				                    break;
		         }}while(option<5);
		        
				 
             }
			 case C.OPEN_CLOSE:{
				 
				  if(hdetail.isOpen())
				  {
					  System.out.println("Hotel is Opened Now Enter 1 to close 0 to back");
					  int status=input.getIntegerInput(0,1);
					  if(status==1)
						  hotelClient.setHotelStatus(hotelId, 0);
					  
				  }
				  else
				  {
					  System.out.println("Hotel is Closed Now Enter 1 to open 0 to back");
					  int status=input.getIntegerInput(0,1);
					  if(status==1)
						  hotelClient.setHotelStatus(hotelId, 1);
				  }
				 
                   }
			}
			System.out.println("1.Add Menu Item 2.List Order 3.Discount 4.Open/ Close 5.Exit");
			choice=input.getIntegerInput(1, 5);
			}
		}
		

	}

}
