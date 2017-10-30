package org.tustcs.photov.servlet;



import org.tustcs.photov.config.Config;
import org.tustcs.photov.utils.AuthUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by Airmacx on 2017/10/15.
 */

public class LoginServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redircetUrl="http://"+Config.REDIRECT_URL+"/weixin?action=wxlogin";
        String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Config.APPID
                +"&redirect_uri=" + URLEncoder.encode(redircetUrl)
                +"&response_type=code" +
                "&scope=" +"snsapi_userinfo"
                +"&state=STATE" +
                "#wechat_redirect";
        resp.sendRedirect(url);
        System.out.println("over1");
    }
}
