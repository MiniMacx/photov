package org.tustcs.photov.dao;

import org.tustcs.photov.entity.OrderDay;

public interface OrderDayMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(OrderDay record);

    int insertSelective(OrderDay record);

    OrderDay selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(OrderDay record);

    int updateByPrimaryKey(OrderDay record);
}