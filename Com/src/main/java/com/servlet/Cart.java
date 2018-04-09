package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.dao.CartDao;
import com.dao.ProductDao;


@WebServlet("/cartServlet")
public class Cart extends HttpServlet{
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
    	
    	//获取ajax参数并查询
    	String itemId = request.getParameter("itemId");
    	String yString = request.getParameter("Unit_Price");
    	String user_phone = (String) session.getAttribute("user_phone");    
		//返回Json格式的判断结果
    	String notlogin=JSON.toJSONString("notlogin");
    	PrintWriter out = null;
    	try {
    	    out = response.getWriter();
    	    if (user_phone != null) {
    	    	CartDao dao = new CartDao();
    	    	//获取此商品的数量如果没有就加入如果存在就加一
    	    	int itemnum = dao.GetNum(user_phone, Integer.parseInt(itemId));
    	    	if (itemnum == 0) {
    	    		dao.Push(user_phone, Integer.valueOf(itemId));
				}else {
					dao.IncrementOne(user_phone,Integer.parseInt(itemId),itemnum);
				}
    	    	//获取用户所有商品种类
    	    	List<Integer> cartitem = new ArrayList<>();
    	    	cartitem = dao.GetItemid(user_phone);
    	    	
    	    	
    	    	ProductDao dao2 = new ProductDao();
    	    	
    	    	Integer CartTotal = 0;
    	    	for(Integer p:cartitem){
    	    		CartTotal += (dao2.ProductPrice(p)*dao.GetNum(user_phone, p));
    	    	}
    	    	session.setAttribute("CartTotal", CartTotal);
    	    	String sum = JSON.toJSONString(CartTotal);
				out.write(sum);
			}else {
				out.write(notlogin);
			}
    	} catch (IOException e) {
    	    e.printStackTrace();
    	} finally {
    	    if (out != null) {
    	        out.close();
    	    }
    	}
    }  
}
