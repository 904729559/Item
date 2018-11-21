package com.dao;

import java.sql.*;
import java.util.*;
import com.factory.JDBCFactory;
import com.pojo.Userkey;

public class DUserkey{
	Connection con=null;
	Statement sta=null;
	PreparedStatement ps=null;
	ResultSet rs=null;

	public int insertObject(Userkey obj){
		int n=0;
		try {
			con=JDBCFactory.getCon();
			con.setAutoCommit(false);
			String sql="insert into USERKEY(key_id,key_name,key_password,key_isadmin) values(seq_key.nextval,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1,obj.getKey_name());
			ps.setString(2,obj.getKey_password());
			ps.setInt(3,obj.getKey_isadmin());
			n=ps.executeUpdate();
		}catch(Exception e) {
			try {
				con.rollback();
			}catch(Exception e1) {
				e.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			JDBCFactory.closeAll(con, sta, rs);
		}
		return n;
	}

	public int deleteObject(Userkey obj){
		int n=0;
		try {
			con=JDBCFactory.getCon();
			con.setAutoCommit(false);
			String sql = "delete from USERKEY where key_id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1,obj.getKey_id());
			n=ps.executeUpdate();
		}catch(Exception e) {
			try {
				con.rollback();
			}catch(Exception e1) {
				e.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			JDBCFactory.closeAll(con, sta, rs);
		}
		return n;
	}

	public int updateObject(Userkey obj){
		int n=0;
		try {
			con=JDBCFactory.getCon();
			con.setAutoCommit(false);
			String sql = "update USERKEY set key_name=?,key_password=?,key_isadmin=? where key_id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1,obj.getKey_name());
			ps.setString(2,obj.getKey_password());
			ps.setInt(3,obj.getKey_isadmin());
			ps.setInt(4, obj.getKey_id());
			n=ps.executeUpdate();
		}catch(Exception e) {
			try {
			con.rollback();
			}catch(Exception e1) {
				e.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			JDBCFactory.closeAll(con, sta, rs);
		}
		return n;
	}

	public ArrayList<Userkey> selectObject(Userkey obj){
		ArrayList<Userkey> al=new ArrayList();
		try {
			con=JDBCFactory.getCon();
			con.setAutoCommit(false);
			sta=con.createStatement();
			String sql="select * from UserKey where 1=1";
			if(obj.getKey_id()!=0){
				sql+=" and key_id='"+obj.getKey_id()+"'";
			}
			if(obj.getKey_name()!=null){
				sql+=" and key_name='"+obj.getKey_name()+"'";
			}
			if(obj.getKey_password()!=null){
				sql+=" and key_password='"+obj.getKey_password()+"'";
			}
			if(obj.getKey_isadmin()!=0){
				sql+=" and key_isadmin='"+obj.getKey_isadmin()+"'";
			}
			rs=sta.executeQuery(sql);
			while(rs.next()){
				Userkey newObj=new Userkey();
				newObj.setKey_id(rs.getInt("key_id"));
				newObj.setKey_name(rs.getString("key_name"));
				newObj.setKey_password(rs.getString("key_password"));
				newObj.setKey_isadmin(rs.getInt("key_isadmin"));
				al.add(newObj);
			}
		}catch(Exception e) {
			try {
				con.rollback();
			}catch(Exception e1) {
				e.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			JDBCFactory.closeAll(con, sta, rs);
		}
		return al;
	}

}