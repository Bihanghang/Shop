package shop.bean;

public class Cart {
	private String user_phone;
	private int itemid;
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	@Override
	public String toString() {
		return "Cart [user_phone=" + user_phone + ", itemid=" + itemid + "]";
	}
	
}
