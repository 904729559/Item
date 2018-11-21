package com.service;

import java.util.ArrayList;
import com.dao.DUserkey;
import com.pojo.Userkey;

public class SUserkey implements ISUserkey {
	DUserkey duk=new DUserkey();
	@Override
	public boolean isUser(String name) {
		// TODO Auto-generated method stub
		boolean result=false;
		Userkey uk=new Userkey();
		uk.setKey_name(name);
		ArrayList<Userkey> al=duk.selectObject(uk);
		if(al!=null&&al.size()>0){
			result=true;
		}
		return result;
	}

	@Override
	public boolean isPass(String name, String password) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		Userkey uk=new Userkey();
		uk.setKey_name(name);
		uk.setKey_password(password);
		
		ArrayList<Userkey> al=duk.selectObject(uk);
		if(al!=null&&al.size()>0){
			result=true;
		}
		return result;
	}

	@Override
	public int userstyle(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean changeUserstyle(String name, int n) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Userkey returnUserkey(String name,String password) {
		// TODO Auto-generated method stub
		Userkey Uk=new Userkey();
		
		Userkey uk=new Userkey();
		uk.setKey_name(name);
		uk.setKey_password(password);
		ArrayList<Userkey> al=duk.selectObject(uk);
		if(al!=null&&al.size()>0){
			Uk=al.get(0);
		}
		return Uk;
	}

	@Override
	public boolean addUserkey(Userkey uk) {
		// TODO Auto-generated method stub
		boolean result=false;
		int n=duk.insertObject(uk);
		if(n>0){
			result=true;
		}
		
		return result;
	}

}
