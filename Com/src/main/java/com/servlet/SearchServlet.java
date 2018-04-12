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


@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet{
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
    	String Pro_name = (String) JSON.parse(request.getParameter("Pro_name"));
    	System.out.println(Pro_name);
    	String Size = request.getParameter("Size");
    	List<String> Sizelist = new ArrayList<>();
    	for (int i = 0; i < JSON.parseArray(Size).size(); i++) {
    		String string = JSON.parseArray(Size).getString(i);
    		string = string.replaceAll("Size", "");
			Sizelist.add(string);
		}
    	
    	String Discount = request.getParameter("Discount");
    	List<Float> Discountlist = new ArrayList<>();
    	for (int i = 0; i < JSON.parseArray(Discount).size(); i++) {
    		String string = JSON.parseArray(Discount).getString(i);
    		string = string.replaceAll("Discount", "");
    		Discountlist.add(Float.valueOf(string));
		}
    	
    	String Catogories = request.getParameter("Catogories");
    	List<String> Catogorieslist = new ArrayList<>();
    	for (int i = 0; i < JSON.parseArray(Catogories).size(); i++) {
    		String string = JSON.parseArray(Catogories).getString(i);
    		string = string.replaceAll("Catogories", "");
    		Catogorieslist.add(string);
		}
    	
    	String Color = request.getParameter("Color");
    	List<String> Colorlist = new ArrayList<>();
    	for (int i = 0; i < JSON.parseArray(Color).size(); i++) {
    		String string = JSON.parseArray(Color).getString(i);
    		string = string.replaceAll("Color", "");
    		Colorlist.add(string);
		}
    	System.out.println(Catogorieslist);
    	System.out.println(Discountlist);
    	System.out.println(Sizelist);
    	System.out.println(Colorlist);
    	
    	ProductDao dao = new ProductDao();
    	List<Product> list = new ArrayList<>();
    	String NewPro_name = "";
    	if (!"请输入商品名".equals(Pro_name)) {
    		NewPro_name = Pro_name;
		}
    	list = dao.ProductSearchByConditions(NewPro_name,Sizelist, Discountlist, Catogorieslist, Colorlist);
    	System.out.println(list);
    	session.setAttribute("SearchResult", list);
    	
    	
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
