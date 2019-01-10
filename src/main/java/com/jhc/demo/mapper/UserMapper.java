package com.jhc.demo.mapper;

import com.jhc.demo.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User findByUserName(@Param("username") String username);
}
