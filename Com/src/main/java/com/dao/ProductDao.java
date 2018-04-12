package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Product;
import com.jdbc.DBUtils;



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
				product.setPro_id(rs.getInt("pro_id"));
				product.setPro_brand(rs.getString("pro_brand"));
				product.setPro_classify((rs.getString("pro_classify")));
				product.setPro_color(rs.getString("pro_color"));
				product.setPro_describe(rs.getString("pro_describe"));
				product.setPro_describephoto(rs.getString("pro_describephoto"));
				product.setPro_name(rs.getString("pro_name"));
				product.setPro_suitperson((rs.getString("pro_suitperson")));
				product.setPro_material(rs.getString("pro_material"));
				product.setPro_size((rs.getString("pro_size")));
				product.setPro_price((rs.getInt("pro_price")));
				product.setPro_discount((rs.getInt("pro_discount")));
				product.setPro_photo((rs.getString("pro_photo")));
			}
			if (product.getPro_name() != null ) {
				return product;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Product ProductSearchByPro_id(Integer pro_id) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtils.getConnection();
			String sql = "select *from product where pro_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, pro_id);
			rs = ps.executeQuery();
			Product product = new Product();
			while ( rs.next() ){
				product.setPro_id(rs.getInt("pro_id"));
				product.setPro_brand(rs.getString("pro_brand"));
				product.setPro_classify((rs.getString("pro_classify")));
				product.setPro_color(rs.getString("pro_color"));
				product.setPro_describe(rs.getString("pro_describe"));
				product.setPro_describephoto(rs.getString("pro_describephoto"));
				product.setPro_name(rs.getString("pro_name"));
				product.setPro_suitperson((rs.getString("pro_suitperson")));
				product.setPro_material(rs.getString("pro_material"));
				product.setPro_size((rs.getString("pro_size")));
				product.setPro_price((rs.getInt("pro_price")));
				product.setPro_discount((rs.getInt("pro_discount")));
				product.setPro_photo((rs.getString("pro_photo")));
			}
			if (product.getPro_name() != null ) {
				return product;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
				product.setPro_id(rs.getInt("pro_id"));
				product.setPro_brand(rs.getString("pro_brand"));
				product.setPro_classify((rs.getString("pro_classify")));
				product.setPro_color(rs.getString("pro_color"));
				product.setPro_describe(rs.getString("pro_describe"));
				product.setPro_describephoto(rs.getString("pro_describephoto"));
				product.setPro_name(rs.getString("pro_name"));
				product.setPro_suitperson((rs.getString("pro_suitperson")));
				product.setPro_material(rs.getString("pro_material"));
				product.setPro_size((rs.getString("pro_size")));
				product.setPro_price((rs.getInt("pro_price")));
				product.setPro_discount((rs.getInt("pro_discount")));
				product.setPro_photo((rs.getString("pro_photo")));
				products.add(product);
			}
			if (products != null ) {
				return products;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Integer ProductPrice(int pro_id) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		Integer price = 0;
		try {
			con = DBUtils.getConnection();
			String sql = "select pro_price from product where pro_id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, pro_id);
			rs = ps.executeQuery();
			while ( rs.next() ){
				price = rs.getInt("pro_price");
			}
			return price;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public List<Product> ProductSearchByConditions(String Pro_name,List<String> Sizelist,List<Float> Discountlist,
			List<String> Catogorieslist,List<String> Colorlist){
		ResultSet rs = null;
		Connection con = null;
		List<Product> products = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			con = DBUtils.getConnection();
			String sql = "select *from product where 1=1 ";
			if (Pro_name != "" && Pro_name != null) {
				sql += "and pro_name like '%"+Pro_name+"%'";
			}
			
			if ( !Sizelist.isEmpty() ){
				for (int i = 0; i < Sizelist.size(); i++) {
					sql += "and pro_size = '"+Sizelist.get(i)+"'";
				}
			}
			if ( !Discountlist.isEmpty() ){
				for (int i = 0; i < Discountlist.size(); i++) {
					sql += "and pro_discount = '"+Discountlist.get(i)+"'";
				}
			}
			if ( !Catogorieslist.isEmpty() ){
				for (int i = 0; i < Catogorieslist.size(); i++) {
					sql += "and pro_brand = '"+Catogorieslist.get(i)+"'";
				}
			}
			if ( !Colorlist.isEmpty() ){
				for (int i = 0; i < Colorlist.size(); i++) {
					sql += "and pro_color = '"+Colorlist.get(i)+"'";
				}
			}
			System.out.println(sql);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while ( rs.next() ){
				Product product = new Product();
				product.setPro_id(rs.getInt("pro_id"));
				product.setPro_brand(rs.getString("pro_brand"));
				product.setPro_classify((rs.getString("pro_classify")));
				product.setPro_color(rs.getString("pro_color"));
				product.setPro_describe(rs.getString("pro_describe"));
				product.setPro_describephoto(rs.getString("pro_describephoto"));
				product.setPro_name(rs.getString("pro_name"));
				product.setPro_suitperson((rs.getString("pro_suitperson")));
				product.setPro_material(rs.getString("pro_material"));
				product.setPro_size((rs.getString("pro_size")));
				product.setPro_price((rs.getInt("pro_price")));
				product.setPro_discount((rs.getInt("pro_discount")));
				product.setPro_photo((rs.getString("pro_photo")));
				products.add(product);
			}
			if (products != null ) {
				return products;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}
}
