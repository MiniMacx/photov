package org.tustcs.photov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tustcs.photov.dto.OrderList;
import org.tustcs.photov.entity.Detail;
import org.tustcs.photov.entity.OrderDay;
import org.tustcs.photov.entity.OrderPoint;
import org.tustcs.photov.entity.Store;
import org.tustcs.photov.service.OrderService;
import org.tustcs.photov.utils.Res;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Airmacx on 2017/10/19.
 */
@Controller
@RequestMapping(value = "/order",produces = {"application/json;charset=UTF-8"})
public class OrderController {
    @Resource
    OrderService orderService;

    private  final Logger logger= LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/getStore",method = RequestMethod.POST)
    @ResponseBody
    public Res getStore(){
        Res res=new Res();
        try {
            List<Store> stores=orderService.getStore();
            if(stores.size()<1){
                return res.setStatus(0).setMsg("没有店家！");
            }
            return res.setStatus(1).setData(stores).setMsg("storeInfo");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return res.setMsg("服务器内部错误").setStatus(0);
        }

    }

    @RequestMapping(value = "/getOrderDay",method = RequestMethod.POST)
    @ResponseBody
    public Res getOrderDay(int storeId){
        Res res=new Res();
        try {
            List<OrderDay> orderDays=orderService.getOrderDay(storeId);
            if(orderDays.size()<1){
                return res.setStatus(0).setMsg("预约时间不存在！");

            }
            return res.setStatus(1).setData(orderDays).setMsg("orderDayInfo");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return res.setMsg("服务器内部错误").setStatus(0);
        }


    }

    @RequestMapping(value = "/getOrderPointList",method = RequestMethod.POST)
    @ResponseBody
    public Res getPointList(int orderId){
        Res res=new Res();
        try {
            List<OrderPoint> orderPoints=orderService.getOrderPointList(orderId);
            if(orderPoints.size()<1){
                return res.setStatus(0).setMsg("预约点为空！");
            }
            return res.setMsg("orderPointList").setStatus(1).setData(orderPoints);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return res.setMsg("服务器内部错误").setStatus(0);
        }

    }

    @RequestMapping(value = "/updateOrderStatus",method = RequestMethod.POST)
    @ResponseBody
    public Res updateOrderStatus(int pointId){
        Res res=new Res();
        try {
            OrderPoint orderPoint=orderService.getSinglePoint(pointId);
            orderPoint.setOrdered(1);

            if (orderPoint.getIsExpire()==1){
                return res.setStatus(0).setMsg("已过期!");
            }

            if(orderService.updateOrderPoint(orderPoint)){
                return res.setMsg("success!").setStatus(1);
            }
            return res.setMsg("failed").setStatus(0);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return res.setMsg("服务器内部错误").setStatus(0);
        }

    }

    @RequestMapping(value = "/refresh",method = RequestMethod.POST)
    @ResponseBody
    public Res refresh(long time){
        Res res=new Res();
        try {
            if((time-57600000) % (3600 * 1000 * 24)>=5000){
                return res.setStatus(0).setMsg("未到时间");
            }
            if(orderService.addOrderDay()){
                return res.setStatus(1).setMsg("success");
            }
            return res.setStatus(0).setMsg("failed");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return res.setMsg("服务器内部错误").setStatus(0);
        }

    }


    @RequestMapping(value = "/genDetail",method = RequestMethod.POST)
    @ResponseBody
    public Res genDetail(int userId,int orderId,int pointId,int typeId,int storeId,
                         long orderTime,String remark){
        Res res=new Res();
        try {
            Detail detail=new Detail(userId,orderId,pointId,typeId,storeId,String.valueOf(orderTime),
                    String.valueOf(new Date().getTime()),remark);
            if(orderService.addDetail(detail)){
                OrderList orderList=orderService.genOrderList(detail.getId(),typeId,storeId,userId);
                orderList.setRemark(remark);
                orderList.setOrderTime(String.valueOf(orderTime));
                return res.setStatus(1).setData(orderList).setMsg("orderList");
            }
            return res.setMsg("failed").setStatus(0);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return res.setMsg("服务器内部错误").setStatus(0);
        }

    }


}
