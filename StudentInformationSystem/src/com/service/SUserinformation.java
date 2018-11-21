package com.service;

import java.util.ArrayList;

import com.dao.DUserinformation;
import com.pojo.Userinformation;

public class SUserinformation implements ISUserinformation {
	DUserinformation dui=new DUserinformation();
	@Override
	public boolean addUserinformation(Userinformation ui) {		
		// TODO Auto-generated method stub
		boolean result=false;
		int n=dui.insertObject(ui);
		
		if(n>0){
			result=true;
		}
		
		return result;
	}

	@Override
	public ArrayList<Userinformation> checkUserinformation(Userinformation ui) {
		// TODO Auto-generated method stub
		ArrayList<Userinformation> al=new ArrayList();		
		al=dui.selectObject(ui);
		return al;
	}

	@Override
	public Userinformation returnUserinformation(int key_id) {
		// TODO Auto-generated method stub
		Userinformation ui=new Userinformation();
		ui.setKey_id(key_id);
		ArrayList<Userinformation> al=new ArrayList();
		al=dui.selectObject(ui);	
		return al.get(0);
	}

}
