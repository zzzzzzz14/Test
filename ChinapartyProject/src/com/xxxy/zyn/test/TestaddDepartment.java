package com.xxxy.zyn.test;

import com.xxxy.zyn.bean.Department;
import com.xxxy.zyn.dao.DepartmentDao;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.UUID;

/**
 * @author zyn
 * @date 2022-05-26-8:37
 */
public class TestaddDepartment {
    @Test
    public void adddepartment(){
        Department dep = new Department();
        String uuid = UUID.randomUUID().toString().replace("-","");
        dep.setDepartment_id(uuid);
        dep.setDepartmentName("计算机");
        dep.setDepartmentFlag("1");
        dep.setDepartmentPid("7f10de7c5415462f8dda5b2a95ad4268");
        dep.setDepartmentCDate(new Date());
        DepartmentDao dao = new DepartmentDao();
        String flag = dao.addDepartment(dep);
        System.out.println(flag);
    }
//    @Test
//    public void selectdepartment(){
//        List<Department> list;
//        DepartmentDao dao = new DepartmentDao();
//        list=dao.getAllDepartment("");
//        for (Department department : list) {
//            System.out.println(department);
//        }
//    }
    @Test
    public void deleDepartment(){
        DepartmentDao dao = new DepartmentDao();
        dao.deleteDepartment("");

    }
}
