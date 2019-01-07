package hotel;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpServer;

public class HTTPServer {

	
	 public static void main(String[] args) throws Exception {
		   
		   System.out.println("Server started");
		   Logger log=Logger.getLogger("test");
		   log.log(Level.FINEST, "Server started");
		    
	        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 10);
	        
	        server.createContext("/user/login", new UserLoginHandler());
	        server.createContext("/user/signup", new UserSignUpHandler());
	        server.createContext("/user/myorder", new MyOrderHandler());
	        server.createContext("/user/rate", new RateOrderHandler());
	        
	       
	        server.createContext("/hotel/login", new HotelLoginHandler());
	        
	        server.createContext("/hotel/signup", new HotelSignUpHandler());
	        server.createContext("/hotel/hotellist", new HotelListHandler());
	        server.createContext("/hotel/menu", new ViewMenuHandler());
	        server.createContext("/hotel/additem", new AddMenuItemHandler());
	        server.createContext("/hotel/orders", new HotelOrderHandler());
	        server.createContext("/hotel/currentorder", new HotelCurrentOrderHandler());
	        server.createContext("/hotel/makeorder", new MakeOrderHandler());
	        server.createContext("/hotel/discount", new HotelDiscountHandler());
	        server.createContext("/hotel/getdetail", new HotelDetailHandler());
	        
	        
	        server.createContext("/agent/login", new AgentLoginHandler());
	        server.createContext("/agent/signup", new AgentSignUpHandler());
	        server.createContext("/agent/orders", new AgentOrderHandler());
	        server.createContext("/agent/deliver", new AgentDeliverHandler());
            
	        server.setExecutor(null); // creates a default executor
	        server.start();
	        
	        
	    }
	 
}
	
	

