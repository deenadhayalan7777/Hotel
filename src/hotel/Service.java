package hotel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Service {
	
	private static Service service=null;
	
	private Service()
	{
		
	}
	
	public static Service getInstance()
	{
		if(service==null)
			service=new Service();
		return service;
	}
	
	public void displayMenu(Map<Integer,Item> items)
	{
		if(items.size()>0)
		{   
			System.out.println("ItemID \t Name \t Price");
			for(Item item:items.values())
			{
				System.out.println(item.getItemId()+"\t"+item.getName()+"\t"+item.getPrice());
			}
		}
		else
			System.out.println("No Items");
	}
	public void displayDiscountMenu(Map<Integer,Item> items,Discount discount)
	{
		if(items.size()>0 && discount.getDiscountItems().size()>0)
		{   
			System.out.println("ItemID \t Name \t Price \tCount \t Discount%");
			
			for (Map.Entry<Integer, DiscountItem> entry: discount.getDiscountItems().entrySet()) {
			     int itemId = entry.getKey();
			    DiscountItem discountItem = entry.getValue();
			    Item item=items.get(itemId);
			    System.out.println(item.getItemId()+"\t"+item.getName()+"\t"+item.getPrice()+"\t"+
			    discountItem.getQuanitityLimit()+"\t"+discountItem.getDiscountPercentage());
			}
			
		}
		else
			System.out.println("No Items");
	}
	
	public void displayOrders(List<Order> orders)
	{
		if(orders.size()>0)
		{   int i=1;
			System.out.println("SNO orderID Total Discount \t\tDate\t\t\tphoneNo");
			for(Order order:orders)
			{
				System.out.println(i+"\t"+order.getOrderId()+"\t"+order.getTotal()+"\t"+order.getDiscount()+"\t"+order.getDate()+"\t"+order.getPhoneNo());
				i++;
			}
		}
		else
			System.out.println("No Orders");
	}
	public void viewOrder(List<ItemQuantity> itemsList)
	{
		System.out.println("ItemID Name  Price  Quantity  Subtotal");
		for(ItemQuantity iq:itemsList)
		{
			
			System.out.println(iq.getItemId()+"\t"+iq.getName()+"\t"+iq.getPrice()+"\t"+iq.getQuantity()+"\t"+iq.getSubtotal());
		}
	}
	public void viewOrder(Order order,Map<Integer,HotelDetail>  hotellist)
	{
		 String hotelname=hotellist.get(order.getHotelId()).getUsername();
		 System.out.println("---------------"+hotelname+"------------------");
		System.out.println("\nORDER ID : "+order.getOrderId()+"\t"+order.getDate()+"\tPHONE : "+order.getPhoneNo()+"\n");
		
		viewOrder(order.getItemsList());
		System.out.println("\nTotal Amount:\t\t\t"+(order.getTotal()+order.getDiscount()));
		System.out.println("Discount Applied:\t\t"+order.getDiscount());
		System.out.println("Net Total:\t\t\t"+order.getTotal()+"\n");
	}
	
	public void displayHotelList(Map<Integer,HotelDetail> hotels)
	{
		for(HotelDetail hotel:hotels.values())
         	{System.out.println(hotel.getHotelId() +"\t "+hotel.getUsername()+"\t "+String.format("%.02f", hotel.getRating())+"\t");
        	 if(!hotel.isOpen()) 
         	 System.out.print("closed");
         	}
	}
	
	public  void displayStatus(Order order)
	{
		switch(order.getStatus())
		{
		case C.ACCEPTED: {System.out.println(" Order is Accepted  waiting for delivery Agent ");
		                break;
		                }
		case  C.ASSIGNED:{System.out.println("Order is Assigned to delivery Agent "  );
			             break;
		              }
		case  C.DELIVERED:{System.out.println("Order is Delivered");
                         break;
                         }
		}
	}
     public static String cryptWithMD5(String text){
		
		StringBuffer sb = new StringBuffer();
		 
	        byte[] digested;
			try {
				digested = MessageDigest.getInstance("MD5").digest(text.getBytes());
				 sb = new StringBuffer();
		        for(int i=0;i<digested.length;i++){
		            sb.append(Integer.toHexString(0xff & digested[i]));
		        }
		        
			} catch (NoSuchAlgorithmException e) {
				
			}  
	        
			return sb.toString();


	   }
	
	
}
