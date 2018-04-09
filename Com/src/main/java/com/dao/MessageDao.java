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



public class MessageDao {
	public void PushMessage(PushMess pushMess) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtils.getConnection();
			String sql = "insert into message (user,to_mess,to_date,to_user,isself,messtype) values(?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, pushMess.getUser());
			ps.setString(2, pushMess.getTo_mess());
			ps.setString(3, pushMess.getTo_date());
			ps.setString(4, pushMess.getTo_user());
			ps.setInt(5, pushMess.isSelf()? 1:0);
			ps.setString(6, pushMess.getMesstype());
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
	public List<PushMess> GetUser(String user) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		List<PushMess> pushMesses = new ArrayList<>();
		try {
			con = DBUtils.getConnection();
			String sql = "select *from message where user=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, user);
			rs = ps.executeQuery();
			while(rs.next()) {
				PushMess pushMess = new PushMess();
				pushMess.setTo_user(rs.getString("to_user"));
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String s = sdf.format(rs.getTimestamp("to_date"));
				pushMess.setTo_date(s);
				pushMess.setTo_mess(rs.getString("to_mess"));
				pushMess.setUser(rs.getString("user"));
				pushMess.setSelf(rs.getInt("isSelf") == 1 ? true:false);
				pushMesses.add(pushMess);
			}
			if (pushMesses != null) {
				return pushMesses;
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
	
	public List<PushMess> GetUserKefu(String user,String to_user,String messtype) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		List<PushMess> pushMesses = new ArrayList<>();
		try {
			con = DBUtils.getConnection();
			String sql = "select *from message where user=? and to_user=? and messtype=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, to_user);
			ps.setString(3, messtype);
			rs = ps.executeQuery();
			while(rs.next()) {
				PushMess pushMess = new PushMess();
				pushMess.setTo_user(rs.getString("to_user"));
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String s = sdf.format(rs.getTimestamp("to_date"));
				pushMess.setTo_date(s);
				pushMess.setTo_mess(rs.getString("to_mess"));
				pushMess.setUser(rs.getString("user"));
				pushMess.setSelf(rs.getInt("isSelf") == 1 ? true:false);
				pushMesses.add(pushMess);
			}
			if (pushMesses != null) {
				return pushMesses;
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
	
	public List<PushMess> GetTo_User(String user) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		List<PushMess> pushMesses = new ArrayList<>();
		try {
			con = DBUtils.getConnection();
			String sql = "select *from message where to_user=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, user);
			rs = ps.executeQuery();
			while(rs.next()) {
				PushMess pushMess = new PushMess();
				pushMess.setTo_user(rs.getString("to_user"));
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String s = sdf.format(rs.getTimestamp("to_date"));
				pushMess.setTo_date(s);
				pushMess.setTo_mess(rs.getString("to_mess"));
				pushMess.setUser(rs.getString("user"));
				pushMess.setSelf(rs.getInt("isSelf") == 1 ? true:false);
				pushMesses.add(pushMess);
			}
			if (pushMesses != null) {
				return pushMesses;
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
