package com.bean;

import java.util.Date;

public class PushMess {
	private String user;
	private String to_mess;
	private String to_date;
	private String to_user;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTo_mess() {
		return to_mess;
	}
	public void setTo_mess(String to_mess) {
		this.to_mess = to_mess;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	public String getTo_user() {
		return to_user;
	}
	public void setTo_user(String to_user) {
		this.to_user = to_user;
	}
	@Override
	public String toString() {
		return "PushMess [user=" + user + ", to_mess=" + to_mess + ", to_date=" + to_date + ", to_user=" + to_user
				+ "]";
	}
	
	
}
