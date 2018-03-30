package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.bean.User;

public class Test {
	public static void main(String[] args) {
		UserDao dao = new UserDao();
		List<User> list = new ArrayList<>();
		list = dao.GetAllUsers();
		List<String> allusers = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			allusers.add(list.get(i).getUser_name());
		}
		System.out.println(allusers);
	}
}
