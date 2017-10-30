package org.tustcs.photov.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tustcs.photov.service.OrderService;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Airmacx on 2017/10/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/*.xml"})
public class OrderServiceImplTest {

    @Resource
    OrderService orderService;

    @Test
    public void getStore() throws Exception {
        System.out.println(orderService.getStore().get(0));
    }

    @Test
    public void getOrderDay() throws Exception {
    }

    @Test
    public void getSingleDay() throws Exception {
    }

    @Test
    public void getSinglePoint() throws Exception {
    }

    @Test
    public void getDetail() throws Exception {
    }

    @Test
    public void getDetailList() throws Exception {
    }

    @Test
    public void getOrderPointList() throws Exception {
    }

    @Test
    public void addDetail() throws Exception {
    }

    @Test
    public void updateOrderDay() throws Exception {
    }

    @Test
    public void updateOrderPoint() throws Exception {
    }

    @Test
    public void updateDetail() throws Exception {
    }

    @Test
    public void genOrderList() throws Exception {
    }

    @Test
    public void addOrderDay() throws Exception {
        orderService.addOrderDay();
    }

}