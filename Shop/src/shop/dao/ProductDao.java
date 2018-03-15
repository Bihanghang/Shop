package shop.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import shop.bean.Product;
import shop.bean.User;
import shop.jdbc.DBUtils;

public class ProductDao {
	public Product ProductSearch(String name) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtils.getConnection();
			String sql = "select *from product where product_name = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			Product product = new Product();
			while ( rs.next() ){
				product.setItemid(rs.getInt("itemid"));
				product.setDelivery_details(rs.getString("delivery_details"));
				product.setItem(rs.getString("item"));
				product.setProduct_name(rs.getString("product_name"));
				product.setUnit_price(rs.getString("unit_price"));
			}
			if (product.getProduct_name() != null ) {
				return product;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Product> AllProductsSearch() {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		List<Product> products = new ArrayList<>();
		try {
			con = DBUtils.getConnection();
			String sql = "select *from product";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while ( rs.next() ){
				Product product = new Product();
				product.setItemid(rs.getInt("itemid"));
				product.setDelivery_details(rs.getString("delivery_details"));
				product.setItem(rs.getString("item"));
				product.setProduct_name(rs.getString("product_name"));
				product.setUnit_price(rs.getString("unit_price"));
				products.add(product);
			}
			if (products != null ) {
				return products;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
