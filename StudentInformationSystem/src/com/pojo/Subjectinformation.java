package com.pojo;

import java.util.Date;

import com.factory.Onlyid;
	
public class Subjectinformation{
	@Onlyid(seqName="seq_subject")
	private int sub_id;
	private String sub_name;
	private int sub_score;
	private Date sub_time;
	private int use_id;
	public int getSub_id() {
		return sub_id;
	}
	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	public int getSub_score() {
		return sub_score;
	}
	public void setSub_score(int sub_score) {
		this.sub_score = sub_score;
	}
	public Date getSub_time() {
		return sub_time;
	}
	public void setSub_time(Date sub_time) {
		this.sub_time = sub_time;
	}
	public int getUse_id() {
		return use_id;
	}
	public void setUse_id(int use_id) {
		this.use_id = use_id;
	}
	
	
}