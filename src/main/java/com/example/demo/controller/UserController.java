package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.pojo.UserBO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ylx
 * Created by fuzamei on 2018/5/18.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getInfo")
    public List<User> getUserInfo(){
        List<User> users = userService.getUserInfo();
        return users;
    }

    @RequestMapping("/changeInfo")
    public String changeInfo(@RequestBody UserBO userBO){
        boolean flag = userService.changeInfo(userBO);
        if(flag){
            return "success";
        }
        return "fail";
    }

}
