package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bean.PushMess;
import com.jdbc.DBUtils;



public class MessageDao {
	public void PushMessage(PushMess pushMess) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtils.getConnection();
			String sql = "insert into pushmess (user,to_mess,to_date,to_user) values(?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, pushMess.getUser());
			ps.setString(2, pushMess.getTo_mess());
			ps.setString(3, pushMess.getTo_date());
			ps.setString(4, pushMess.getTo_user());
			ps.execute();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<PushMess> GetUser(String user) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		List<PushMess> pushMesses = new ArrayList<>();
		try {
			con = DBUtils.getConnection();
			String sql = "select *from pushmess where user=?";
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
				pushMesses.add(pushMess);
			}
			if (pushMesses != null) {
				return pushMesses;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<PushMess> GetTo_user(String to_user) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		List<PushMess> pushMesses = new ArrayList<>();
		try {
			con = DBUtils.getConnection();
			String sql = "select *from pushmess where to_user=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, to_user);
			rs = ps.executeQuery();
			while(rs.next()) {
				PushMess pushMess = new PushMess();
				pushMess.setTo_user(rs.getString("to_user"));
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String s = sdf.format(rs.getTimestamp("to_date"));
				pushMess.setTo_date(s);
				pushMess.setTo_mess(rs.getString("to_mess"));
				pushMess.setUser(rs.getString("user"));
				pushMesses.add(pushMess);
			}
			if (pushMesses != null) {
				return pushMesses;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
