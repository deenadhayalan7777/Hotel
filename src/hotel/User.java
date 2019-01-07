package hotel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User implements Serializable{

	private int userId;
	private String username;
	private String password;
	private String phone;
	private Map<Integer,Order> myOrders;
	private Map<Integer,Order> currentOrders;
	
	
	
	public User(int userId, String username, String password, String phone) {
		
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.phone = phone;
		myOrders=new HashMap<Integer,Order>();
		currentOrders=new HashMap<Integer,Order>();
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public Map<Integer,Order> getMyOrders() {
		return myOrders;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId != other.userId)
			return false;
		return true;
	}
	public Map<Integer,Order> getCurrentOrders() {
		return currentOrders;
	}
	public void setCurrentOrders(Map<Integer,Order> currentOrders) {
		this.currentOrders = currentOrders;
	}
	
	
	
}
