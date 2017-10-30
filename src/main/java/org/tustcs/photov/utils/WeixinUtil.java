package org.tustcs.photov.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.tustcs.photov.config.Config;
import org.tustcs.photov.entity.Button;
import org.tustcs.photov.entity.ButtonClick;
import org.tustcs.photov.entity.ButtonView;
import org.tustcs.photov.entity.Menu;

import java.io.IOException;

/**
 * Created by Airmacx on 2017/10/23.
 */
public class WeixinUtil {

    private static final String ACCESS_TOKEN="https://api.weixin.qq.com/cgi-bin/token";
    private static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ Config.APPID+"&secret="+Config.APPSECRET;
    private static final String MENU_URL= "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    private  static String accessToken;

    public static String getAccessToken() throws IOException {
        JSONObject jsonObject  = AuthUtil.doGetJson(ACCESS_TOKEN_URL);
        accessToken=jsonObject.getString("access_token");
        return accessToken;
    }

    public static int initMenu(String token) throws IOException {
        int result=0;
        String url=MENU_URL.replace("ACCESS_TOKEN",token);


        //设置菜单
        Menu m=new Menu();

        ButtonClick button1=new ButtonClick();
        button1.setKey("key_1201");
        button1.setName("一个按钮");

        ButtonView button11=new ButtonView();
        button11.setName("test");
        button11.setType("view");
        button11.setUrl(Config.TEST_PAGE_URL);

        ButtonClick button12=new ButtonClick();
        button12.setName("扫aa码");
        button12.setType("scancode_push");
        button12.setKey("key_1001");

        button1.setSub_button(new Button[]{button11,button12});
        m.setButton(new Button[]{button1});


        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"), JSON.toJSONString(m));

        String res=HttpsPost.post(url,requestBody);
        JSONObject jsonObject=JSON.parseObject(res);

        if(res!=null){
            result=jsonObject.getInteger("errcode");
        }
        return result;
    }
}
