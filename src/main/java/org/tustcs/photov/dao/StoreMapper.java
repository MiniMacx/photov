package org.tustcs.photov.dao;

import org.tustcs.photov.entity.Store;

import java.util.List;

public interface StoreMapper {
    int deleteByPrimaryKey(Integer storeId);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(Integer storeId);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);

    List<Store> selectAll();
}