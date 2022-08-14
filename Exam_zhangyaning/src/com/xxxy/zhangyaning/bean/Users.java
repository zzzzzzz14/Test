package com.xxxy.zhangyaning.bean;

import java.io.Serializable;

public class Users implements Serializable{
		private String sname;
		private String snum;
		private String sdept;
		private float yuer;//当前余额
		private float jiner;//当时缴费金额
		public Users() {
			super();
		}
		public Users(String sname, String snum, String sdept, float yuer, float jiner) {
			super();
			this.sname = sname;
			this.snum = snum;
			this.sdept = sdept;
			this.yuer = yuer;
			this.jiner = jiner;
		}
		public String getSname() {
			return sname;
		}
		public void setSname(String sname) {
			this.sname = sname;
		}
		public String getSnum() {
			return snum;
		}
		public void setSnum(String snum) {
			this.snum = snum;
		}
		public String getSdept() {
			return sdept;
		}
		public void setSdept(String sdept) {
			this.sdept = sdept;
		}
		public float getYuer() {
			return yuer;
		}
		public void setYuer(float yuer) {
			this.yuer = yuer;
		}
		public float getJiner() {
			return jiner;
		}
		public void setJiner(float jiner) {
			this.jiner = jiner;
		}
	
		
}
