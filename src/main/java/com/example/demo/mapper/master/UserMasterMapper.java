package com.example.demo.mapper.master;

import com.example.demo.pojo.UserBO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ylx
 * Created by fuzamei on 2018/5/18.
 */
@Mapper
public interface UserMasterMapper {

    int changeInfo(UserBO userBO);
}
