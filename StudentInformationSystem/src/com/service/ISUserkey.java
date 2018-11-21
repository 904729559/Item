package com.service;

import com.pojo.Userkey;

//关于UserKey的业务

public interface ISUserkey {
	public boolean isUser(String name);//是否已存在用户名
	public boolean isPass(String name,String password);//是否通过登陆
	public int userstyle(String name);//显示用户级别
	public boolean changeUserstyle(String name,int n);//改变用户级别
	public Userkey returnUserkey(String name,String password);//获取对象
	public boolean addUserkey(Userkey uk);//新增用户
}
