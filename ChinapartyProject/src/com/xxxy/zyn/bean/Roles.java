package com.xxxy.zyn.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class Roles implements Serializable {
	private String roles_id;
	private String rolesName;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date rolesCDate;
	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Roles(String roles_id, String rolesName, Date rolesCDate) {
		super();
		this.roles_id = roles_id;
		this.rolesName = rolesName;
		this.rolesCDate = rolesCDate;
	}
	
	public String getRoles_id() {
		return roles_id;
	}
	public void setRoles_id(String roles_id) {
		this.roles_id = roles_id;
	}
	public String getRolesName() {
		return rolesName;
	}
	public void setRolesName(String rolesName) {
		this.rolesName = rolesName;
	}
	public void Roles(String rolesName) {
		this.rolesName = rolesName;
	}
	public Date getRolesCDate() {
		return rolesCDate;
	}
	public void setRolesCDate(Date rolesCDate) {
		this.rolesCDate = rolesCDate;
	}
	@Override
	public String toString() {
		return "Roles [roles_id=" + roles_id + ", rolesName=" + rolesName + ", rolesCDate=" + rolesCDate + "]";
	}
	
}
