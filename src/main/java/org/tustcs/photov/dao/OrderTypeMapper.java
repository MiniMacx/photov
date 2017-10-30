package org.tustcs.photov.dao;

import org.tustcs.photov.entity.OrderType;

public interface OrderTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(OrderType record);

    int insertSelective(OrderType record);

    OrderType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(OrderType record);

    int updateByPrimaryKey(OrderType record);
}