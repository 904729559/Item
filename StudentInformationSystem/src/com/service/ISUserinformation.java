package com.service;

import java.util.ArrayList;

import com.pojo.Userinformation;

public interface ISUserinformation {
	public boolean addUserinformation(Userinformation ui);//添加
	public ArrayList<Userinformation> checkUserinformation(Userinformation ui);//查询
	public Userinformation returnUserinformation(int key_id);//根据id查找对象
}
