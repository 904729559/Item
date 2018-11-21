package com.dao;

import java.sql.*;
import java.util.*;
import com.factory.JDBCFactory;
import com.pojo.Subjectinformation;

public class DSubjectinformation{
	Connection con=null;
	Statement sta=null;
	PreparedStatement ps=null;
	ResultSet rs=null;

	public int insertObject(Subjectinformation obj){
		int n=0;
		try {
			con=JDBCFactory.getCon();
			con.setAutoCommit(false);
			String sql="insert into SUBJECTINFORMATION(sub_id,sub_name,sub_score,sub_time,use_id) values(seq_subject.nextval,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1,obj.getSub_name());
			ps.setInt(2,obj.getSub_score());
			ps.setTimestamp(3,new Timestamp(obj.getSub_time().getTime()));
			ps.setInt(4,obj.getUse_id());
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

	public int deleteObject(Subjectinformation obj){
		int n=0;
		try {
			con=JDBCFactory.getCon();
			con.setAutoCommit(false);
			String sql = "delete from SUBJECTINFORMATION where sub_id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1,obj.getSub_id());
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

	public int updateObject(Subjectinformation obj){
		int n=0;
		try {
			con=JDBCFactory.getCon();
			con.setAutoCommit(false);
			String sql = "update SUBJECTINFORMATION set sub_name=?,sub_score=?,sub_time=?,use_id=? where sub_id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1,obj.getSub_name());
			ps.setInt(2,obj.getSub_score());
			ps.setTimestamp(3,new Timestamp(obj.getSub_time().getTime()));
			ps.setInt(4,obj.getUse_id());
			ps.setInt(5, obj.getSub_id());
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

	public ArrayList<Subjectinformation> selectObject(Subjectinformation obj){
		ArrayList<Subjectinformation> al=new ArrayList();
		try {
			con=JDBCFactory.getCon();
			con.setAutoCommit(false);
			sta=con.createStatement();
			String sql="select * from UserKey where 1=1";
			if(obj.getSub_id()!=0){
				sql+=" and sub_id='"+obj.getSub_id()+"'";
			}
			if(obj.getSub_name()!=null){
				sql+=" and sub_name='"+obj.getSub_name()+"'";
			}
			if(obj.getSub_score()!=0){
				sql+=" and sub_score='"+obj.getSub_score()+"'";
			}
			if(obj.getSub_time()!=null){
				sql+=" and sub_time='"+obj.getSub_time()+"'";
			}
			if(obj.getUse_id()!=0){
				sql+=" and use_id='"+obj.getUse_id()+"'";
			}
			rs=sta.executeQuery(sql);
			while(rs.next()){
				Subjectinformation newObj=new Subjectinformation();
				newObj.setSub_id(rs.getInt("sub_id"));
				newObj.setSub_name(rs.getString("sub_name"));
				newObj.setSub_score(rs.getInt("sub_score"));
				newObj.setSub_time(rs.getDate("sub_time"));
				newObj.setUse_id(rs.getInt("use_id"));
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