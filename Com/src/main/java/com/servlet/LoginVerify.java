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
import com.bean.User;
import com.dao.CartDao;
import com.dao.ProductDao;
import com.dao.UserDao;


@WebServlet("/loginServlet.do")
public class LoginVerify extends HttpServlet{
	
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
    	String phone = request.getParameter("phone");
    	String password = request.getParameter("password");
    	System.out.println(phone);
    	System.out.println(password);
		User user = new User();
    	if (!phone.equals("客服") && !phone.equals("")) {
    		UserDao dao = new UserDao();
    		user = dao.UserSearch(phone);
		}
    	//返回Json格式的判断结果
    	String notexit = JSON.toJSONString("notexit");
    	String success=JSON.toJSONString("success");
    	String fail=JSON.toJSONString("fail");
    	String kefu=JSON.toJSONString("kefu");
    	PrintWriter out = null;
    	try {
    	    out = response.getWriter();
    	    if (phone.equals("客服")) {
    	    	if (password.equals("666")) {
    	    		out.write(kefu);
				}else {
					out.write(fail);
				}
			}else if (user == null) {
				out.write(notexit);
			}else {
				if (user.getUser_paswprd().equals(password)) {
					CartDao dao = new CartDao();
	    	    	List<Integer> cartitem = new ArrayList<>();
	    	    	cartitem = dao.GetItemid(phone);
	    	    	ProductDao dao2 = new ProductDao();
	    	    	Integer CartTotal = 0;
	    	    	for(Integer p:cartitem){
	    	    		CartTotal += dao2.ProductPrice(p);
	    	    	}
	    	    	session.setAttribute("CartTotal", CartTotal);
					session.setAttribute("user_phone", user.getUser_phone());
					session.setAttribute("user_name", user.getUser_name());
					
					out.write(success);
				} else {
					out.write(fail);
				}
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
