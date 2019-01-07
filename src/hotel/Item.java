package hotel;

import java.io.Serializable;

public class Item implements Serializable{
	
private int itemId;
private String name;
private int price;


public Item(int itemId,String name, int price) {
	
	this.itemId=itemId;
	this.name = name;
	this.price = price;
	
}
public int getItemId() {
	return itemId;
}
public void setItemId(int itemId) {
	this.itemId = itemId;
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
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + itemId;
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
	Item other = (Item) obj;
	if (itemId != other.itemId)
		return false;
	return true;
}


}
