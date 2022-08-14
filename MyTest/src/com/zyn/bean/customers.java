package com.zyn.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Date;

/***
 * 一个数据表对应一个java类
 * 表中的一条记录对应java类的一个对象
 * 表中的一个字段对应java类的一个属性
 * @author zhang ya ning
 *
 */


public class customers {
	private String name;
	private int id;
	private String email;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date birth;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public customers() {
		super();
		// TODO Auto-generated constructor stub
	}
	public customers(int id, String name, String email, Date birth) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "customers [id=" + id + ", name=" + name + ", email=" + email + ", birth=" + birth + "]";
	}
	
	
}
