package org.tustcs.photov.service;

import org.tustcs.photov.entity.Detail;
import org.tustcs.photov.entity.User;

import java.util.List;

/**
 * Created by Airmacx on 2017/10/17.
 */
public interface UserService {

    List<Detail> getOrderList(int userId);

    User getInfo(int userId);

    boolean editUserInfo(User user);

    boolean addUser(User user);
}
