package com.jhc.demo.service;

import com.jhc.demo.mapper.UserMapper;
import com.jhc.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jhc on 2019/1/9
 */

public interface UserService {
    User findByUserName(String username);
}
