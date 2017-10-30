package org.tustcs.photov.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tustcs.photov.entity.User;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Airmacx on 2017/10/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/*.xml"})
public class UserMapperTest {
    @Resource
    UserMapper userMapper;

    @Test
    public void insertSelective() throws Exception {
        User u=new User();
        u.setNickname("??..");
        u.setSex("男");
        userMapper.insertSelective(u);
        int userId=u.getUserId();
        System.out.println(userId);
    }

}