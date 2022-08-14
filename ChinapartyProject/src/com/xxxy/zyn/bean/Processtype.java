package com.xxxy.zyn.bean;

import java.io.Serializable;
import java.util.Date;


public class Processtype implements Serializable{
 private String processtype_id;
 private String ptypeName;
 private String ptypeFlag;
 
public Processtype() {
	super();
	// TODO Auto-generated constructor stub
}
public Processtype(String processtype_id, String ptypeName, String ptypeFlag, Date ptypeCDate) {
	super();
	this.processtype_id = processtype_id;
	this.ptypeName = ptypeName;
	this.ptypeFlag = ptypeFlag;
	
}
public String getProcesstype_id() {
	return processtype_id;
}
public void setProcesstype_id(String processtype_id) {
	this.processtype_id = processtype_id;
}
public String getPtypeName() {
	return ptypeName;
}
public void setPtypeName(String ptypeName) {
	this.ptypeName = ptypeName;
}
public String getPtypeFlag() {
	return ptypeFlag;
}
public void setPtypeFlag(String ptypeFlag) {
	this.ptypeFlag = ptypeFlag;
}

@Override
public String toString() {
	return "Processtype [processtype_id=" + processtype_id + ", ptypeName=" + ptypeName + ", ptypeFlag=" + ptypeFlag
			 + "]";
}
	 
	
}