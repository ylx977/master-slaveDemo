package com.example.demo.mapper.slave;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @author ylx
 * Created by fuzamei on 2018/5/18.
 */
@Mapper
public interface UserSlaveMapper {

    List<User> getUserInfo();
}
