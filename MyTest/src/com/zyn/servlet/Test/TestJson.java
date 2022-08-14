package com.zyn.servlet.Test;

import com.alibaba.fastjson.JSONObject;
import com.zyn.bean.Page;
import com.zyn.dao.Goodsdao;
import com.zyn.dao.getJson;
import org.junit.Test;

/**
 * @author zyn
 * @date 2022-05-18-11:17
 */
public class TestJson {
    @Test
    public void Test(){
        getJson getJson = new getJson();
        getJson.select();
    }
    @Test
    public  void Test1(){
        getJson getJson = new getJson();
        getJson.select_();
    }
    @Test
    public  void TestGetCount(){
        getJson getJson = new getJson();
        System.out.println(getJson.getCount());
    }
    @Test
    public void TestPage(){
        getJson getJson = new getJson();
        Page page = new Page();
        Object count = getJson.getCount();
        page.setTotalCount(28);
        page.setCurrentPage(2);
        page.setCount(5);
        JSONObject jsonObject = getJson.Page(page);
        System.out.println(jsonObject);
    }
    @Test
    public void Testreduce(){
        Goodsdao dao = new Goodsdao();
        System.out.println(dao.reduce("2"));
    }
}
