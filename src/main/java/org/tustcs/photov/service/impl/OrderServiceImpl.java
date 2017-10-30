package org.tustcs.photov.service.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tustcs.photov.config.Config;
import org.tustcs.photov.dao.*;
import org.tustcs.photov.dto.OrderList;
import org.tustcs.photov.entity.*;
import org.tustcs.photov.service.OrderService;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Airmacx on 2017/10/17.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderDayMapper orderDayMapper;

    @Resource
    OrderPointMapper orderPointMapper;

    @Resource
    OrderTypeMapper orderTypeMapper;

    @Resource
    StoreMapper storeMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    DetailMapper detailMapper;

    @Override
    public List<Store> getStore() {
        return storeMapper.selectAll();
    }

    @Override
    public List<OrderDay> getOrderDay(int storeId) {
        return orderDayMapper.selectByStore(storeId);
    }


    @Override
    public OrderDay getSingleDay(int orderId) {
        return orderDayMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public OrderPoint getSinglePoint(int pointId) {
        return orderPointMapper.selectByPrimaryKey(pointId);
    }

    @Override
    public Detail getDetail(int userId) {
        List<Detail> details=detailMapper.selectByUser(userId);
        return details.get(0);
    }

    public List<Detail> getDetailList(int userId){
        Date now=new Date();
        List<Detail> details=detailMapper.selectByUser(userId);
        for (Detail d:
             details) {
            if(Long.valueOf(d.getOrderTime())<now.getTime() && d.getStatus()==0){
                d.setStatus(-2);
                detailMapper.updateByPrimaryKeySelective(d);
            }
        }
        return  details;
    }

    @Override
    public List<OrderPoint> getOrderPointList(int orderId) {
        return orderPointMapper.selectByOrderId(orderId);
    }



    @Override
    public boolean addDetail(Detail detail) {
        detail.setStatus(0);
        detail.setCreateTime(String.valueOf(new Date().getTime()));
        return detailMapper.insertSelective(detail)>0;
    }


    @Override
    public boolean updateOrderDay(OrderDay orderDay) {

        return orderDayMapper.updateByPrimaryKeySelective(orderDay)>0;
    }

    @Override
    public boolean updateOrderPoint(OrderPoint orderPoint) {
        return orderPointMapper.updateByPrimaryKeySelective(orderPoint)>0;
    }

    @Override
    public boolean updateDetail(Detail detail) {
        return detailMapper.updateByPrimaryKeySelective(detail)>0;
    }

    @Override
    public OrderList genOrderList(int detailId,int typeId,int storeId,int userId) {
        OrderList orderList=new OrderList();

        OrderType orderType=orderTypeMapper.selectByPrimaryKey(typeId);
        Store store=storeMapper.selectByPrimaryKey(storeId);
        User user=userMapper.selectByPrimaryKey(userId);


            orderList.setCreateTime(String.valueOf(new Date().getTime()));
            orderList.setDetailId(detailId);
            orderList.setEmail(user.getEmail());
            orderList.setTypeName(orderType.getName());
            orderList.setMoney(orderType.getMoney());
            orderList.setPeopleName(user.getRealName());
            orderList.setPhone(user.getPhone());
            orderList.setStoreAddr(store.getAddr());
            orderList.setStoreName(store.getName());
     return orderList;
    }


    @Override
    public boolean addOrderDay() {

        List<Store> stores=storeMapper.selectAll();
        for (Store s :
                stores) {

            //将过期orderDay以及orderPoint置1
            List<OrderDay> orderDays=orderDayMapper.selectByStore(s.getStoreId());
            OrderDay past=orderDays.get(0);
            past.setIsExpire(1);
            if(orderDayMapper.updateByPrimaryKeySelective(past)>0 &&
            orderPointMapper.updateByOrderId(past.getOrderId(),1)>0){

                //插入新的orderDay与orderPoint
                OrderDay orderDay=new OrderDay();
                orderDay.setStoreId(s.getStoreId());
                if(orderDayMapper.insertSelective(orderDay)>0){
                    orderDays=orderDayMapper.selectByStore(s.getStoreId());
                    OrderDay last=orderDays.get(orderDays.size()-1);
                    OrderPoint orderPoint=new OrderPoint();
                    orderPoint.setOrderId(last.getOrderId());
                    for(int i = 0; i< Config.POINT_TOTAL; i++){
                        orderPointMapper.insertSelective(orderPoint);
                    }
                }else {
                    return false;
                }
            }else {
                return false;
            }

        }
        return true;
    }
}
