package com.example.demo.service;

import com.example.demo.pojo.User;
import com.example.demo.pojo.UserBO;

import java.util.List;

/**
 * @author ylx
 * Created by fuzamei on 2018/5/18.
 */
public interface UserService {


    /**
     *
     * @return
     */
    List<User> getUserInfo();

    /**
     *
     * @return
     * @param userBO
     */
    boolean changeInfo(UserBO userBO);
}
