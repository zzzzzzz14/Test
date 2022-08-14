package com.xxxy.zyn.test;

import com.xxxy.zyn.bean.Logins;
import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.dao.LoginsDao;
import org.testng.annotations.Test;

import java.util.List;


/**
 * @author zyn
 * @date 2022-06-07-19:10
 */
public class LoginsDaoTest {

    @Test
    public void isSameName() {
    }

//    @Test
//    public void addLogins() {
//        Logins model = new Logins("1","1","1","zz","123456","1",new Date());
//        LoginsDao dao = new LoginsDao();
//        System.out.println(dao.addLogins(model));
//    }

    @Test
    public void deleteLogins() {
        LoginsDao dao = new LoginsDao();
        System.out.println(dao.deleteLogins("1"));
    }

    @Test
    public void findLoginsById() {
        LoginsDao dao = new LoginsDao();
        Logins model = dao.findLoginsById("1");
        System.out.println(model);
    }

//    @Test
//    public void updateLogins() {
//        Logins model = new Logins("1","1","1","wx","00000000","0",new Date());
//        LoginsDao dao = new LoginsDao();
//        String s = dao.updateLogins(model);
//        System.out.println(s);
//    }

    @Test
    public void getAllLoginsByPage() {
        String s="";
        Page page = new Page();
        page.setCurrentPage(1);
        page.setCount(10);
        LoginsDao dao = new LoginsDao();
        List<Logins> list = dao.getAllLoginsByPage(s, page);
        for (Logins logins : list) {
            System.out.println(logins);
        }
    }

    @Test
    public void getCount() {
        LoginsDao dao = new LoginsDao();
        int count = dao.getCount("");
        System.out.println(count);
    }

}