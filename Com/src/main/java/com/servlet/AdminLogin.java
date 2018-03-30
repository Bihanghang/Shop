package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;
import com.dao.UserDao;
@WebServlet("/adminlogin")
public class AdminLogin extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDao dao = new UserDao();
		List<User> list = new ArrayList<>();
		list = dao.GetAllUsers();
		List<String> allusers = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			allusers.add(list.get(i).getUser_name());
		}
		HttpSession session = req.getSession();
		session.setAttribute("allusers", allusers);
		req.getRequestDispatcher("/adminclient.jsp").forward(req, resp);
	}
	
}
