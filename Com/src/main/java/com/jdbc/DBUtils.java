package com.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtils {
	public static Connection getConnection() throws IOException
	{
		Connection con=null;
		String url,user,password,driverClass;
		
		url="jdbc:mysql://localhost:3306/shopdata";
		user="root";
		password="721214wan..";
		driverClass="com.mysql.jdbc.Driver";
		try{
			Class.forName(driverClass);
			con=DriverManager.getConnection(url,user,password);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
}
