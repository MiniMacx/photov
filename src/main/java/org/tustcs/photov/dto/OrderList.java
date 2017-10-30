package org.tustcs.photov.dto;

import org.tustcs.photov.dao.DetailMapper;
import org.tustcs.photov.dao.OrderTypeMapper;
import org.tustcs.photov.dao.StoreMapper;
import org.tustcs.photov.dao.UserMapper;
import org.tustcs.photov.entity.Detail;
import org.tustcs.photov.entity.OrderType;
import org.tustcs.photov.entity.Store;
import org.tustcs.photov.entity.User;

import javax.annotation.Resource;

/**
 * Created by Airmacx on 2017/10/18.
 */
public class OrderList {



    private int detailId;

    private String typeName;

    private float money;


    private String storeName;

    private String storeAddr;

    private String peopleName;

    private String phone;

    private String email;

    private String remark;

    private String createTime;

    private String orderTime;


    public OrderList() {
    }


    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddr() {
        return storeAddr;
    }

    public void setStoreAddr(String storeAddr) {
        this.storeAddr = storeAddr;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
