package org.tustcs.photov.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Role;
import org.tustcs.photov.config.Config;
import org.tustcs.photov.dao.UserMapper;
import org.tustcs.photov.dto.WechatUser;
import org.tustcs.photov.entity.Token;
import org.tustcs.photov.entity.User;
import org.tustcs.photov.service.TokenService;
import org.tustcs.photov.service.UserService;
import org.tustcs.photov.utils.AuthUtil;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Airmacx on 2017/10/15.
 */

public class CallBackServlet extends HttpServlet {

    @Resource
    UserService userService;

    @Resource
    UserMapper userMapper;

    @Resource
    TokenService tokenService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code=req.getParameter("code");
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Config.APPID
                +"&secret=" +Config.APPSECRET
                + "&code=" +code
                + "&grant_type=authorization_code";
        JSONObject jsonObject=AuthUtil.doGetJson(url);
        String openid=jsonObject.getString("openid");
        String access_token=jsonObject.getString("access_token");
        String infoUrl="https://api.weixin.qq.com/sns/userinfo?access_token=" +access_token+
                "&openid=" +openid+
                "&lang=zh_CN";
        JSONObject userInfo=AuthUtil.doGetJson(infoUrl);
        System.out.println(userInfo.toJSONString());
        User u=userMapper.selectByOpenid(openid);
        if (u==null){
            u=new User();
            u.setNickname(userInfo.getString("nickname"));
            u.setHeadimgurl(userInfo.getString("headimgurl"));
            u.setSex(userInfo.getString("sex"));
            u.setProvince(userInfo.getString("province"));
            u.setOpenid(userInfo.getString("openid"));

            userService.addUser(u);
            u=userMapper.selectByOpenid(openid);
        }
        Token token=tokenService.createToken(u.getUserId());
        PrintWriter pw=resp.getWriter();

        WechatUser wechatUser=new WechatUser(u.getNickname(),u.getSex(),u.getHeadimgurl(),u.getOpenid(),
                                            u.getProvince(),token.getRecId(),token.getUserId(),token.getToken());

        pw.write(JSON.toJSONString(wechatUser));

        pw.close();
        resp.sendRedirect("网页");
    }
}
