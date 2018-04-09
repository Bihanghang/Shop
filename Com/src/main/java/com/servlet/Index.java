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

import com.bean.Product;
import com.dao.ProductDao;

@WebServlet("/indexServlet")
public class Index extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductDao dao = new ProductDao();
		List<Product> products = new ArrayList<>();
		products = dao.AllProductsSearch();
		HttpSession session = req.getSession();
		session.setAttribute("products", products);
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
}
