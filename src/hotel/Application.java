package hotel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Application {
	
	
	private Map<Integer,Hotel> hotels;
	private Map<Integer,User> users;
	private Map<Integer,Agent> agents;
	
	private FileHandler fh;
	private int hotelCount;
	private int userCount;
	private int orderCount;
	private int agentCount;
	private static Application app=null;
	Service service;
	
	private Application()
	{
		 
		 hotels=new HashMap<Integer,Hotel>();
		 users=new HashMap<Integer,User>();
		 agents=new HashMap<Integer,Agent>();

		 orderCount=0;
		 fh=new FileHandler();
		hotels=(Map<Integer, Hotel>) fh.read("hotels.txt");
		users=(Map<Integer, User>) fh.read("users.txt");
		agents=(Map<Integer, Agent>) fh.read("agents.txt");
		
		orderCount=(int) fh.read("server.txt");
		 hotelCount=hotels.size()+1;
		 userCount=users.size()+1;
		 agentCount=agents.size()+1;
		 
		 service=Service.getInstance();
	}
	
	public static Application getInstance()
	{
		if(app==null)
			app=new Application();
		return app;
	}
	
	public int userLogin(String username,String password)
	{
		int userId=0;
		User user=null;
		 for(User u:users.values())
		 {
			 if(u.getUsername().equals(username))
			 {
				user=u; 
			 }
		 }
		 if(user!=null)
		 {
			 if(user.getPassword().equals(password))
				 userId= user.getUserId();
			 else
				 userId=-1;
		 }
		return userId;
	}
	public int userSignUp(String username,String password,String phone)
	{   
		User user;
		for(User u:users.values())
		 {
			 if(u.getUsername().equals(username))
			 {
				return -2;
			 }
		 }
		synchronized(this){
			user=new User(userCount,username,password,phone);
			userCount++;
		    }
		
		users.put(user.getUserId(), user);
		
		return user.getUserId();
	}
	public int agentLogin(String username,String password)
	{
		int agentId=0;
		Agent agent=null;
		 for(Agent u:agents.values())
		 {
			 if(u.getUsername().equals(username))
			 {
				agent=u; 
			 }
		 }
		 if(agent!=null)
		 {
			 if(agent.getPassword().equals(password))
				 agentId= agent.getAgentId();
			 else
				 agentId=-1;
		 }
		return agentId;
	}
	public int agentSignUp(String username,String password,String phone)
	{   
		Agent agent;
		for(Agent u:agents.values())
		 {
			 if(u.getUsername().equals(username))
			 {
				return -2;
			 }
		 }
		synchronized(this){
			agent=new Agent(agentCount,username,password,phone);
			agentCount++;
		    }
		
		agents.put(agent.getAgentId(), agent);
		
		return agent.getAgentId();
	}
	public int hotelLogin(String username,String password)
	{
		int hotelId=0;
		Hotel hotel=null;
		 for(Hotel u:hotels.values())
		 {
			 if(u.getUsername().equals(username))
			 {
				hotel=u; 
			 }
		 }
		 if(hotel!=null)
		 {
			 if(hotel.getPassword().equals(password))
				 hotelId= hotel.getHotelId();
			 else
				 hotelId=-1;
		 }
		return hotelId;
	}
	public int hotelSignUp(String username,String password,String phone)
	{
		Hotel hotel;
		for(Hotel u:hotels.values())
		 {
			 if(u.getUsername().equals(username))
			 {
				return -2;
			 }
		 }
		synchronized(this) {
		hotel=new Hotel(hotelCount,username,password,phone);
		hotelCount++;
		}
		hotels.put(hotel.getHotelId(), hotel);
		return hotel.getHotelId();
	}
	public Map<Integer,HotelDetail> getHotelList()
	{
		Map<Integer,HotelDetail> hotellist=new HashMap<Integer,HotelDetail>();
		for(Hotel hotel:hotels.values())
		{
			HotelDetail hdetail=new HotelDetail(hotel.getHotelId(),hotel.getUsername(),hotel.getPhone(),hotel.getRating(),hotel.isOpen());
			hotellist.put(hotel.getHotelId(), hdetail);
		}
		return hotellist;
		
	}
	public User getUser(int userId) {
		return users.get(userId);
		
	}
	public Hotel getHotel(int hotelId) {
		
	 return hotels.get(hotelId);
	}
	
	public Agent getAgent(int agentId)
	{
		if(agents.get(agentId)==null)
			System.out.println("agent is null in get");
		
		return agents.get(agentId);
	}
	
	public void saveInFile()
	{
		fh.write("hotels.txt", hotels);
		fh.write("users.txt", users);
		fh.write("agents.txt", agents);
		fh.write("server.txt", orderCount);
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	
	
	
	
}
