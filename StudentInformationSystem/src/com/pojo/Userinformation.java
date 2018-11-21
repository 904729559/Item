package com.pojo;

import java.util.Date;

import com.factory.Onlyid;
	
public class Userinformation{
	@Onlyid(seqName="seq_user")
	private int use_id;
	private String use_idcard;
	private String use_name;
	private String use_sex;
	private Date use_birthday;
	private String use_hometown;
	private Date use_schoolstart;
	private Date use_schoolend;
	private int use_inschool;
	private String use_professional;
	private String use_class;
	private int key_id;
	
	
	public int getUse_id() {
		return use_id;
	}
	public void setUse_id(int use_id) {
		this.use_id = use_id;
	}
	public String getUse_idcard() {
		return use_idcard;
	}
	public void setUse_idcard(String use_idcard) {
		this.use_idcard = use_idcard;
	}
	public String getUse_name() {
		return use_name;
	}
	public void setUse_name(String use_name) {
		this.use_name = use_name;
	}
	public String getUse_sex() {
		return use_sex;
	}
	public void setUse_sex(String use_sex) {
		this.use_sex = use_sex;
	}
	public Date getUse_birthday() {
		return use_birthday;
	}
	public void setUse_birthday(Date use_birthday) {
		this.use_birthday = use_birthday;
	}
	public String getUse_hometown() {
		return use_hometown;
	}
	public void setUse_hometown(String use_hometown) {
		this.use_hometown = use_hometown;
	}
	public Date getUse_schoolstart() {
		return use_schoolstart;
	}
	public void setUse_schoolstart(Date use_schoolstart) {
		this.use_schoolstart = use_schoolstart;
	}
	public Date getUse_schoolend() {
		return use_schoolend;
	}
	public void setUse_schoolend(Date use_schoolend) {
		this.use_schoolend = use_schoolend;
	}
	public int getUse_inschool() {
		return use_inschool;
	}
	public void setUse_inschool(int use_inschool) {
		this.use_inschool = use_inschool;
	}
	public String getUse_professional() {
		return use_professional;
	}
	public void setUse_professional(String use_professional) {
		this.use_professional = use_professional;
	}
	public String getUse_class() {
		return use_class;
	}
	public void setUse_class(String use_class) {
		this.use_class = use_class;
	}
	public int getKey_id() {
		return key_id;
	}
	public void setKey_id(int key_id) {
		this.key_id = key_id;
	}
	
}