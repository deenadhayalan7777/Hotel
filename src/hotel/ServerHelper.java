package hotel;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import com.google.gson.Gson;

public class ServerHelper {

	
   private static ServerHelper helper=null;
  
	private ServerHelper()
	{
		
	}
	
	public static ServerHelper getInstance()
	{
		if(helper==null)
			helper=new ServerHelper();
		return helper;
	}
	
	
	
	public  Set<Order> getOrderOnDate(int option,Set<Order> orderList,Date date1,Date date2) 
	{
		
		Set<Order> newList=new HashSet<Order>();
		
		for(Order order:orderList)
		{   switch(option)
			{
		     case 1:if(order.getDate().after(date1))
				     newList.add(order);
		             break;
		     case 2:if(order.getDate().before(date1))
		    	     newList.add(order);
		             break;
		     case 3: if(order.getDate().after(date1)
		    		 && order.getDate().before(date2))
		    	    newList.add(order);
		             break;
		 	}
		}
		return newList;
		
	}
	public  Set<Order> displayOrderOnItem(int itemId,Set<Order> orderList) 
	{
		
		Set<Order> newList=new HashSet<Order>();
		for(Order order:orderList)
		{   List<ItemQuantity> iqlist=order.getItemsList();
		    boolean containsItem=false;
		    for(ItemQuantity iq:iqlist)
		    {
		    	if(iq.getItemId()==itemId)
		    	{
		    		containsItem=true;
		    		break;
		    	}
		    }
			if(containsItem)
				newList.add(order);
		}
		return newList;
	}
	public  Set<Order> displayOrderOnTotal(int total,int option,Set<Order> orderList) 
	{
		
		Set<Order> newList=new HashSet<Order>();
		for(Order order:orderList)
		{   switch(option)
			 {
		      case 1:if(order.getTotal()>total)
				    newList.add(order);
			         break;
			  case 2:if(order.getTotal()<total) 
				      newList.add(order);
			         break;
			  case 3:if(order.getTotal()==total) 
				     newList.add(order);
			         break;  
			 }
		}
		return newList;
	}
	
}
