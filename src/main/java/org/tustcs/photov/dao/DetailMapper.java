package org.tustcs.photov.dao;

import org.apache.ibatis.annotations.Param;
import org.tustcs.photov.entity.Detail;

import java.util.List;

public interface DetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Detail record);

    int insertSelective(Detail record);

    Detail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Detail record);

    int updateByPrimaryKey(Detail record);

    List<Detail> selectAll();

    List<Detail> selectByUser(@Param("userId") int userId);
}