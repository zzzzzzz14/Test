package com.xxxy.zyn.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zyn
 * @date 2022-05-26-8:15
 */
public class Department implements Serializable {
    private String department_id;
    private String departmentName;
    private String departmentPid;
    private String departmentFlag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date departmentCDate;
    private String pdname;

    public Department(String department_id, String departmentName, String departmentPid, String departmentFlag, Date departmentCDate, String pdname) {
        this.department_id = department_id;
        this.departmentName = departmentName;
        this.departmentPid = departmentPid;
        this.departmentFlag = departmentFlag;
        this.departmentCDate = departmentCDate;
        this.pdname = pdname;
    }

    public Department() {
    }

    public String getPdname() {
        return pdname;
    }

    public void setPdname(String pdname) {
        this.pdname = pdname;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentPid() {
        return departmentPid;
    }

    public void setDepartmentPid(String departmentPid) {
        this.departmentPid = departmentPid;
    }

    public String getDepartmentFlag() {
        return departmentFlag;
    }

    public void setDepartmentFlag(String departmentFlag) {
        this.departmentFlag = departmentFlag;
    }

    public Date getDepartmentCDate() {
        return departmentCDate;
    }

    public void setDepartmentCDate(Date departmentCDate) {
        this.departmentCDate = departmentCDate;
    }

    @Override
    public String toString() {
        return "Department{" +
                "department_id='" + department_id + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", departmentPid='" + departmentPid + '\'' +
                ", departmentFlag='" + departmentFlag + '\'' +
                ", departmentCDate=" + departmentCDate +
                ", pdname='" + pdname + '\'' +
                '}';
    }
}
