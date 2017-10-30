package org.tustcs.photov.config;

import com.alibaba.fastjson.JSONObject;
import org.tustcs.photov.entity.*;
import org.tustcs.photov.servlet.WechatServlet;
import org.tustcs.photov.utils.WeixinUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Airmacx on 2017/10/18.
 */
public class TestOrder extends Thread{

    private static long orderNum = 0l;
    private static String date ;

    public static void main(String[] args) throws InterruptedException, IOException {
//        for (int i = 0; i < 10000; i++) {
//            System.out.println(TestOrder.getOrderNo());
//            Thread.sleep(1000);
//        }



        String accessToken= WeixinUtil.getAccessToken();
        int result=WeixinUtil.initMenu(accessToken);
        if(result==0){
            System.out.println("success");
        }
        System.out.println(result);
    }

    /**
     * 生成订单编号
     * @return
     */
    public static synchronized String getOrderNo() {
        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        if(date==null||!date.equals(str)){
            date = str;
            orderNum  = 0l;
        }
        orderNum ++;
        long orderNo = Long.parseLong((date));
        orderNo += orderNum;;
        return orderNo+"";
    }
}
