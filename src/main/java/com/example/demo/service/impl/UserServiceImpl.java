package com.example.demo.service.impl;

import com.example.demo.mapper.master.UserMasterMapper;
import com.example.demo.mapper.slave.UserSlaveMapper;
import com.example.demo.pojo.User;
import com.example.demo.pojo.UserBO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ylx
 * Created by fuzamei on 2018/5/18.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMasterMapper userMasterMapper;

    private final UserSlaveMapper userSlaveMapper;

    @Autowired
    public UserServiceImpl(UserMasterMapper userMasterMapper, UserSlaveMapper userSlaveMapper) {
        this.userMasterMapper = userMasterMapper;
        this.userSlaveMapper = userSlaveMapper;
    }

    @Override
    public List<User> getUserInfo() {
        return userSlaveMapper.getUserInfo();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeInfo(UserBO userBO) {
        int success = userMasterMapper.changeInfo(userBO);
        if(success == 0){
            return false;
        }
        return true;
    }
}
