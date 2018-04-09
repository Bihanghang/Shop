package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bean.PushMess;
import com.jdbc.DBUtils;

public class CartDao {
	public void Push(String phone,int itemId) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtils.getConnection();
			String sql = "insert into cart (user_phone,itemid) values(?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, phone);
			ps.setInt(2, itemId);
			ps.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Integer> GetItemid(String phone) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		List<Integer> list = new ArrayList<>();
		try {
			con = DBUtils.getConnection();
			String sql = "select itemid from cart where user_phone=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, phone);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt("itemid"));
			}
			if (list != null) {
				return list;
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
	
	
	
	public int GetNum(String phone,int itemid) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		int itemnum = 0;
		try {
			con = DBUtils.getConnection();
			String sql = "select itemnum from cart where user_phone=? and itemid=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, phone);
			ps.setInt(2, itemid);
			rs = ps.executeQuery();
			while(rs.next()) {
				itemnum = rs.getInt("itemnum");
			}
			return itemnum;
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
		return itemnum;
	}
	
	public void IncrementOne(String phone,int itemid,int OldNum){
		int NowNum = OldNum + 1;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtils.getConnection();
			String sql = "UPDATE cart SET itemnum= ? WHERE user_phone=? and itemid=?;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, NowNum);
			ps.setString(2, phone);
			ps.setInt(3, itemid);
			ps.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void DecreaseOne(String phone,int itemid,int OldNum){
		int NowNum = OldNum - 1;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtils.getConnection();
			String sql = "UPDATE cart SET itemnum= ? WHERE user_phone=? and itemid=?;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, NowNum);
			ps.setString(2, phone);
			ps.setInt(3, itemid);
			ps.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void EmptyCart(String phone){
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtils.getConnection();
			String sql = "delete from cart where user_phone=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, phone);
			ps.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void DeleteSingle(String phone,int itemid) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtils.getConnection();
			String sql = "delete from cart where user_phone=? and itemid=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, phone);
			ps.setInt(2, itemid);
			ps.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
