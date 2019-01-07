package hotel;


import java.io.Serializable;
import java.util.List;

public class Hold implements Serializable{

	private int holdId;
    private List<ItemQuantity> itemsList;
    private int total;
    public static int count=0;
	
    public Hold( List<ItemQuantity> itemsList, int total) {
		count++;
		this.holdId = count;
		this.itemsList = itemsList;
		this.total = total;
	}

	public int getHoldId() {
		return holdId;
	}

	public void setHoldId(int holdId) {
		this.holdId = holdId;
	}

	public List<ItemQuantity> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<ItemQuantity> itemsList) {
		this.itemsList = itemsList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		
		
	}
    
}
