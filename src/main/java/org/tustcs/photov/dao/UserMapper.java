package org.tustcs.photov.dao;

import org.apache.ibatis.annotations.Param;
import org.tustcs.photov.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByOpenid(@Param("openid") String openid);
}