package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.alibaba.fastjson.JSON;
import com.bean.PushMess;
import com.bean.User;
import com.dao.CartDao;
import com.dao.MessageDao;
import com.dao.ProductDao;
import com.dao.UserDao;

@WebServlet("/clientServlet")
public class ClientServlet extends HttpServlet{

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
    	
    	
    	
        //返回编码格式
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json; charset=utf-8");
    	
    	//获取ajax参数并查询
    	HttpSession session = request.getSession();
    	String user_name = (String) session.getAttribute("user_name");
    	if (user_name != null) {
    		MessageDao dao = new MessageDao();
    		List<PushMess> list1 = new ArrayList<>();
    		list1 = dao.GetUser(user_name);
    		List<PushMess> list2 = new ArrayList<>();
    		list2 = dao.GetTo_User(user_name);
    		List<PushMess> User_to客服List = new ArrayList<>();
    		User_to客服List.addAll(list1);
    		User_to客服List.addAll(list2);
    		Collections.sort(User_to客服List,new Comparator<PushMess>(){
    			public int compare(PushMess arg0, PushMess arg1) {
    				return arg0.getTo_date().compareTo(arg1.getTo_date());
    			}
    		});
    		session.setAttribute("User_to客服List", User_to客服List);
    	}
    	String logined=JSON.toJSONString("logined");
    	String notlogin=JSON.toJSONString("notlogin");
    	PrintWriter out = null;
    	try {
    	    out = response.getWriter();
    	    if (user_name != null) {
    	    	out.write(logined);
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




