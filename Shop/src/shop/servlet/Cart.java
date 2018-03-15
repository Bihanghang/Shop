package shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import shop.bean.User;
import shop.dao.UserDao;
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
    	String product_id = request.getParameter("product_id");
    	String user_phone = (String) session.getAttribute("user_phone");
    	
		//返回Json格式的判断结果
    	String logined = JSON.toJSONString("logined");
    	String notlogin=JSON.toJSONString("notlogin");
    	PrintWriter out = null;
    	try {
    	    out = response.getWriter();
    	    if (user_phone != null) {
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
