package com.service;

import java.util.ArrayList;

import com.pojo.Userinformation;

public interface ISUserinformation {
	public boolean addUserinformation(Userinformation ui);//���
	public ArrayList<Userinformation> checkUserinformation(Userinformation ui);//��ѯ
	public Userinformation returnUserinformation(int key_id);//����id���Ҷ���
}
