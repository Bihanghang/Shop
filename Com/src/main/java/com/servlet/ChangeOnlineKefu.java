package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.dao.MessageDao;
import com.dao.ProductDao;
import com.dao.UserDao;

import net.sf.json.JSONObject;


@WebServlet("/changeonlinekefu")
public class ChangeOnlineKefu extends HttpServlet{
	
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
    	
    	
    	//查询客服向下线客户发送信息的条数
    	
    	String user = request.getParameter("user");
    	System.out.println("suree"+user);
    	MessageDao dao = new MessageDao();
    	dao.ChangeOffToOnKefu(user);
    	JSONObject JSONObj = JSONObject.fromObject("{}");
    	
    	int num = dao.AllOfflineNumbersToKefu();
    	JSONObj.put("num", num);
    	JSONObj.put("user", user);
    	
    	PrintWriter out = null;
    	try {
    	    out = response.getWriter();
    	    out.write(JSONObj.toString());
    	    
    	} catch (IOException e) {
    	    e.printStackTrace();
    	} finally {
    	    if (out != null) {
    	        out.close();
    	    }
    	}
    }  
}
