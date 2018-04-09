package com.bean;

public class CartPlusNum {
	private Product product;
	private int num;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "CartPlusNum [product=" + product + ", num=" + num + "]";
	}
	
}
