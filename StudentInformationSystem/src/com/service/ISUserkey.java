package com.service;

import com.pojo.Userkey;

//����UserKey��ҵ��

public interface ISUserkey {
	public boolean isUser(String name);//�Ƿ��Ѵ����û���
	public boolean isPass(String name,String password);//�Ƿ�ͨ����½
	public int userstyle(String name);//��ʾ�û�����
	public boolean changeUserstyle(String name,int n);//�ı��û�����
	public Userkey returnUserkey(String name,String password);//��ȡ����
	public boolean addUserkey(Userkey uk);//�����û�
}
