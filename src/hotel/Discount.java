package hotel;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Discount implements Serializable{
private int totalPercentage;
private int timePercentage;
private int membershipPercentage;
private int baseTotal;
private int membershipPoint;
private int toTime;
private int fromTime;
private Map<Integer,DiscountItem> discountItems;

public Discount()  {
	
	this.totalPercentage = 0;
	this.timePercentage = 0;
	this.membershipPercentage = 0;
	this.baseTotal = 0;
	this.membershipPoint = 0;
	this.toTime = 0;
	this.fromTime = 0;
	this.discountItems = new HashMap<Integer,DiscountItem>();
}
public Discount(int totalPercentage, int timePercentage, int membershipPercentage, int baseTotal, int membershipPoint,
		int toTime, int fromTime, Map<Integer, DiscountItem> discountItems) {
	
	this.totalPercentage = totalPercentage;
	this.timePercentage = timePercentage;
	this.membershipPercentage = membershipPercentage;
	this.baseTotal = baseTotal;
	this.membershipPoint = membershipPoint;
	this.toTime = toTime;
	this.fromTime = fromTime;
	this.discountItems = discountItems;
}

public int getTotalPercentage() {
	return totalPercentage;
}
public void setTotalPercentage(int totalPercentage) {
	this.totalPercentage = totalPercentage;
}
public int getTimePercentage() {
	return timePercentage;
}
public void setTimePercentage(int timePercentage) {
	this.timePercentage = timePercentage;
}
public int getMembershipPercentage() {
	return membershipPercentage;
}
public void setMembershipPercentage(int membershipPercentage) {
	this.membershipPercentage = membershipPercentage;
}
public int getBaseTotal() {
	return baseTotal;
}
public void setBaseTotal(int baseTotal) {
	this.baseTotal = baseTotal;
}
public int getMembershipPoint() {
	return membershipPoint;
}
public void setMembershipPoint(int membershipPoint) {
	this.membershipPoint = membershipPoint;
}
public int getToTime() {
	return toTime;
}
public void setToTime(int toTime) {
	this.toTime = toTime;
}
public int getFromTime() {
	return fromTime;
}
public void setFromTime(int fromTime) {
	this.fromTime = fromTime;
}
public Map<Integer, DiscountItem> getDiscountItems() {
	return discountItems;
}
public void setDiscountItems(Map<Integer, DiscountItem> discountItems) {
	this.discountItems = discountItems;
}
public int applyItemDiscount(ItemQuantity iq)
{
     if(discountItems.containsKey(iq.getItemId()))
     {
    	DiscountItem di=discountItems.get(iq.getItemId());
    	if(iq.getQuantity()>=di.getQuanitityLimit())
    	return iq.getSubtotal()-((iq.getSubtotal()*di.getDiscountPercentage())/100);
     }
return iq.getSubtotal();
}
public Order applyDiscount(Order order)
{
	int total=applyTotalDiscount(order.getTotal());
	    total=applyTimeDiscount(order.getDate(),total);
	    order.setTotal(total);
	
	return order;
}

public int applyTotalDiscount(int total)
{ 
 if(total>=baseTotal)
	return total-((total*totalPercentage)/100);
  return total;
}

public int applyTimeDiscount(Date date,int total)
{
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(date);
	int hour = calendar.get(Calendar.HOUR_OF_DAY);
	if(hour>=fromTime && hour<=toTime)
		return total-((total*timePercentage)/100);
	return total;
	
}
public int applyMembershipDiscount(int total)
{
	
	return total-((total*membershipPercentage)/100);
 
}
@Override
public String toString() {
	return "Available Discounts \n1.For total greater than " + baseTotal + " discount " + totalPercentage + "%\n2. For early bird customers within " +fromTime+" to "+ toTime + " discount " 
+ timePercentage+ "%\n3.For members with "+ membershipPoint+ " points discount "+  membershipPercentage+"%"+"\n4.For following items discount will be applied as mentioned \nItemId\tItemQuantity\tDiscount% \n"+discountItems; 
	
}




}
