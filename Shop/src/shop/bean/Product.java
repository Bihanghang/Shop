package shop.bean;

public class Product {
	private int itemid;
	private int discount;
	
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	private String item;
	private String product_name;
	private String unit_price;
	private String delivery_details;
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}
	public String getDelivery_details() {
		return delivery_details;
	}
	public void setDelivery_details(String delivery_details) {
		this.delivery_details = delivery_details;
	}
	@Override
	public String toString() {
		return "Product [itemid=" + itemid + ", discount=" + discount + ", item=" + item + ", product_name="
				+ product_name + ", unit_price=" + unit_price + ", delivery_details=" + delivery_details + "]";
	}
	
	
	
}
