package hotel;

import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ServerAccess {

  private static ServerAccess helper=null;
  
    public CloseableHttpClient httpclient;
	private Gson gson;
	public HttpPost post;
	public ResponseHandler<String> responseHandler;
	
	private ServerAccess()
	{
		 httpclient = HttpClients.createDefault();
		 gson=new Gson();
		 responseHandler = new ResponseHandler<String>() {
         
            @Override
            public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }

        };
	}
	
	public static ServerAccess getInstance()
	{
		if(helper==null)
			helper=new ServerAccess();
		return helper;
	}
	
	public int login(String username,String password,String context)
	{
		int id=0;
		context=context+"/login";
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    	
    	urlParameters.add(new BasicNameValuePair("username", username));
    	urlParameters.add(new BasicNameValuePair("password", password));
    	
    	String response=getResponse(urlParameters,context);
    	id=Integer.parseInt(response);
    	
    	return id;
	}
	
	public int signUp(String username,String password,String phone,String context)
	{int id=0;
	 context=context+"/signup";
	List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
	
	urlParameters.add(new BasicNameValuePair("username", username));
	urlParameters.add(new BasicNameValuePair("password", password));
	urlParameters.add(new BasicNameValuePair("phone", phone));
	
	String response=getResponse(urlParameters,context);
	id=Integer.parseInt(response);

    return id;
	}
	
	
	public void addItem(Item item,int hotelId)
	{
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("hotelId", Integer.toString(hotelId)));
        urlParameters.add(new BasicNameValuePair("code", Integer.toString(1)));
        String itemString= gson.toJson(item);
        urlParameters.add(new BasicNameValuePair("item", itemString));
        getResponse(urlParameters, "hotel/additem");
        
	}
	public void removeItem(int itemId,int hotelId)
	{
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("hotelId", Integer.toString(hotelId)));
        urlParameters.add(new BasicNameValuePair("itemId", Integer.toString(itemId)));
        urlParameters.add(new BasicNameValuePair("code", Integer.toString(2)));
        getResponse(urlParameters, "hotel/additem");
        
	}
	
	public Map<Integer,Item> getMenu(int hotelId)
	{
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("hotelId", Integer.toString(hotelId)));
        String menulist=getResponse(urlParameters, "hotel/menu");
        Map<Integer,Item> menu =new Gson().fromJson(menulist, new TypeToken<HashMap<Integer,Item>>(){}.getType());
		
       return menu;
	}
	
	public Discount getDiscount(int hotelId)
	{
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("hotelId", Integer.toString(hotelId)));
        urlParameters.add(new BasicNameValuePair("code", Integer.toString(C.GET_DISCOUNT)));
        
        String menulist=getResponse(urlParameters, "hotel/discount");
        
        Discount discount =new Gson().fromJson(menulist, Discount.class);
        return discount;
		
	}
	
	public HotelDetail getHotelDetail(int hotelId)
	{
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("hotelId", Integer.toString(hotelId)));
        urlParameters.add(new BasicNameValuePair("code", Integer.toString(1)));
        
        String menulist=getResponse(urlParameters, "hotel/getdetail");
        
        HotelDetail hdetail =new Gson().fromJson(menulist, HotelDetail.class);
        return hdetail;
		
	}
	public void setHotelStatus(int hotelId,int status)
	{
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("hotelId", Integer.toString(hotelId)));
        urlParameters.add(new BasicNameValuePair("code", Integer.toString(2)));
        urlParameters.add(new BasicNameValuePair("status", Integer.toString(status)));
        
        getResponse(urlParameters, "hotel/getdetail");
     	
	}
	
	public Map<Integer,HotelDetail> getHotelList()
	{
		 List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            
         String hotellist=getResponse(urlParameters, "hotel/hotellist");
         Map<Integer,HotelDetail> map = new Gson().fromJson(hotellist, new TypeToken<HashMap<Integer,HotelDetail>>(){}.getType());
         return map;
	}
	
  public Map<Integer,Order> getClientOrders(int userId)
  {
	  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		 urlParameters.add(new BasicNameValuePair("userId", Integer.toString(userId)));
		 urlParameters.add(new BasicNameValuePair("code", Integer.toString(2)));
      String orderlist=getResponse(urlParameters, "user/myorder");
      Map<Integer,Order> ordersmap = new Gson().fromJson(orderlist, new TypeToken<HashMap<Integer,Order>>(){}.getType());
      return ordersmap;
  }
  
  public Map<Integer,Order> getHotelCurrentOrders(int userId)
  {
	  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		 urlParameters.add(new BasicNameValuePair("hotelId", Integer.toString(userId)));
      String orderlist=getResponse(urlParameters, "hotel/currentorder");
      Map<Integer,Order> ordersmap = new Gson().fromJson(orderlist, new TypeToken<HashMap<Integer,Order>>(){}.getType());
      return ordersmap;
  }
  
  public Map<Integer,Order> getAgentOrders(int agentId)
  {
	  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		 urlParameters.add(new BasicNameValuePair("agentId", Integer.toString(agentId)));
		 urlParameters.add(new BasicNameValuePair("code", Integer.toString(2)));
      String orderlist=getResponse(urlParameters, "agent/orders");
      Map<Integer,Order> ordersmap = new Gson().fromJson(orderlist, new TypeToken<HashMap<Integer,Order>>(){}.getType());
      return ordersmap;
  }
  
  public Map<Integer,Order> getAgentCurrentOrders(int agentId)
  {
	  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		 urlParameters.add(new BasicNameValuePair("agentId", Integer.toString(agentId)));
		 urlParameters.add(new BasicNameValuePair("code", Integer.toString(1)));
      String orderlist=getResponse(urlParameters, "agent/orders");
      Map<Integer,Order> ordersmap = new Gson().fromJson(orderlist, new TypeToken<HashMap<Integer,Order>>(){}.getType());
      return ordersmap;
  }
  
  public Map<Integer,Order> getClientCurrentOrders(int userId)
  {
	  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		 urlParameters.add(new BasicNameValuePair("userId", Integer.toString(userId)));
		 urlParameters.add(new BasicNameValuePair("code", Integer.toString(1)));
      String orderlist=getResponse(urlParameters, "user/myorders");
      Map<Integer,Order> ordersmap = new Gson().fromJson(orderlist, new TypeToken<HashMap<Integer,Order>>(){}.getType());
      return ordersmap;
  }
  
  public Set<Order> getOrders(int hotelId,int option,String date1,String date2)
  {
	  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		 urlParameters.add(new BasicNameValuePair("hotelId", Integer.toString(hotelId)));
		 urlParameters.add(new BasicNameValuePair("code",Integer.toString(1)));
		 urlParameters.add(new BasicNameValuePair("option",Integer.toString(option)));
		 urlParameters.add(new BasicNameValuePair("date1",date1));
		 urlParameters.add(new BasicNameValuePair("date1",date2));
		 String menulist=getResponse(urlParameters, "hotel/orders");
	     Set<Order> orderList =new Gson().fromJson(menulist, new TypeToken<HashSet<Order>>(){}.getType()); 
	     return orderList;
  }
  
  public Set<Order> getOrders(int hotelId,int option,int total)
  {
	  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		 urlParameters.add(new BasicNameValuePair("hotelId", Integer.toString(hotelId)));
		 urlParameters.add(new BasicNameValuePair("code",Integer.toString(2)));
		 urlParameters.add(new BasicNameValuePair("option",Integer.toString(option)));
		 urlParameters.add(new BasicNameValuePair("total",Integer.toString(total)));
		 String menulist=getResponse(urlParameters, "hotel/orders");
	     Set<Order> orderList =new Gson().fromJson(menulist, new TypeToken<HashSet<Order>>(){}.getType()); 
	     return orderList;
  }
  
  public Set<Order> getOrders(int hotelId,int itemId)
  {
	  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		 urlParameters.add(new BasicNameValuePair("hotelId", Integer.toString(hotelId)));
		 urlParameters.add(new BasicNameValuePair("code",Integer.toString(3)));
		 urlParameters.add(new BasicNameValuePair("itemId",Integer.toString(itemId)));
		 String menulist=getResponse(urlParameters, "hotel/orders");
	     Set<Order> orderList =new Gson().fromJson(menulist, new TypeToken<HashSet<Order>>(){}.getType()); 
	     return orderList;
  }
  
  public void makeOrder(int hotelId,int userId,List<ItemQuantity> items,int total,int discount,int rating)
	{
	  String il= gson.toJson(items) ;
	  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
      urlParameters.add(new BasicNameValuePair("hotelId", Integer.toString(hotelId)));
      urlParameters.add(new BasicNameValuePair("userId", Integer.toString(userId)));
		 urlParameters.add(new BasicNameValuePair("itemslist", il));
		 urlParameters.add(new BasicNameValuePair("total", Integer.toString(total)));
		 urlParameters.add(new BasicNameValuePair("discount", Integer.toString(discount)));
		 urlParameters.add(new BasicNameValuePair("rating", Integer.toString(rating)));
			
		 getResponse(urlParameters, "hotel/makeorder");
	}
  
  public void setDiscountTime(int hotelId,int timePercentage,int toTime,int fromTime)
  {
	  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
      urlParameters.add(new BasicNameValuePair("hotelId", Integer.toString(hotelId)));
      urlParameters.add(new BasicNameValuePair("fromtime", Integer.toString(fromTime)));
      urlParameters.add(new BasicNameValuePair("totime",Integer.toString(toTime)));
      urlParameters.add(new BasicNameValuePair("timedp", Integer.toString(timePercentage)));
      urlParameters.add(new BasicNameValuePair("code",Integer.toString(2)));
      
      getResponse(urlParameters, "hotel/discount");
    
  }
  public void setDiscountTotal(int hotelId,int totalPercentage,int baseTotal)	
  {
	  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
      urlParameters.add(new BasicNameValuePair("hotelId", Integer.toString(hotelId)));
      urlParameters.add(new BasicNameValuePair("code",Integer.toString(1)));
	  urlParameters.add(new BasicNameValuePair("btotal", Integer.toString(baseTotal)));
         urlParameters.add(new BasicNameValuePair("totaldp",Integer.toString(totalPercentage)));
         
         getResponse(urlParameters, "hotel/discount");
	  
  }
  public void setDiscountItem(int hotelId,int itemPercentage,int itemId,int count)
  {
	  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
      urlParameters.add(new BasicNameValuePair("hotelId", Integer.toString(hotelId)));
      urlParameters.add(new BasicNameValuePair("itemId", Integer.toString(itemId)));
         urlParameters.add(new BasicNameValuePair("count",Integer.toString(count)));
         urlParameters.add(new BasicNameValuePair("itemdp", Integer.toString(itemPercentage)));
         urlParameters.add(new BasicNameValuePair("code",Integer.toString(3)));
      
      getResponse(urlParameters, "hotel/discount");
    
  }
  public void setDiscountMembership(int hotelId,int memberPercentage,int point)
  {
	  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
      urlParameters.add(new BasicNameValuePair("hotelId", Integer.toString(hotelId)));
      urlParameters.add(new BasicNameValuePair("point", Integer.toString(point)));
         urlParameters.add(new BasicNameValuePair("memberdp",Integer.toString(memberPercentage)));
         urlParameters.add(new BasicNameValuePair("code",Integer.toString(4)));
      
      getResponse(urlParameters, "hotel/discount");
  }
  
  
  public int assignOrder(int agentId,Order order)
  {
	  String orderString= gson.toJson(order) ;
	  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
      urlParameters.add(new BasicNameValuePair("agentId", Integer.toString(agentId)));
      urlParameters.add(new BasicNameValuePair("code", Integer.toString(1)));
      urlParameters.add(new BasicNameValuePair("order", orderString));
      String limit=getResponse(urlParameters,"agent/deliver");
	  return Integer.parseInt(limit);
  }
  public void deliverOrder(int agentId,Order order)
  {
	  String orderString= gson.toJson(order) ;
	  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
      urlParameters.add(new BasicNameValuePair("agentId", Integer.toString(agentId)));
      urlParameters.add(new BasicNameValuePair("code", Integer.toString(2)));
      urlParameters.add(new BasicNameValuePair("order", orderString));
      getResponse(urlParameters,"agent/deliver");
  }
  public int getStatus(int userId,Order order)
  {
	  return 0;
  }
  public void rateOrder(int userId,Order order)
  {
	  String orderString= gson.toJson(order) ;
	  List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
      urlParameters.add(new BasicNameValuePair("userId", Integer.toString(userId)));
      urlParameters.add(new BasicNameValuePair("order", orderString));
      getResponse(urlParameters,"user/rate");
  }
  public String getResponse(List<NameValuePair> urlParameters,String context)
  {
	  String response=null;
	  String url = "http://localhost:8000/"+context;
     	post = new HttpPost(url);
  	   
  	 try {
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		response = httpclient.execute(post, responseHandler);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
  	 return response;
  }
}
