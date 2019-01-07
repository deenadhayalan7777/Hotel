package hotel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;




public class App {
	
	private static List<Item> items;
	private static List<Order> orders;
	private static Map<Integer,Hold> holdOrders;
    private static Scanner in;
    private static Map<String,Integer> members;
	private static Discount discount;
	private static InputHandler input;
	private Map<Integer,String> hotellist;
	private Map<Integer,Hotel> hotels;
	private Map<Integer,User> users;
	private FileHandler fh;
	
	public App()
	{
		 hotellist=new HashMap<Integer,String>();
		 hotels=new HashMap<Integer,Hotel>();
		 users=new HashMap<Integer,User>();
		 fh=new FileHandler();
		 hotels=(Map<Integer, Hotel>) fh.read("hotels.txt");
		 users=(Map<Integer, User>) fh.read("users.txt");
		 
	}
	 /*
	public static ItemQuantity addItem()
	{
		System.out.println("Enter itemId and quantity");
		int itemId=input.getIntegerInput();
		int quantity=input.getIntegerInput();
		int subtotal=quantity*items.get(itemId-1).getPrice();
		return new ItemQuantity(itemId,quantity,subtotal);
		
		
	}
	
	public int login(String username,String password)
	{
		
		
		return 0;
		
	}
	
	
	public static void main(String[] args) throws ParseException {
		
			//Map<Integer,List<Item>> menulist=new HashMap<Integer,List<Item>>();//hotelId -menulist
		
		input=new InputHandler();
		FileHandler fh=new FileHandler();
		Service s=Service.getInstance();
		 holdOrders=new HashMap<Integer,Hold>();
		 //orders=new ArrayList<Order>();
		
		    discount=(Discount)fh.read("discount.txt");
            members=(Map<String,Integer>)fh.read("members.txt");
            items = (List<Item>)fh.read("menu1.txt"); 
            orders=(List<Order>)fh.read("order1.txt");
            Item.count=items.size();
           Order.count=orders.size(); 
            //Hold.count=Collections.max(holdOrders.keySet());
            
            int choice=0;
		do
		{	
		System.out.println("Enter choice \n1. Add Menu Items \n2.Make Order \n3.List Order\n4.Discount\n5.Exit");
		in=new Scanner(System.in);
		 choice=input.getIntegerInput();
		switch(choice)
		{
		case C.ADD_MENU: {
				s.displayMenu(items);
				System.out.println("Enter 1 to add items and 0 to back");
				int ch=input.getIntegerInput(0,1);
				while(ch!=0)
					{System.out.println("Enter Name and price");
					String name=in.next();
					int price=input.getIntegerInput();
					Item item=new Item(name,price);
					items.add(item);
					System.out.println("Item added successfully");
					
					System.out.println("Enter 1 to add items and 0 to back");
				    ch=input.getIntegerInput(0,1);
			         }
				s.displayMenu(items);
				fh.write("menu1.txt",items);
			    break;
			    }
		case 2: {
			     s.displayMenu(items);
			     System.out.println("1.New order \t2.Resume Order ");
			     int c=0,total=0,d=0;
			     List<ItemQuantity> itemslist=new ArrayList<ItemQuantity>();
			     
			     c=input.getIntegerInput(1,2);
			     if(c==C.RESUME_ORDER)
			     {
			    	 
			    	System.out.println("Enter hold Id"); 
			    	int holdId=input.getIntegerInput();
			    	Hold holdOrder=holdOrders.get(holdId);
			    	itemslist=holdOrder.getItemsList();
			    	s.viewOrder(items,itemslist);
			    	total=holdOrder.getTotal();
			    	d=total;
			    	holdOrders.remove(holdId);
			    	//fhold.write(holdOrders);
			    	 System.out.println("Enter choice \n1.Add more items \t 2.Place Order \t3.Hold Order");
			    	 c=input.getIntegerInput(1,3);
			     }
			     
			     
			     while(c==1)
			     {   
			    	 boolean isItemRepeated=false;
			    	 ItemQuantity iq=addItem();
			         for(int i=0;i<itemslist.size();i++)
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
			    	 s.viewOrder(items,itemslist);
			    	 System.out.println("Enter choice \n1.Add more items \t 2.Place Order \t3.Hold Order");
			    	 c=input.getIntegerInput(1,3);
			    	 
			     }
			     if(c==C.HOLD_ORDER)
			     {   
			    	  Hold holdOrder=new Hold(itemslist,total);
			    	 holdOrders.put(holdOrder.getHoldId(),holdOrder);
			    	System.out.println("Your order is in hold Your Hold Id is "+ holdOrder.getHoldId() );
			    	
			     }
			     else
			     {d=total;	 
			     total=discount.applyTotalDiscount(total);
			     total=discount.applyTimeDiscount(new Date(), total);
			     String phoneNo="-";
			     System.out.println("Do you have phoneNo 1.Yes 2.No");
			     int ph=input.getIntegerInput(1, 2);
			     if(ph==1)
				  {   System.out.println("Enter phone number");
				       phoneNo=in.next();
				      int count=1;
					     if(members.containsKey(phoneNo))
					     {   count+=members.get(phoneNo);
					    	 if(count>=discount.getMembershipPoint())
					    	 {
					    		 System.out.println("you are eligble for membership discount 1.To Apply 2.Apply Later");
					    		 int ch=input.getIntegerInput(1,2);
					    		 if(ch==1)
					    		 {
					    			 total=discount.applyMembershipDiscount(total);
					    			 count-=discount.getMembershipPoint();
					    			 
					    		 }
					    	 }
					     }
					     members.put(phoneNo, count);
					     fh.write("members.txt",members);
				  }
			    
			     d=d-total;
			     
			     orders.add(new Order(itemslist,total,phoneNo,d));
			     System.out.println("Order Placed");
			     
			     fh.write("order1.txt",orders);
			     }
			     break;
			     
		        }
		case C.DISPLAY_ORDER: {  int ch=0;
		           System.out.println("1.All orders\n2.Based on Constraint \3.Back ");
		           ch=input.getIntegerInput(1,3);
		           switch(ch)
		           {
		           case 1:s.displayOrders(orders);
		                  break;
		           case 2: int cn=0;
		                   List<Set<Order>> constraints=new ArrayList<Set<Order>>();
		                   List<Integer> operator=new ArrayList<Integer>();
		                  Set<Order> orderList;
		        	      do{System.out.println("Based on \n1.Date\n2.Total\n3.Items");   
		                   cn = input.getIntegerInput(1,3);
		                   orderList=new HashSet<Order>(orders);
						    switch(cn)
						     {
						     
						     case C.BASED_ON_DATE:System.out.println("\n1.After\n2.Before\n3.Between");
						            int option=input.getIntegerInput(1,3);
						    	    
						    	    orderList=s.displayOrderOnDate(option,orderList);
						            break;
						     case C.BASED_ON_TOTAL:System.out.println("Enter option 1.greater than 2.Less than 3.equal"); 
						            int op=input.getIntegerInput(1,3);
						            System.out.println("Enter Total");
						            orderList=s.displayOrderOnTotal(orders,input.getIntegerInput(),op,orderList);
						            break;
						     case C.BASED_ON_ITEM:s.displayMenu(items);
						    	    System.out.println("Enter item id");  
						    	    orderList=s.displayOrderOnItem(input.getIntegerInput(1,items.size()),orderList);
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
		                  s.displayOrders(new ArrayList<Order>(orderList));
		           case 3:break;       
		          }
		         if(ch!=3)  
		         { int c;
			     System.out.println("Enter Order Id to view or 0 to back");
			     c=input.getIntegerInput(0,orders.size());
			     while(c!=0){
			     
			     s.viewOrder(items,orders.get(c-1));
			     System.out.println("Enter Order Id to view or 0 to back");
			     c=input.getIntegerInput(0,orders.size());
		         }
		         }
			      break;
			     }
			    
		        
		case C.DISCOUNT: int option;
		         do{
		        	System.out.println(discount);
		        	
		          System.out.println("To modify \n1.Total Discount \n2.Time Discount \n3.Item Discount \n4.Membership Discount \n5.Back");
		         option=input.getIntegerInput(1,5);
		         switch(option)
		         {
		         case C.TOTAL:System.out.println("Enter total percentage discount  ");
		               int totalPercentage=input.getIntegerInput(0,100);
		               System.out.println("Enter total amount");
		               int baseTotal=input.getIntegerInput(0,10000);
		               discount.setBaseTotal(baseTotal);
		               discount.setTotalPercentage(totalPercentage);
		               break;
		         case C.TIME:System.out.println("Enter time percentage discount ");
	                   int timePercentage=input.getIntegerInput(0,100);
	                   System.out.println("Enter from hour");
	                   int toTime=input.getIntegerInput(0,24);
	                   System.out.println("Enter to hour");
	  			       int fromTime=input.getIntegerInput(0,24);
	                   discount.setFromTime(fromTime);
	                   discount.setTimePercentage(timePercentage);
	                   discount.setToTime(toTime);
	                   break;  
		         case C.ITEM:System.out.println("Hotel Menu");
		                s.displayMenu(items);
		                System.out.println("Discount Menu");
		                s.displayDiscountMenu(items,discount);
		                System.out.println("1.Add item discount or 0 to Back");
		                int ch=input.getIntegerInput(0,1);
		                while(ch!=0)
		                {
		                	System.out.println("Enter itemId ");
		                	int itemId=input.getIntegerInput(1,items.size());
		                	System.out.println("Enter item count");
		                	int itemCount=input.getIntegerInput(0,100);
		                	System.out.println("Enter item discount percentage");
		                	int discountPercentage=input.getIntegerInput(0,100);
		                	discount.getDiscountItems().put(itemId, new DiscountItem(itemId,itemCount,discountPercentage));
		                	s.displayDiscountMenu(items,discount);
		                	System.out.println("1.Add item discount and 0 to Back");
			                ch=input.getIntegerInput(0,1);
		                }
		                break;
		         case C.MEMBERSHIP:System.out.println("Enter membership  percentage discount  ");
					                 int membershipPercentage=input.getIntegerInput(0,100); 
					                 System.out.println("Enter membership point");
					                int membershipPoint=input.getIntegerInput(0,100);
					                discount.setMembershipPercentage(membershipPercentage);
				                    discount.setMembershipPoint(membershipPoint);
				                    break;
		         }}while(option<5);
		        
		         fh.write("discount.txt",discount);

		         
		  }
		
	}while(choice<5);
	
	*/
  

}
