package com.pojo;

import com.factory.Onlyid;

public class Userkey{
	@Onlyid(seqName="seq_key")
	private int key_id;
	private String key_name;
	private String key_password;
	private int key_isadmin;
	public int getKey_id() {
		return key_id;
	}
	public void setKey_id(int key_id) {
		this.key_id = key_id;
	}
	public String getKey_name() {
		return key_name;
	}
	public void setKey_name(String key_name) {
		this.key_name = key_name;
	}
	public String getKey_password() {
		return key_password;
	}
	public void setKey_password(String key_password) {
		this.key_password = key_password;
	}
	public int getKey_isadmin() {
		return key_isadmin;
	}
	public void setKey_isadmin(int key_isadmin) {
		this.key_isadmin = key_isadmin;
	}
	
}