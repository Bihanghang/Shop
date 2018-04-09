package com.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.PushMess;
import com.bean.User;
import com.dao.MessageDao;
import com.dao.UserDao;
@WebServlet("/loginServlet")
public class Login extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		HttpSession session = req.getSession();
		session.setAttribute("username", username);
		if (username.equals("客服")) {
			UserDao dao = new UserDao();
			List<User> list = new ArrayList<>();
			list = dao.GetAllUsers();
			List<String> allusers = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				allusers.add(list.get(i).getUser_name());
			}
			session.setAttribute("allusers", allusers);
			req.getRequestDispatcher("/adminclient.jsp").forward(req, resp);
		}else {
			
			MessageDao dao = new MessageDao();
			List<PushMess> list1 = new ArrayList<>();
			list1 = dao.GetUser(username);
			List<PushMess> list2 = new ArrayList<>();
			list2 = dao.GetTo_User(username);
			List<PushMess> User_to客服List = new ArrayList<>();
			User_to客服List.addAll(list1);
			User_to客服List.addAll(list2);
			Collections.sort(User_to客服List,new Comparator<PushMess>(){
	            public int compare(PushMess arg0, PushMess arg1) {
	                return arg0.getTo_date().compareTo(arg1.getTo_date());
	            }
	        });
			session.setAttribute("User_to客服List", User_to客服List);
			req.getRequestDispatcher("chatclient.jsp").forward(req, resp);
		}
	
	}
	
}
