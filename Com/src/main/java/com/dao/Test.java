package com.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.bean.PushMess;
import com.bean.User;

public class Test {
	public static void main(String[] args) {
		UserDao dao = new UserDao();
		System.out.println(dao.GetAllUsersName());
	}
}
