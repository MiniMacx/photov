package org.tustcs.photov.entity;

public class Detail {
    private Integer id;

    private Integer userId;

    private Integer orderId;

    private Integer pointId;

    private Integer typeId;

    private Integer storeId;

    private Integer status;

    private String orderTime;

    private String createTime;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime == null ? null : orderTime.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Detail(Integer userId, Integer orderId, Integer pointId, Integer typeId, Integer storeId, String orderTime,String createTime, String remark) {
        this.userId = userId;
        this.orderId = orderId;
        this.pointId = pointId;
        this.typeId = typeId;
        this.storeId = storeId;
        this.orderTime = orderTime;
        this.createTime=createTime;
        this.remark = remark;
    }

    public Detail() {
    }
}