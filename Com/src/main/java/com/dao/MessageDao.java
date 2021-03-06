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
			String sql = "insert into message (user,to_mess,to_date,to_user,isself,messtype,linetype) values(?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, pushMess.getUser());
			ps.setString(2, pushMess.getTo_mess());
			ps.setString(3, pushMess.getTo_date());
			ps.setString(4, pushMess.getTo_user());
			ps.setInt(5, pushMess.isSelf()? 1:0);
			ps.setString(6, pushMess.getMesstype());
			ps.setString(7, pushMess.getLinetype());
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
				pushMess.setLinetype(rs.getString("linetype"));
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
				pushMess.setLinetype(rs.getString("linetype"));
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
				pushMess.setLinetype(rs.getString("linetype"));
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
	
	//查找所有向下线客服发送的用户
	public List<String> UsersToOfflineKefu(){
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		List<String> list = new ArrayList<>();
		try {
			con = DBUtils.getConnection();
			String sql = "select user from message where linetype='offline' and to_user='客服'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("user"));
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
	
	//查找向下线客服发送信息的条数
		public int OfflineNumbersToKefu(String name){
			ResultSet rs = null;
			Connection con = null;
			PreparedStatement ps = null;
			int num = 0;
			try {
				con = DBUtils.getConnection();
				String sql = "select count(*) from message where linetype='offline' and user=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, name);
				rs = ps.executeQuery();
				while(rs.next()) {
					num = rs.getInt("count(*)");
				}
				if (num != 0) {
					return num;
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
			return 0;
		}
	
		//查找向下线客服发送信息的总条数
				public int AllOfflineNumbersToKefu(){
					ResultSet rs = null;
					Connection con = null;
					PreparedStatement ps = null;
					int num = 0;
					try {
						con = DBUtils.getConnection();
						String sql = "select count(*) from message where linetype='offline' and to_user='客服'";
						ps = con.prepareStatement(sql);
						rs = ps.executeQuery();
						while(rs.next()) {
							num = rs.getInt("count(*)");
						}
						if (num != 0) {
							return num;
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
					return 0;
				}
			
	//查找向下线客户发送信息的条数
	public int OfflineNumbers(String name){
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		int num = 0;
		try {
			con = DBUtils.getConnection();
			String sql = "select count(*) from message where linetype='offline' and to_user=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()) {
				num = rs.getInt("count(*)");
			}
			if (num != 0) {
				return num;
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
		return 0;
	}
	
	//修改客服向下线用户发送消息种类为online
	public void ChangeOffToOn(String to_user){
		Connection con = null;
		PreparedStatement ps = null;
		int num = 0;
		try {
			con = DBUtils.getConnection();
			String sql = "update message set linetype='online' where to_user=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, to_user);
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
	
	//修改下线用户向客服发送消息种类为online
		public void ChangeOffToOnKefu(String user){
			Connection con = null;
			PreparedStatement ps = null;
			int num = 0;
			try {
				con = DBUtils.getConnection();
				String sql = "update message set linetype='online' where user=? ";
				ps = con.prepareStatement(sql);
				ps.setString(1, user);
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
