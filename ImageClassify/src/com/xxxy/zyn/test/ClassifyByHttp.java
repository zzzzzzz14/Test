package com.xxxy.zyn.test;

import com.xxxy.zyn.utils.Base64Util;
import com.xxxy.zyn.utils.FileUtil;
import com.xxxy.zyn.utils.HttpUtil;
import org.junit.Test;

import java.net.URLEncoder;

/**
 * @author zyn
 * @date 2022-06-25-16:26
 */
public class ClassifyByHttp {


    @Test
    public void Test(){
        plant();
    }
    public static String plant() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v1/plant";
        try {
            // 本地文件路径
            String filePath = "image/image.jpg";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.ea351ba763a27783abb3343f310c76cb.2592000.1658738735.282335-26542546";
            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
