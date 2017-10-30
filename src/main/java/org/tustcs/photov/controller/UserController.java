package org.tustcs.photov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tustcs.photov.dto.UserInfo;
import org.tustcs.photov.entity.Detail;
import org.tustcs.photov.entity.User;
import org.tustcs.photov.service.UserService;
import org.tustcs.photov.utils.Res;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by Airmacx on 2017/10/18.
 */
@Controller
@RequestMapping(value = "/user",produces = {"application/json;charset=UTF-8"})
public class UserController {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    UserService userService;

    @RequestMapping(value = "/getInfo",method = RequestMethod.POST)
    @ResponseBody
    public Res getInfo(int userId){
        Res res=new Res();
        try {
            User u=userService.getInfo(userId);
            if(u !=null){
                UserInfo userInfo=new UserInfo();
                userInfo.setAddr(u.getAddr());
                userInfo.setEmail(u.getEmail());
                userInfo.setPhone(u.getPhone());
                userInfo.setRealName(u.getRealName());
                return res.setStatus(1).setMsg("userInfo").setData(userInfo);
            }

            return res.setMsg("用户不存在").setStatus(0);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return res.setMsg("服务器内部错误").setStatus(0);
        }

    }


    @RequestMapping(value = "/getWechatUser",method = RequestMethod.POST)
    @ResponseBody
    public Res getWechatUser(){
    return null;
    }

    @RequestMapping(value = "/getDetailList",method = RequestMethod.POST)
    @ResponseBody
    public Res getDetailList(int userId){
        Res res=new Res();
        List<Detail> details=userService.getOrderList(userId);
        if(details != null){
            return res.setStatus(1).setMsg("detailList").setData(details);
        }
        return res.setStatus(0).setMsg("用户不存在");
    }

    @RequestMapping(value = "/editInfo",method = RequestMethod.POST)
    @ResponseBody
    public Res editInfo(int userId,String realName,String addr,String phone,String email){
        Res res=new Res();
        try {
            User user=userService.getInfo(userId);
            if(user!=null){
                if(phone.length()>11){
                    return res.setStatus(0).setMsg("手机号格式不正确");
                }
                user.setAddr(addr);
                user.setRealName(realName);
                user.setPhone(phone);
                user.setEmail(email);
                if (userService.editUserInfo(user)){
                    return res.setStatus(1).setMsg("success!");
                }

            }
            return res.setStatus(0).setMsg("用户不存在");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return res.setMsg("服务器内部错误").setStatus(0);
        }


    }



}
