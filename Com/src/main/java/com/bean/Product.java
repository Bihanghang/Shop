package com.bean;

public class Product {
	private int pro_id;
	private String pro_name;
	private String pro_classify;
	private String pro_suitperson;
	private String pro_material;
	private String pro_brand;
	private String pro_size;
	private String pro_color;
	private float pro_price;
	private float pro_discount;
	private String pro_describe;
	private String pro_photo;
	private String pro_describephoto;
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getPro_classify() {
		return pro_classify;
	}
	public void setPro_classify(String pro_classify) {
		this.pro_classify = pro_classify;
	}
	public String getPro_suitperson() {
		return pro_suitperson;
	}
	public void setPro_suitperson(String pro_suitperson) {
		this.pro_suitperson = pro_suitperson;
	}
	public String getPro_material() {
		return pro_material;
	}
	public void setPro_material(String pro_material) {
		this.pro_material = pro_material;
	}
	public String getPro_brand() {
		return pro_brand;
	}
	public void setPro_brand(String pro_brand) {
		this.pro_brand = pro_brand;
	}
	public String getPro_size() {
		return pro_size;
	}
	public void setPro_size(String pro_size) {
		this.pro_size = pro_size;
	}
	public String getPro_color() {
		return pro_color;
	}
	public void setPro_color(String pro_color) {
		this.pro_color = pro_color;
	}
	public float getPro_price() {
		return pro_price;
	}
	public void setPro_price(float pro_price) {
		this.pro_price = pro_price;
	}
	public float getPro_discount() {
		return pro_discount;
	}
	public void setPro_discount(float pro_discount) {
		this.pro_discount = pro_discount;
	}
	public String getPro_describe() {
		return pro_describe;
	}
	public void setPro_describe(String pro_describe) {
		this.pro_describe = pro_describe;
	}
	public String getPro_photo() {
		return pro_photo;
	}
	public void setPro_photo(String pro_photo) {
		this.pro_photo = pro_photo;
	}
	public String getPro_describephoto() {
		return pro_describephoto;
	}
	public void setPro_describephoto(String pro_describephoto) {
		this.pro_describephoto = pro_describephoto;
	}
	@Override
	public String toString() {
		return "Product [pro_id=" + pro_id + ", pro_name=" + pro_name + ", pro_classify=" + pro_classify
				+ ", pro_suitperson=" + pro_suitperson + ", pro_material=" + pro_material + ", pro_brand=" + pro_brand
				+ ", pro_size=" + pro_size + ", pro_color=" + pro_color + ", pro_price=" + pro_price + ", pro_discount="
				+ pro_discount + ", pro_describe=" + pro_describe + ", pro_photo=" + pro_photo + ", pro_describephoto="
				+ pro_describephoto + "]";
	}
	
}
