package com.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import com.factory.JDBCFactory;
import com.pojo.Userinformation;

public class DUserinformation{
	Connection con=null;
	Statement sta=null;
	PreparedStatement ps=null;
	ResultSet rs=null;

	public int insertObject(Userinformation obj){
		int n=0;
		try {
			con=JDBCFactory.getCon();
			con.setAutoCommit(false);
			String sql="insert into USERINFORMATION(use_id,use_idcard,use_name,use_sex,use_birthday,use_hometown,use_schoolstart,use_schoolend,use_inschool,use_professional,use_class,key_id) values(seq_user.nextval,?,?,?,?,?,?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1,obj.getUse_idcard());
			ps.setString(2,obj.getUse_name());
			ps.setString(3,obj.getUse_sex());
			ps.setDate(4,new Date(obj.getUse_birthday().getTime()));//仅仅获取日期，java.util.Date在使用其方法时不能为null！
			ps.setString(5,obj.getUse_hometown());
			ps.setDate(6,new Date(obj.getUse_schoolstart().getTime()));
			ps.setDate(7,new Date(obj.getUse_schoolend().getTime()));
			ps.setInt(8,obj.getUse_inschool());
			ps.setString(9,obj.getUse_professional());
			ps.setString(10,obj.getUse_class());
			ps.setInt(11,obj.getKey_id());
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

	public int deleteObject(Userinformation obj){
		int n=0;
		try {
			con=JDBCFactory.getCon();
			con.setAutoCommit(false);
			String sql = "delete from USERINFORMATION where use_id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1,obj.getUse_id());
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

	public int updateObject(Userinformation obj){
		int n=0;
		try {
			con=JDBCFactory.getCon();
			con.setAutoCommit(false);
			String sql = "update USERINFORMATION set use_idcard=?,use_name=?,use_sex=?,use_birthday=?,use_hometown=?,use_schoolstart=?,use_schoolend=?,use_inschool=?,use_professional=?,use_class=?,key_id=? where use_id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1,obj.getUse_idcard());
			ps.setString(2,obj.getUse_name());
			ps.setString(3,obj.getUse_sex());
			ps.setTimestamp(4,new Timestamp(obj.getUse_birthday().getTime()));
			ps.setString(5,obj.getUse_hometown());
			ps.setTimestamp(6,new Timestamp(obj.getUse_schoolstart().getTime()));
			ps.setTimestamp(7,new Timestamp(obj.getUse_schoolend().getTime()));
			ps.setInt(8,obj.getUse_inschool());
			ps.setString(9,obj.getUse_professional());
			ps.setString(10,obj.getUse_class());
			ps.setInt(11,obj.getKey_id());
			ps.setInt(12, obj.getUse_id());
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

	public ArrayList<Userinformation> selectObject(Userinformation obj){
		ArrayList<Userinformation> al=new ArrayList();
		try {
			con=JDBCFactory.getCon();
			con.setAutoCommit(false);
			sta=con.createStatement();
			String sql="select * from USERINFORMATION where 1=1";
			if(obj.getUse_id()!=0){
				sql+=" and use_id='"+obj.getUse_id()+"'";
			}
			if(obj.getUse_idcard()!=null){
				sql+=" and use_idcard='"+obj.getUse_idcard()+"'";
			}
			if(obj.getUse_name()!=null){
				sql+=" and use_name='"+obj.getUse_name()+"'";
			}
			if(obj.getUse_sex()!=null){
				sql+=" and use_sex='"+obj.getUse_sex()+"'";
			}
			if(obj.getUse_birthday()!=null){
				sql+=" and use_birthday='"+obj.getUse_birthday()+"'";
			}
			if(obj.getUse_hometown()!=null){
				sql+=" and use_hometown='"+obj.getUse_hometown()+"'";
			}
			if(obj.getUse_schoolstart()!=null){
				sql+=" and use_schoolstart='"+obj.getUse_schoolstart()+"'";
			}
			if(obj.getUse_schoolend()!=null){
				sql+=" and use_schoolend='"+obj.getUse_schoolend()+"'";
			}
			if(obj.getUse_inschool()!=0){
				sql+=" and use_inschool='"+obj.getUse_inschool()+"'";
			}
			if(obj.getUse_professional()!=null){
				sql+=" and use_professional='"+obj.getUse_professional()+"'";
			}
			if(obj.getUse_class()!=null){
				sql+=" and use_class='"+obj.getUse_class()+"'";
			}
			if(obj.getKey_id()!=0){
				sql+=" and key_id='"+obj.getKey_id()+"'";
			}
			rs=sta.executeQuery(sql);
			System.out.println(sql);
			while(rs.next()){
				Userinformation newObj=new Userinformation();
				newObj.setUse_id(rs.getInt("use_id"));
				newObj.setUse_idcard(rs.getString("use_idcard"));
				newObj.setUse_name(rs.getString("use_name"));
				newObj.setUse_sex(rs.getString("use_sex"));
				newObj.setUse_birthday(rs.getDate("use_birthday"));
				newObj.setUse_hometown(rs.getString("use_hometown"));
				newObj.setUse_schoolstart(rs.getDate("use_schoolstart"));
				newObj.setUse_schoolend(rs.getDate("use_schoolend"));
				newObj.setUse_inschool(rs.getInt("use_inschool"));
				newObj.setUse_professional(rs.getString("use_professional"));
				newObj.setUse_class(rs.getString("use_class"));
				newObj.setKey_id(rs.getInt("key_id"));
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