package org.tustcs.photov.dao;

import org.tustcs.photov.entity.OrderPoint;

public interface OrderPointMapper {
    int deleteByPrimaryKey(Integer pointId);

    int insert(OrderPoint record);

    int insertSelective(OrderPoint record);

    OrderPoint selectByPrimaryKey(Integer pointId);

    int updateByPrimaryKeySelective(OrderPoint record);

    int updateByPrimaryKey(OrderPoint record);
}