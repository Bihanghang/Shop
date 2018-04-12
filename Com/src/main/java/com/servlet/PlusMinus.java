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
import com.bean.CartPlusNum;
import com.dao.CartDao;
import com.dao.ProductDao;


@WebServlet("/plusminus")
public class PlusMinus extends HttpServlet{
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
    	String type = request.getParameter("type");
    	List<CartPlusNum> cartplusnum = new ArrayList<>();
    	if (user_phone != null && "plustype".equals(type)){
    		CartDao dao = new CartDao();
    		int num = dao.GetNum(user_phone, Integer.parseInt(itemid));
    		dao.IncrementOne(user_phone, Integer.parseInt(itemid), num);
    		
    		ProductDao dao2 = new ProductDao();
			List<Integer> cartId = new ArrayList<>();
			cartId = dao.GetItemid(user_phone);
			for(Integer i:cartId){
				CartPlusNum cartPlusNum = new CartPlusNum();
				cartPlusNum.setProduct(dao2.ProductSearchByPro_id(i));
				cartPlusNum.setNum(dao.GetNum(user_phone, i));
				cartplusnum.add(cartPlusNum);
			}
			session.setAttribute("cartplusnum", cartplusnum);
			
			List<Integer> cartitem = new ArrayList<>();
	    	cartitem = dao.GetItemid(user_phone);
	    	Integer CartTotal = 0;
	    	for(Integer p:cartitem){
	    		CartTotal += (dao2.ProductPrice(p)*dao.GetNum(user_phone, p));
	    	}
	    	session.setAttribute("CartTotal", CartTotal);
	    	
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
    	} else if (user_phone != null && "minustype".equals(type)) {
    		CartDao dao = new CartDao();
    		int num = dao.GetNum(user_phone, Integer.parseInt(itemid));
    		if (num > 1) {
    			dao.DecreaseOne(user_phone, Integer.parseInt(itemid), num);
        		
        		ProductDao dao2 = new ProductDao();
    			List<Integer> cartId = new ArrayList<>();
    			cartId = dao.GetItemid(user_phone);
    			for(Integer i:cartId){
    				CartPlusNum cartPlusNum = new CartPlusNum();
    				cartPlusNum.setProduct(dao2.ProductSearchByPro_id(i));
    				cartPlusNum.setNum(dao.GetNum(user_phone, i));
    				cartplusnum.add(cartPlusNum);
    			}
    			session.setAttribute("cartplusnum", cartplusnum);
    			
    			List<Integer> cartitem = new ArrayList<>();
    	    	cartitem = dao.GetItemid(user_phone);
    	    	Integer CartTotal = 0;
    	    	for(Integer p:cartitem){
    	    		CartTotal += (dao2.ProductPrice(p)*dao.GetNum(user_phone, p));
    	    	}
    	    	session.setAttribute("CartTotal", CartTotal);
    	    	
    	    	
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
}