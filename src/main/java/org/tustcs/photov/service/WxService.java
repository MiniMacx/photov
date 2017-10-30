package org.tustcs.photov.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.tustcs.photov.config.Config;
import org.tustcs.photov.dao.UserMapper;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.tustcs.photov.entity.Token;
import org.tustcs.photov.entity.User;
import org.tustcs.photov.service.TokenService;
import org.tustcs.photov.utils.HttpsPost;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * Created by Airmacx on 2017/10/22.
 */


public class WxService extends HttpServlet {

    @Autowired
    UserMapper userMapper;

    @Autowired
    TokenService tokenService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        System.out.println("over2");
        String action=req.getParameter("action");
        if(action==null){
            setAct(resp,  new JSONObject().fluentPut("actionData","null action")
                                        .fluentPut("status",0));
            return;
        }else {
            switch (action) {
                case "wxlogin":
                    wxlogin(req,resp);
                default:
                    setAct(resp,  new JSONObject().fluentPut("actionData","null action")
                            .fluentPut("status",0));
            }
        }
    }



    public static void setAct(HttpServletResponse resp, JSONObject o) {
        try {
            resp.setContentType("text/json;charset=UTF-8");
            resp.setHeader("Access-Control-Allow-Origin", "*");
            resp.setStatus(200);
            PrintWriter out = resp.getWriter();
            out.print(o);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setErr(HttpServletResponse resp, Exception e) {
        try {
            resp.setHeader("Access-Control-Allow-Origin", "*");
            resp.setStatus(500);
            PrintWriter out = resp.getWriter();
            // out.print("error 500");
            e.printStackTrace(out);
            out.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }


    private void wxlogin(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            String code= req.getParameter("code");

            if(code==null || code.length()<=0){
                setAct(resp,new JSONObject().fluentPut("status",-1).fluentPut("msg","code cannot be null"));
                return;
            }


            RequestBody params1= new FormBody.Builder()
                    .add("appid", Config.APPID)
                    .add("secret",Config.APPSECRET)
                    .add("code",code)
                    .add("grant_type","authorization_code")
                    .build();

            String response1 = HttpsPost.post("https://api.weixin.qq.com/sns/oauth2/access_token", params1);

            JSONObject accessTokenRe= JSON.parseObject(response1);



            String openid=accessTokenRe.getString("openid");
            System.out.println(openid);
            String accessToken=accessTokenRe.getString("access_token");


            RequestBody params2=new FormBody.Builder()
                    .add("access_token",accessToken)
                    .add("openid",openid)
                    .build();

            String response2=HttpsPost.post("https://api.weixin.qq.com/sns/userinfo?access_token=",params2);

            JSONObject userInfoRe=JSON.parseObject(response2);

            User u=new User();

            u.setOpenid(userInfoRe.getString("openid"));
            u.setHeadimgurl(userInfoRe.getString("headimgurl"));
            u.setProvince(userInfoRe.getString("province"));
            u.setNickname(userInfoRe.getString("nickname"));
            u.setSex(userInfoRe.getString("sex"));

            User u1=userMapper.selectByOpenid(openid);
            Token userToken=null;

            if(u1==null){
                userMapper.insertSelective(u);
                userToken = tokenService.createToken(u.getUserId());
            }else {
                u.setUserId(u1.getUserId());
                userMapper.updateByPrimaryKeySelective(u);
                userToken = tokenService.createToken(u.getUserId());
            }
            JSONObject resBody=new JSONObject();
            resBody.fluentPut("userId",u.getUserId())
                    .fluentPut("nickname",u.getNickname())
                    .fluentPut("sex",u.getSex())
                    .fluentPut("headimgurl",u.getHeadimgurl())
                    .fluentPut("openid",u.getOpenid())
                    .fluentPut("province",u.getProvince())
                    .fluentPut("recId",userToken.getRecId())
                    .fluentPut("token",userToken.getToken());

            setAct(resp,resBody);
            return;
        }catch (Exception e){
            e.printStackTrace();
            setErr(resp,e);
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
}
