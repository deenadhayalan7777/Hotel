package hotel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel implements Serializable {

	private int hotelId;
	private String username;
	private String password;
	private String phone;
	private float rating;
	private boolean isOpen;
	private Map<Integer,Item> menu;
	private Map<Integer,Order> orders;
	private Discount discount;
	private Map<Integer,Order> currentOrders;
	
	
	public Hotel(int hotelId,String username, String password, String phone) {
		
		this.hotelId=hotelId;
		this.username = username;
		this.password = password;
		this.phone = phone;
		setRating(5);
		menu =new HashMap<Integer,Item>();
		orders=new HashMap<Integer,Order>();
		currentOrders=new HashMap<Integer,Order>();
		discount=new Discount();
		setOpen(true);
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
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
	public Map<Integer,Item> getMenu() {
		return menu;
	}
	
	public Map<Integer,Order> getOrders() {
		return orders;
	}
	
	public Discount getDiscount() {
		return discount;
	}
	public void setDiscount(Discount discount) {
		this.discount = discount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hotelId;
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
		Hotel other = (Hotel) obj;
		if (hotelId != other.hotelId)
			return false;
		return true;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public void calculateRating()
	{
		float totalRating=0;
		int numberOfRatings=0;
		for(Order order:orders.values())
		{
			if(order.getRating()!=0)
			{
				numberOfRatings++;
				totalRating+=order.getRating();
			}
		}
		rating=(totalRating/numberOfRatings);
	}
	public Map<Integer,Order> getCurrentOrders() {
		return currentOrders;
	}
	public void setCurrentOrders(Map<Integer,Order> currentOrders) {
		this.currentOrders = currentOrders;
	}
	
	
}
