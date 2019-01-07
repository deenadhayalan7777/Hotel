package hotel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
  
	private int orderId;
	private int hotelId;
    private List<ItemQuantity> itemsList;
    private Date date;
    private int rating;
    private int total;
    private int discount;
    private String phoneNo;
    private int userId;
    private int agentId;
    private int status; 
	public Order(int orderId,List<ItemQuantity> itemsList, int total,String phoneNo,int discount,int hotelId,int userId) {
		
		this.itemsList = itemsList;
		this.total = total;
		this.setRating(0);
		this.orderId=orderId;
		this.phoneNo=phoneNo;
		this.setDiscount(discount);
		this.setHotelId(hotelId);
		this.userId=userId;
		setStatus(C.ACCEPTED);
		date=new Date();
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public List<ItemQuantity> getItemsList() {
		return itemsList;
	}
	public void setItems(List<ItemQuantity> itemsList) {
		this.itemsList = itemsList;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderId;
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
		Order other = (Order) obj;
		if (orderId != other.orderId)
			return false;
		return true;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
    
    
}
