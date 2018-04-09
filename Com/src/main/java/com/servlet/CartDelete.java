package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.dao.CartDao;
import com.dao.ProductDao;


@WebServlet("/cartdelete")
public class CartDelete extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
        retData(request, response, "GET");  
    }  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
          
        retData(request, response, "POST");  
    }  
  
      
    /** 
     * 对请求提供返回数据 
     * @param request 
     * @param response 
     * @param method 
     * @return 
     * @throws IOException 
     */  
    private void retData(HttpServletRequest request, HttpServletResponse response,String method) throws IOException{  
    	
    	HttpSession session = request.getSession();
    	
        //返回编码格式
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json; charset=utf-8");
    	
    	String user_phone = (String) session.getAttribute("user_phone");    
    	String itemid = request.getParameter("itemid");
    	if (user_phone != null && itemid == null) {
    	CartDao dao = new CartDao();
    	dao.EmptyCart(user_phone); 
    	session.setAttribute("CartTotal", 0);
    	String success = JSON.toJSONString("success");
    	PrintWriter out = null;
    	try {
    	    out = response.getWriter();
    	    out.write(success);
    	} catch (IOException e) {
    	    e.printStackTrace();
    	} finally {
    	    if (out != null) {
    	        out.close();
    	    }
    	}
    	}else if (user_phone != null && itemid != null){
    		CartDao dao = new CartDao();
    		int num = dao.GetNum(user_phone, Integer.parseInt(itemid));
    		ProductDao dao2 = new ProductDao();
    		int price = dao2.ProductPrice(Integer.parseInt(itemid));
    		int TotalMonney = (int) session.getAttribute("CartTotal");
    		session.setAttribute("CartTotal", (TotalMonney-num*price));
    		dao.DeleteSingle(user_phone, Integer.parseInt(itemid));
    		String success = JSON.toJSONString("success");
    		PrintWriter out = null;
        	try {
        	    out = response.getWriter();
        	    out.write(success);
        	} catch (IOException e) {
        	    e.printStackTrace();
        	} finally {
        	    if (out != null) {
        	        out.close();
        	    }
        	}
    	}
    }  
}