package com.factory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCFactory {
	private static String DRIVER;
	private static String URL;
	private static String USER;
	private static String PASSWORD;
	
	static{
		Properties pro=new Properties();
		InputStream in=JDBCFactory.class.getResourceAsStream("jdbc.properties");
		try {
			pro.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DRIVER=pro.getProperty("driver");
		URL=pro.getProperty("url");
		USER=pro.getProperty("user");
		PASSWORD=pro.getProperty("password");
	}
	public static Connection getCon(){
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con=null;
		try {
			con=DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeAll(Connection conn,Statement st,ResultSet rs){
		//数据库资源的关闭
		try {
			if(rs!=null)
				rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if(st!=null)
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
