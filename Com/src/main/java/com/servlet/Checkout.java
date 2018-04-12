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

import com.bean.CartPlusNum;
import com.bean.Product;
import com.dao.CartDao;
import com.dao.ProductDao;

@WebServlet("/checkoutServlet")
public class Checkout extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		CartDao dao = new CartDao();
		String phone = (String) session.getAttribute("user_phone");
		List<CartPlusNum> cartplusnum = new ArrayList<>();
		if (phone != null) {
			ProductDao dao2 = new ProductDao();
			List<Integer> cartId = new ArrayList<>();
			cartId = dao.GetItemid(phone);
			for(Integer i:cartId){
				CartPlusNum cartPlusNum = new CartPlusNum();
				cartPlusNum.setProduct(dao2.ProductSearchByPro_id(i));
				cartPlusNum.setNum(dao.GetNum(phone, i));
				cartplusnum.add(cartPlusNum);
			}
		}
		session.setAttribute("cartplusnum", cartplusnum);
		req.getRequestDispatcher("checkout.jsp").forward(req, resp);
	}
	
}
