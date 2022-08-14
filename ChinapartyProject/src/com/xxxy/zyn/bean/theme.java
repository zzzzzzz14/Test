package com.xxxy.zyn.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class theme {
 private String theme_id;
 private String themeTitle;
 private String themeFlag;
 @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date themeCDate;
public theme() {
	super();
	// TODO Auto-generated constructor stub
}
public theme(String theme_id, String themeTitle, String themeFlag, Date themeCDate) {
	super();
	this.theme_id = theme_id;
	this.themeTitle = themeTitle;
	this.themeFlag = themeFlag;
	this.themeCDate = themeCDate;
}
public String getTheme_id() {
	return theme_id;
}
public void setTheme_id(String theme_id) {
	this.theme_id = theme_id;
}
public String getThemeTitle() {
	return themeTitle;
}
public void setThemeTitle(String themeTitle) {
	this.themeTitle = themeTitle;
}
public String getThemeFlag() {
	return themeFlag;
}
public void setThemeFlag(String themeFlag) {
	this.themeFlag = themeFlag;
}
public Date getThemeCDate() {
	return themeCDate;
}
public void setThemeCDate(Date themeCDate) {
	this.themeCDate = themeCDate;
}
@Override
public String toString() {
	return "Theme [theme_id=" + theme_id + ", themeTitle=" + themeTitle + ", themeFlag=" + themeFlag + ", themeCDate="
			+ themeCDate + "]";
}
 
}
