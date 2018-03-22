package graywind.shop.dao;

import org.apache.ibatis.annotations.Param;

import graywind.shop.bean.User;

public interface UserMapper {
    public User getUser(@Param("username") String username);
    
    public void register(User user);
}
