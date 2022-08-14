package com.xxxy.zyn.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class Questions implements Serializable {
	private String questions_id ; 
	private String theme_id ; 
	private String quesTitle ;
	private String quesA ; 
	private String quesB ;
	private String quesC ; 
	private String quesD ; 
	private String quesScore ; 
	private String quesStand ;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date quesCDate ;
	private String quesFlag ;
	private String quesStmp ;
	private String themeTitle;
	
	public Questions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Questions(String questions_id, String theme_id, String quesTitle, String quesA, String quesB, String quesC, String quesD, String quesScore, String quesStand, Date quesCDate, String quesFlag, String quesStmp, String themeTitle) {
		this.questions_id = questions_id;
		this.theme_id = theme_id;
		this.quesTitle = quesTitle;
		this.quesA = quesA;
		this.quesB = quesB;
		this.quesC = quesC;
		this.quesD = quesD;
		this.quesScore = quesScore;
		this.quesStand = quesStand;
		this.quesCDate = quesCDate;
		this.quesFlag = quesFlag;
		this.quesStmp = quesStmp;
		this.themeTitle = themeTitle;
	}

	public String getThemeTitle() {
		return themeTitle;
	}

	public void setThemeTitle(String themeTitle) {
		this.themeTitle = themeTitle;
	}

	public String getQuestions_id() {
		return questions_id;
	}

	public void setQuestions_id(String questions_id) {
		this.questions_id = questions_id;
	}

	public String getTheme_id() {
		return theme_id;
	}

	public void setTheme_id(String theme_id) {
		this.theme_id = theme_id;
	}

	public String getQuesTitle() {
		return quesTitle;
	}

	public void setQuesTitle(String quesTitle) {
		this.quesTitle = quesTitle;
	}

	public String getQuesA() {
		return quesA;
	}

	public void setQuesA(String quesA) {
		this.quesA = quesA;
	}

	public String getQuesB() {
		return quesB;
	}

	public void setQuesB(String quesB) {
		this.quesB = quesB;
	}

	public String getQuesC() {
		return quesC;
	}

	public void setQuesC(String quesC) {
		this.quesC = quesC;
	}

	public String getQuesD() {
		return quesD;
	}

	public void setQuesD(String quesD) {
		this.quesD = quesD;
	}

	public String getQuesScore() {
		return quesScore;
	}

	public void setQuesScore(String quesScore) {
		this.quesScore = quesScore;
	}

	public String getQuesStand() {
		return quesStand;
	}

	public void setQuesStand(String quesStand) {
		this.quesStand = quesStand;
	}

	public Date getQuesCDate() {
		return quesCDate;
	}

	public void setQuesCDate(Date quesCDate) {
		this.quesCDate = quesCDate;
	}

	public String getQuesFlag() {
		return quesFlag;
	}

	public void setQuesFlag(String quesFlag) {
		this.quesFlag = quesFlag;
	}

	public String getQuesStmp() {
		return quesStmp;
	}

	public void setQuesStmp(String quesStmp) {
		this.quesStmp = quesStmp;
	}

	@Override
	public String toString() {
		return "Questions{" +
				"questions_id='" + questions_id + '\'' +
				", theme_id='" + theme_id + '\'' +
				", quesTitle='" + quesTitle + '\'' +
				", quesA='" + quesA + '\'' +
				", quesB='" + quesB + '\'' +
				", quesC='" + quesC + '\'' +
				", quesD='" + quesD + '\'' +
				", quesScore='" + quesScore + '\'' +
				", quesStand='" + quesStand + '\'' +
				", quesCDate=" + quesCDate +
				", quesFlag='" + quesFlag + '\'' +
				", quesStmp='" + quesStmp + '\'' +
				", themeTitle='" + themeTitle + '\'' +
				'}';
	}
}
