package com.xxxy.zyn.test;

import com.xxxy.zyn.bean.User;
import com.xxxy.zyn.dao.Ordersdao;
import com.xxxy.zyn.dao.Userdao;
import org.junit.Test;

/**
 * @author zyn
 * @date 2022-06-03-18:01
 */
public class TestUser {
    @Test
    public void Testfind(){
        User user = new User();
        user.setUsername("zz");
        Userdao dao = new Userdao();
        System.out.println(dao.findusername(user));
    }
    @Test
    public void Testadd(){
        User user = new User();
        user.setUsername("zz");
        user.setUserpass("123456");
        user.setPhone("15047802283");
        Userdao userdao = new Userdao();
        userdao.addUser(user);
    }
    @Test
    public  void Testlogin(){
        User user = new User();
        user.setUsername("z");
        user.setUserpass("12345");
        Userdao dao = new Userdao();
        System.out.println(dao.login(user));
    }
    @Test
    public void Testorderhistory(){
        Ordersdao dao = new Ordersdao();
        String wx = dao.orderhistory("wx");
        System.out.println(wx);
    }
}
