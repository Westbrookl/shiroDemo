package com.jhc.demo.service;

import com.jhc.demo.mapper.UserMapper;
import com.jhc.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jhc on 2019/1/9
 */
@Service
public class UserServiceImpl implements  UserService {

    @Resource
    private UserMapper userMapper;

   public  User findByUserName(String usermame){
        return userMapper.findByUserName(usermame);
    }
}
