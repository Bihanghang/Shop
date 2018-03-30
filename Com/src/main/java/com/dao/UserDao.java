package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bean.User;
import com.jdbc.DBUtils;




public class UserDao {
	
	public User UserSearch(String name) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtils.getConnection();
			String sql = "select *from user where user_name = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			User user = new User();
			while ( rs.next() ){
				user.setUser_addr(rs.getString("user_addr"));
				user.setUser_birth(rs.getString("user_birth"));
				user.setUser_jifen(rs.getInt("user_jifen"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_paswprd(rs.getString("user_password"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_sex(rs.getString("user_sex"));
				user.setUser_touxiang(rs.getString("user_touxiang"));
			}
			if (user.getUser_name() != null) {
				return user;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<User> GetAllUsers(){
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		List<User> list = new ArrayList<>();
		try {
			con = DBUtils.getConnection();
			String sql = "select *from user";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while ( rs.next() ){
				User user = new User();
				user.setUser_addr(rs.getString("user_addr"));
				user.setUser_birth(rs.getString("user_birth"));
				user.setUser_jifen(rs.getInt("user_jifen"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_paswprd(rs.getString("user_password"));
				user.setUser_phone(rs.getString("user_phone"));
				user.setUser_sex(rs.getString("user_sex"));
				user.setUser_touxiang(rs.getString("user_touxiang"));
				list.add(user);
			}
			if (list != null) {
				return list;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
