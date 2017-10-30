package org.tustcs.photov.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tustcs.photov.dao.DetailMapper;
import org.tustcs.photov.dao.UserMapper;
import org.tustcs.photov.entity.Detail;
import org.tustcs.photov.entity.User;
import org.tustcs.photov.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Airmacx on 2017/10/17.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    DetailMapper detailMapper;

    @Override
    public List<Detail> getOrderList(int userId) {
        return userMapper.selectByPrimaryKey(userId) != null?
                detailMapper.selectByUser(userId) : null;
    }

    @Override
    public User getInfo(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public boolean editUserInfo(User user) {
        return userMapper.updateByPrimaryKeySelective(user)>0;
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.insertSelective(user)>0;
    }
}
