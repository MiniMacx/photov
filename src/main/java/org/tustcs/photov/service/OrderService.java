package org.tustcs.photov.service;

import org.tustcs.photov.dto.OrderList;
import org.tustcs.photov.entity.*;

import java.util.List;

/**
 * Created by Airmacx on 2017/10/17.
 */
public interface OrderService {

    List<Store> getStore();

    List<OrderDay> getOrderDay(int storeId);


    OrderDay getSingleDay(int orderId);

    Detail getDetail(int userId);

    List<Detail> getDetailList(int userId);

    List<OrderPoint> getOrderPointList(int orderId);

    boolean updateOrderDay(OrderDay orderDay);

    boolean updateOrderPoint(OrderPoint orderPoint);

    boolean addDetail(Detail detail);

    boolean updateDetail(Detail detail);

    OrderList genOrderList(int detailId,int typeId,int storeId,int userId);

    boolean addOrderDay();

    OrderPoint getSinglePoint(int pointId);
}
