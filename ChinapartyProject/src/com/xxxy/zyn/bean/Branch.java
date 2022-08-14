package com.xxxy.zyn.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Branch {
	private String branch_id;
	private String branchName;
	private String branchPid;
	private String branchFlag;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date branchCDate;
	private String pbname;
	public Branch() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Branch(String branch_id, String branchName, String branchPid, String branchFlag, Date branchCDate,
			String pbname) {
		super();
		this.branch_id = branch_id;
		this.branchName = branchName;
		this.branchPid = branchPid;
		this.branchFlag = branchFlag;
		this.branchCDate = branchCDate;
		this.pbname = pbname;
	}

	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchPid() {
		return branchPid;
	}
	public void setBranchPid(String branchPid) {
		this.branchPid = branchPid;
	}
	public String getBranchFlag() {
		return branchFlag;
	}
	public void setBranchFlag(String branchFlag) {
		this.branchFlag = branchFlag;
	}
	public Date getBranchCDate() {
		return branchCDate;
	}
	public void setBranchCDate(Date branchCDate) {
		this.branchCDate = branchCDate;
	}
	public String getPbname() {
		return pbname;
	}

	public void setPbname(String pbname) {
		this.pbname = pbname;
	}
	@Override
	public String toString() {
		return "Branch [branch_id=" + branch_id + ", branchName=" + branchName + ", branchPid=" + branchPid
				+ ", branchFlag=" + branchFlag + ", branchCDate=" + branchCDate + ", pbname=" + pbname + "]";
	}
}

