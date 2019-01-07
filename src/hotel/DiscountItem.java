package hotel;

import java.io.Serializable;

public class DiscountItem implements Serializable{

private int itemId;
private int quanitityLimit;
private int discountPercentage;

public DiscountItem(int itemId, int quanitityLimit, int discountPercentage) {
	
	this.itemId = itemId;
	this.quanitityLimit = quanitityLimit;
	this.discountPercentage = discountPercentage;
}

public int getItemId() {
	return itemId;
}

public void setItemId(int itemId) {
	this.itemId = itemId;
}

public int getQuanitityLimit() {
	return quanitityLimit;
}

public void setQuanitityLimit(int quanitityLimit) {
	this.quanitityLimit = quanitityLimit;
}

public int getDiscountPercentage() {
	return discountPercentage;
}

public void setDiscountPercentage(int discountPercentage) {
	this.discountPercentage = discountPercentage;
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
	DiscountItem other = (DiscountItem) obj;
	if (itemId != other.itemId)
		return false;
	return true;
}

@Override
public String toString() {
	return  itemId + "\t" + quanitityLimit + "\t\t"
			+ discountPercentage+"\n" ;
}


}
