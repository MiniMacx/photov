package org.tustcs.photov.dao;

import org.apache.ibatis.annotations.Param;
import org.tustcs.photov.entity.OrderDay;

import java.util.List;

public interface OrderDayMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(OrderDay record);

    int insertSelective(OrderDay record);

    OrderDay selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(OrderDay record);

    int updateByPrimaryKey(OrderDay record);

    List<OrderDay>  selectByStore(int storeId);

}