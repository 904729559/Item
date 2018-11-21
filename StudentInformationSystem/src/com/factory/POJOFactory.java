package com.factory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class POJOFactory {
	private static Connection con;
	private static Statement sta;
	private static ResultSet rs;
	
	public static int getPojo(String tabName,String packagePath){
		tabName=tabName.toUpperCase();
		int n=0;
		String head="";
		String body="";
		String total="";
		
		head="package "+packagePath+";\r\n\r\n";		
		
		body="public class "+changeNameType(tabName)+"{\r\n";
		
		try {
			con=JDBCFactory.getCon();
			con.setAutoCommit(false);
			sta=con.createStatement();
			String sql="select * from USER_TAB_COLS where TABLE_NAME='"+tabName+"'";
			System.out.println(sql);
			rs=sta.executeQuery(sql);
			boolean flag=true;
			while(rs.next()){
				String javaType=getJavaType(rs.getString("DATA_TYPE"));
				if("Date".equals(javaType)&&flag){
					head+="import java.util.Date;\r\n\t\n";
					flag=false;
				}
				String javaCol=rs.getString("COLUMN_NAME").toLowerCase();
				body+="\tprivate "+javaType+" "+javaCol+";\r\n";
				n++;
			}
			body+="}";
			total=head+body;
			File f=new File("src/"+packagePath.replace(".", "/")+"/"+changeNameType(tabName)+".java");
			try {
				f.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				PrintWriter pw=new PrintWriter(new FileWriter(f));
				pw.write(total);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			JDBCFactory.closeAll(con, sta, rs);
		}
		
		return n;
	}
	private static String changeNameType(String oldName){
		String newName=null;
		newName=(oldName.charAt(0)+"").toUpperCase()+oldName.substring(1).toLowerCase();
		return newName;
	}
	private static String getJavaType(String databaseType){
		String javaType=null;
		switch(databaseType){
		case "VARCHAR2":
		case "CHAR":
		case "NVARCHAR2":
		case "LONG":
			return "String";
		case "NUMBER":
			return "int";
		case "DATE":
			return "Date";
		}
		return javaType;
	}
	
//≤‚ ‘∑Ω∑®	£∫
	public static void main(String[] args) {
		
		int n1=POJOFactory.getPojo("userinformation", "com.pojo");
		int n2=POJOFactory.getPojo("userkey", "com.pojo");
		int n3=POJOFactory.getPojo("subjectinformation", "com.pojo");
	}
}
