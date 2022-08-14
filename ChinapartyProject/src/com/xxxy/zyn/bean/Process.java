package com.xxxy.zyn.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class Process implements Serializable {
	private String process_id;
	private String processtype_id;
	private String processDate;
	private String processPlace;
	private String processHandler;
	private String processContent;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date processTime;
	private String logins_id;
	private String ptypeName;//履历类姓名
	public Process() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Process(String process_id, String processtype_id, String processDate, String processPlace,
			String processHandler, String processContent, Date processTime, String logins_id) {
		super();
		this.process_id = process_id;
		this.processtype_id = processtype_id;
		this.processDate = processDate;
		this.processPlace = processPlace;
		this.processHandler = processHandler;
		this.processContent = processContent;
		this.processTime = processTime;
		this.logins_id = logins_id;
	}

	public String getProcess_id() {
		return process_id;
	}

	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}

	public String getProcesstype_id() {
		return processtype_id;
	}

	public void setProcesstype_id(String processtype_id) {
		this.processtype_id = processtype_id;
	}

	public String getProcessDate() {
		return processDate;
	}

	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}

	public String getProcessPlace() {
		return processPlace;
	}

	public void setProcessPlace(String processPlace) {
		this.processPlace = processPlace;
	}

	public String getProcessHandler() {
		return processHandler;
	}

	public void setProcessHandler(String processHandler) {
		this.processHandler = processHandler;
	}

	public String getProcessContent() {
		return processContent;
	}

	public void setProcessContent(String processContent) {
		this.processContent = processContent;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public String getLogins_id() {
		return logins_id;
	}

	public void setLogins_id(String logins_id) {
		this.logins_id = logins_id;
	}

	public String getPtypeName() {
		return ptypeName;
	}

	public void setPtypeName(String ptypeName) {
		this.ptypeName = ptypeName;
	}

	@Override
	public String toString() {
		return "Process [process_id=" + process_id + ", processtype_id=" + processtype_id + ", processDate="
				+ processDate + ", processPlace=" + processPlace + ", processHandler=" + processHandler
				+ ", processContent=" + processContent + ", processTime=" + processTime + ", logins_id=" + logins_id
				+ ", ptypeName=" + ptypeName + "]";
	}

	
}
