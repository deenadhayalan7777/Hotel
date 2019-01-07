package hotel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Agent implements Serializable {

	private int agentId;
	private String username;
	private String password;
	private String phone;
	private Map<Integer,Order> currentOrders;
	private Map<Integer,Order> myOrders;
	
	
	
	
	public Agent(int agentId, String username, String password, String phone) {
		
		this.agentId = agentId;
		this.username = username;
		this.password = password;
		this.phone = phone;
		currentOrders=new HashMap<Integer,Order>();
		myOrders=new HashMap<Integer,Order>();
	}
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Map<Integer, Order> getCurrentOrders() {
		return currentOrders;
	}
	public void setCurrentOrders(Map<Integer, Order> currentOrders) {
		this.currentOrders = currentOrders;
	}
	public Map<Integer, Order> getMyOrders() {
		return myOrders;
	}
	public void setMyOrders(Map<Integer, Order> myOrders) {
		this.myOrders = myOrders;
	}
	
	
	
}
