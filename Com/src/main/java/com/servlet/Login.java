package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
			req.getRequestDispatcher("adminclient.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("chatclient.jsp").forward(req, resp);
		}
	
	}
	
}
