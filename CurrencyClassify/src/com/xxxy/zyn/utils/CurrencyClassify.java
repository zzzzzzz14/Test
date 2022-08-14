package com.xxxy.zyn.utils;

import com.baidu.aip.imageclassify.AipImageClassify;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author zyn
 * @date 2022-06-25-17:24
 */
public class CurrencyClassify {
    //设置APPID/AK/SK
    public static final String APP_ID = "26542546";
    public static final String API_KEY = "eD1D2air1eq5oWu4bHPXjIBA";
    public static final String SECRET_KEY = "miAd8y7FBKIKXoNV67afKEbwLXieVzGs";

    public static String currencyClassify(String fname) {
        // 初始化一个AipImageClassify
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
//        System.out.println(fname);
        String path = fname;
        JSONObject res = client.currency(path, new HashMap<String, String>());
        System.out.println(res.toString(2));
        return res.toString();
    }
}
