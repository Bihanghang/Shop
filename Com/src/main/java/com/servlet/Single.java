package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.Product;
import com.dao.CartDao;
import com.dao.ProductDao;


@WebServlet("/singleServlet")
public class Single extends HttpServlet{
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
    	
    	//获取ajax参数
    	String pro_id = request.getParameter("pro_id");
    	System.out.println(pro_id);
    	
    	ProductDao dao = new ProductDao();
    	Product product = new Product();
    	product = dao.ProductSearchByPro_id(Integer.parseInt(pro_id));
    	String PhotoDescribe = product.getPro_describephoto();
    	session.setAttribute("PhotoDescribe0", PhotoDescribe.split(",")[0]);
    	session.setAttribute("PhotoDescribe1", PhotoDescribe.split(",")[1]);
    	session.setAttribute("PhotoDescribe2", PhotoDescribe.split(",")[2]);
    	session.setAttribute("Single", product);
    	
    	System.out.println(product);
    	String success=JSON.toJSONString("success");
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
