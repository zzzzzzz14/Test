package com.xxxy.zyn.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable{
	private String post_id;
	private String postName;
	@JSONField(format = "YYYY-MM-dd HH:mm:ss" )
	private Date postCDate;
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Post(String post_id, String postName, Date postCDate) {
		super();
		this.post_id = post_id;
		this.postName = postName;
		this.postCDate = postCDate;
	}
	public String getPost_id() {
		return post_id;
	}
	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public Date getPostCDate() {
		return postCDate;
	}
	public void setPostCDate(Date postCDate) {
		this.postCDate = postCDate;
	}
	@Override
	public String toString() {
		return "Post [post_id=" + post_id + ", postName=" + postName + ", postCDate=" + postCDate + "]";
	}
	
	
}
