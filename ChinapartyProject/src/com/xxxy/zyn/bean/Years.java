package com.xxxy.zyn.bean;

import java.io.Serializable;
//implements Serializable表示序列化
public class Years implements Serializable{
	//定义数据库中years表中的属性
	String years_id;
	String yearsname;
	String yearsflag;
	//构造方法
	//不带参数
	public Years() {
		super();
		// TODO Auto-generated constructor stub
	}
	//带参数
	public Years(String years_id, String yearsname, String yearsflag) {
		super();
		this.years_id = years_id;
		this.yearsname = yearsname;
		this.yearsflag = yearsflag;
	}
	public String getYears_id() {
		return years_id;
	}
	//get set 方法
	public void setYears_id(String years_id) {
		this.years_id = years_id;
	}
	public String getYearsname() {
		return yearsname;
	}
	public void setYearsname(String yearsname) {
		this.yearsname = yearsname;
	}
	public String getYearsflag() {
		return yearsflag;
	}
	public void setYearsflag(String yearsflag) {
		this.yearsflag = yearsflag;
	}
	
	
	
	
}
