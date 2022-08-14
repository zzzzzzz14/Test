package com.xxxy.zyn.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
//实现接口
public class Classes implements Serializable{
	private String classes_id;
	private String classesName;
	private String classesFlag;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date classesCDate;
	//years_id 是外键
	private String years_id;
	private String yearsname;
	//构造方法
	//无任何参数
	public Classes() {
		super();
		// TODO Auto-generated constructor stub
	}
	//构造方法
	//包含表中所有的属性
	public Classes(String classes_id, String classesName, String classesFlag, Date classesCDate, String years_id,
			String yearsname) {
		super();
		this.classes_id = classes_id;
		this.classesName = classesName;
		this.classesFlag = classesFlag;
		this.classesCDate = classesCDate;
		this.years_id = years_id;
		this.yearsname = yearsname;
	}
	//建立表中属性的get set方法
	public String getClasses_id() {
		return classes_id;
	}
	public void setClasses_id(String classes_id) {
		this.classes_id = classes_id;
	}
	public String getClassesName() {
		return classesName;
	}
	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}
	public String getClassesFlag() {
		return classesFlag;
	}
	public void setClassesFlag(String classesFlag) {
		this.classesFlag = classesFlag;
	}
	public Date getClassesCDate() {
		return classesCDate;
	}
	public void setClassesCDate(Date classesCDate) {
		this.classesCDate = classesCDate;
	}
	public String getYears_id() {
		return years_id;
	}
	public void setYears_id(String years_id) {
		this.years_id = years_id;
	}
	public String getYearsname() {
		return yearsname;
	}
	public void setYearsname(String yearsname) {
		this.yearsname = yearsname;
	}
	//字符转换
	@Override
	public String toString() {
		return "Classes [classes_id=" + classes_id + ", classesName=" + classesName + ", classesFlag=" + classesFlag
				+ ", classesCDate=" + classesCDate + ", years_id=" + years_id + ", yearsname=" + yearsname + "]";
	}
	
	//
	
	
	
}
