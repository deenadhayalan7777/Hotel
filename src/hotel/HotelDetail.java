package hotel;

public class HotelDetail {

	private int hotelId;
	private String username;
    private String phone;
	public int getHotelId() {
		return hotelId;
	}
	public HotelDetail(int hotelId, String username, String phone, float rating, boolean isOpen) {
		
		this.hotelId = hotelId;
		this.username = username;
		this.phone = phone;
		this.rating = rating;
		this.isOpen = isOpen;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	private float rating;
	private boolean isOpen;
	
}
