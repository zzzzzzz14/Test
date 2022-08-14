package com.xxxy.zyn.bean;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{
	private int currentPage;//当前页
	private int totalPage;//共有多少页
	private int count;//一共有多少条数据
	private int totalCount;//本次共取到多少条数据
	private List pageRes;
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Page(int currentPage, int totalPage, int count, int totalCount, List pageRes) {
		super();
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.count = count;
		this.totalCount = totalCount;
		this.pageRes = pageRes;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List getPageRes() {
		return pageRes;
	}
	public void setPageRes(List pageRes) {
		this.pageRes = pageRes;
	}
	

}
