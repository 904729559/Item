package com.factory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pojo.Subjectinformation;
import com.pojo.Userinformation;
import com.pojo.Userkey;



public class DAOFactory {
	
	public static void getDAO(Object obj,String packagePath){
		String total="";
		String head="";
		String body0="";
		String body="";
		
		head="package "+packagePath+";\r\n\r\n";
		head+="import java.sql.*;\r\n";
		head+="import java.util.*;\r\n";
		head+="import com.factory.JDBCFactory;\r\n";
		head+="import "+obj.getClass().getName()+";\r\n\r\n";
		body0="public class D"+obj.getClass().getSimpleName()+"{\r\n";
		body0+="\tConnection con=null;\r\n";
		body0+="\tStatement sta=null;\r\n";
		body0+="\tPreparedStatement ps=null;\r\n";
		body0+="\tResultSet rs=null;\r\n\r\n";
		body=getBody1(obj)+"\r\n";
		body+=getBody2(obj)+"\r\n";
		body+=getBody3(obj)+"\r\n";
		body+=getBody4(obj)+"\r\n}";
		total=head+body0+body;
		
//输出		
		File f=new File("src/"+packagePath.replace(".", "/")+"/D"+obj.getClass().getSimpleName()+".java");
		try {
			f.createNewFile();
			PrintWriter pw=new PrintWriter(new FileWriter(f));
			pw.write(total);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	

	
	public static String getBody1(Object obj){
		Class cla=obj.getClass();//得到类
		Field fs[]=cla.getDeclaredFields();//得到所有属性
		String seq="";//序列名
		String fie="";//主键字段
		String zd="";//非主键字段
		String wh="";//？
		for (int i = 0; i < fs.length; i++) {
			Onlyid id=fs[i].getAnnotation(Onlyid.class);			
			if(id!=null){
				seq=id.seqName();
				fie=fs[i].getName();
				continue;
			}
			zd+=","+fs[i].getName();
			wh+=",?";			
		}		
		String method1="\tpublic int insertObject("+cla.getSimpleName()+" obj){\r\n";
		method1+="\t\tint n=0;\r\n";
		method1+="\t\ttry {\r\n";
		method1+="\t\t\tcon=JDBCFactory.getCon();\r\n";
		method1+="\t\t\tcon.setAutoCommit(false);\r\n";		
		method1+="\t\t\tString sql=\"insert into "+cla.getSimpleName().toUpperCase()+"("+fie+zd+") "+"values("+seq+".nextval"+wh+")\";\r\n";
		method1+="\t\t\tps=con.prepareStatement(sql);\r\n";
		for (int i = 0; i < fs.length; i++) {		
			if(!fie.equals(fs[i].getName())){
				if("Date".equals(fs[i].getType().getSimpleName())){
					method1+="\t\t\tps.setTimestamp("+i+",new Timestamp(obj.get"+DAOFactory.changeNameType(fs[i].getName())+"().getTime()));\r\n";
					continue;
				}
				method1+="\t\t\tps.set"+DAOFactory.changeNameType(fs[i].getType().getSimpleName())+"("+i+",obj.get"+DAOFactory.changeNameType(fs[i].getName())+"());\r\n";
			}
		}
		method1+="\t\t\tn=ps.executeUpdate();\r\n";				
		method1+="\t\t}catch(Exception e) {\r\n";
		method1+="\t\t\ttry {\r\n";
		method1+="\t\t\t\tcon.rollback();\r\n";
		method1+="\t\t\t}catch(Exception e1) {\r\n";
		method1+="\t\t\t\te.printStackTrace();\r\n";
		method1+="\t\t\t}\r\n";
		method1+="\t\t\te.printStackTrace();\r\n";
		method1+="\t\t}finally{\r\n";
		method1+="\t\t\tJDBCFactory.closeAll(con, sta, rs);\r\n";
		method1+="\t\t}\r\n";
		method1+="\t\treturn n;\r\n";
		method1+="\t}\r\n";
		return method1;
		
	}
	public static String getBody2(Object obj){
		Class cla=obj.getClass();//得到类
		Field fs[]=cla.getDeclaredFields();//得到所有属性
		String method2="\tpublic int deleteObject("+cla.getSimpleName()+" obj){\r\n";
		method2+="\t\tint n=0;\r\n";
		method2+="\t\ttry {\r\n";
		method2+="\t\t\tcon=JDBCFactory.getCon();\r\n";
		method2+="\t\t\tcon.setAutoCommit(false);\r\n";
		
		Field fie=null;//主键字段
		for (int i = 0; i < fs.length; i++) {
			Onlyid id=fs[i].getAnnotation(Onlyid.class);			
			if(id!=null){
				fie=fs[i];				
			}
		}
		method2+="\t\t\tString sql = \"delete from "+cla.getSimpleName().toUpperCase()+" where "+fie.getName()+"=?\";\r\n";
		method2+="\t\t\tps = con.prepareStatement(sql);\r\n";
		method2+="\t\t\tps.set"+DAOFactory.changeNameType(fie.getType().getSimpleName())+"(1,obj.get"+DAOFactory.changeNameType(fie.getName())+"());\r\n";
		method2+="\t\t\tn=ps.executeUpdate();\r\n";			
		method2+="\t\t}catch(Exception e) {\r\n";
		method2+="\t\t\ttry {\r\n";
		method2+="\t\t\t\tcon.rollback();\r\n";
		method2+="\t\t\t}catch(Exception e1) {\r\n";
		method2+="\t\t\t\te.printStackTrace();\r\n";
		method2+="\t\t\t}\r\n";
		method2+="\t\t\te.printStackTrace();\r\n";
		method2+="\t\t}finally{\r\n";
		method2+="\t\t\tJDBCFactory.closeAll(con, sta, rs);\r\n";
		method2+="\t\t}\r\n";
		method2+="\t\treturn n;\r\n";
		method2+="\t}\r\n";
		return method2;
		
	}
	public static String getBody3(Object obj){
		Class cla=obj.getClass();//得到类
		Field fs[]=cla.getDeclaredFields();//得到所有属性
		String method3="\tpublic int updateObject("+cla.getSimpleName()+" obj){\r\n";
		method3+="\t\tint n=0;\r\n";
		method3+="\t\ttry {\r\n";
		method3+="\t\t\tcon=JDBCFactory.getCon();\r\n";
		method3+="\t\t\tcon.setAutoCommit(false);\r\n";
		String str="";//键值对
		Field fie=null;//主键字段
		for (int i = 0; i < fs.length; i++) {
			Onlyid id=fs[i].getAnnotation(Onlyid.class);
			if(id!=null){
				fie=fs[i];
				continue;
			}
			if(i==(fs.length-1)){
				str+=fs[i].getName()+"=?";
			}else{
				str+=fs[i].getName()+"=?,";
			}			
		}
		String sql = "update "+cla.getSimpleName().toUpperCase()+" set "+str+" where "+fie.getName()+"=?";
		method3+="\t\t\tString sql = \""+sql+"\";\r\n";	
		method3+="\t\t\tps = con.prepareStatement(sql);\r\n";
		for (int i = 0; i < fs.length; i++) {		
			if(!(fie.getName()).equals(fs[i].getName())){
				if("Date".equals(fs[i].getType().getSimpleName())){
					method3+="\t\t\tps.setTimestamp("+i+",new Timestamp(obj.get"+DAOFactory.changeNameType(fs[i].getName())+"().getTime()));\r\n";
					continue;
				}
				method3+="\t\t\tps.set"+DAOFactory.changeNameType(fs[i].getType().getSimpleName())+"("+i+",obj.get"+DAOFactory.changeNameType(fs[i].getName())+"());\r\n";
			}
		}
		method3+="\t\t\tps.set"+DAOFactory.changeNameType(fie.getType().getSimpleName())+"("+fs.length+", obj.get"+DAOFactory.changeNameType(fie.getName())+"());\r\n";	
		method3+="\t\t\tn=ps.executeUpdate();\r\n";					
		method3+="\t\t}catch(Exception e) {\r\n";
		method3+="\t\t\ttry {\r\n";
		method3+="\t\t\tcon.rollback();\r\n";
		method3+="\t\t\t}catch(Exception e1) {\r\n";
		method3+="\t\t\t\te.printStackTrace();\r\n";
		method3+="\t\t\t}\r\n";
		method3+="\t\t\te.printStackTrace();\r\n";
		method3+="\t\t}finally{\r\n";
		method3+="\t\t\tJDBCFactory.closeAll(con, sta, rs);\r\n";
		method3+="\t\t}\r\n";
		method3+="\t\treturn n;\r\n";
		method3+="\t}\r\n";
		return method3;
		
	}
	public static String getBody4(Object obj){
		Class cla=obj.getClass();//得到类
		Field fs[]=cla.getDeclaredFields();//得到所有属性
		String method4="\tpublic ArrayList<"+obj.getClass().getSimpleName()+"> selectObject("+cla.getSimpleName()+" obj){\r\n";
		method4+="\t\tArrayList<"+obj.getClass().getSimpleName()+"> al=new ArrayList();\r\n";
		method4+="\t\ttry {\r\n";
		method4+="\t\t\tcon=JDBCFactory.getCon();\r\n";
		method4+="\t\t\tcon.setAutoCommit(false);\r\n";
		method4+="\t\t\tsta=con.createStatement();\r\n";
		method4+="\t\t\tString sql=\"select * from "+cla.getSimpleName().toUpperCase()+" where 1=1\";\r\n";
		for (int i = 0; i < fs.length; i++) {
			String fn=DAOFactory.changeNameType(fs[i].getName());
			String iv=DAOFactory.getInitialValue(fs[i].getType().getSimpleName());			
			method4+="\t\t\tif(obj.get"+fn+"()!="+iv+"){\r\n";
			method4+="\t\t\t\tsql+=\" and "+fs[i].getName()+"='\"+obj.get"+fn+"()+\"'\";\r\n";
			method4+="\t\t\t}\r\n";
		}
		method4+="\t\t\trs=sta.executeQuery(sql);\r\n";
		method4+="\t\t\twhile(rs.next()){\r\n";
		method4+="\t\t\t\t"+cla.getSimpleName()+" newObj=new "+cla.getSimpleName()+"();\r\n";
		for (int i = 0; i < fs.length; i++) {
			String fn=DAOFactory.changeNameType(fs[i].getName());
			String cn=DAOFactory.changeNameType(fs[i].getType().getSimpleName());
			method4+="\t\t\t\tnewObj.set"+fn+"(rs.get"+cn+"(\""+fs[i].getName()+"\"));\r\n";			
		}
		method4+="\t\t\t\tal.add(newObj);\r\n";
		method4+="\t\t\t}\r\n";
		
		method4+="\t\t}catch(Exception e) {\r\n";
		method4+="\t\t\ttry {\r\n";
		method4+="\t\t\t\tcon.rollback();\r\n";
		method4+="\t\t\t}catch(Exception e1) {\r\n";
		method4+="\t\t\t\te.printStackTrace();\r\n";
		method4+="\t\t\t}\r\n";
		method4+="\t\t\te.printStackTrace();\r\n";
		method4+="\t\t}finally{\r\n";
		method4+="\t\t\tJDBCFactory.closeAll(con, sta, rs);\r\n";
		method4+="\t\t}\r\n";
		method4+="\t\treturn al;\r\n";
		method4+="\t}\r\n";
		return method4;
		
	}
	private static String changeNameType(String oldName){//方法：将首字母大写
		String newName=null;
		newName=(oldName.charAt(0)+"").toUpperCase()+oldName.substring(1).toLowerCase();
		return newName;
	}
	private static String getInitialValue(String className){//方法：得到初始值
		String value=null;
		switch(className){
		case "String":
		case "Date":
			return "null";
		case "short":
		case "int":
		case "long":
			return "0";
		case "float":
		case "double":
			return "0.0";
		}
		return value;
	}
	
//	测试方法
	public static void main(String[] args) {
		Userinformation ui=new Userinformation();
		Userkey uk=new Userkey();
		Subjectinformation si=new Subjectinformation();
		DAOFactory.getDAO(ui, "com.dao");
		DAOFactory.getDAO(uk, "com.dao");
		DAOFactory.getDAO(si, "com.dao");
	}
}
