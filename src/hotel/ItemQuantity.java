package hotel;

import java.io.Serializable;

public class ItemQuantity implements Serializable {
	
	private int itemId;
	private int quantity;
	private int subtotal;
	private String name;
	private int price;
	
	public ItemQuantity(int itemId, int quantity, int subtotal,String name,int price) {
		
		this.itemId = itemId;
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.name=name;
		this.price=price;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
