package com.xxxy.zyn.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class Newstype implements Serializable {
	private String newstype_id;
	private String newstypeName;
	private String newstypeFlag;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date newsstypeCDate;

	public Newstype() {
		super();
	}

	public Newstype(String newstype_id, String newstypeName, String newstypeFlag, Date newsstypeCDate) {
		super();
		this.newstype_id = newstype_id;
		this.newstypeName = newstypeName;
		this.newstypeFlag = newstypeFlag;
		this.newsstypeCDate = newsstypeCDate;
	}

	public String getNewstype_id() {
		return newstype_id;
	}

	public void setNewstype_id(String newstype_id) {
		this.newstype_id = newstype_id;
	}

	public String getNewstypeName() {
		return newstypeName;
	}

	public void setNewstypeName(String newstypeName) {
		this.newstypeName = newstypeName;
	}

	public String getNewstypeFlag() {
		return newstypeFlag;
	}

	public void setNewstypeFlag(String newstypeFlag) {
		this.newstypeFlag = newstypeFlag;
	}

	public Date getNewsstypeCDate() {
		return newsstypeCDate;
	}

	public void setNewsstypeCDate(Date newsstypeCDate) {
		this.newsstypeCDate = newsstypeCDate;
	}

	@Override
	public String toString() {
		return "Newstype [newstype_id=" + newstype_id + ", newstypeName=" + newstypeName + ", newstypeFlag="
				+ newstypeFlag + ", newsstypeCDate=" + newsstypeCDate + "]";
	}

}
