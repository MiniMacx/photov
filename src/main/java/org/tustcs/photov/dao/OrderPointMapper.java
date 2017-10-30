package org.tustcs.photov.dao;

import org.apache.ibatis.annotations.Param;
import org.tustcs.photov.entity.OrderPoint;

import java.util.List;

public interface OrderPointMapper {
    int deleteByPrimaryKey(Integer pointId);

    int insert(OrderPoint record);

    int insertSelective(OrderPoint record);

    OrderPoint selectByPrimaryKey(Integer pointId);

    int updateByPrimaryKeySelective(OrderPoint record);

    int updateByPrimaryKey(OrderPoint record);

    List<OrderPoint> selectByOrderId(@Param("orderId") int orderId);

    int updateByOrderId(@Param("orderId") int orderId,@Param("isExpire") int isExpire);
}