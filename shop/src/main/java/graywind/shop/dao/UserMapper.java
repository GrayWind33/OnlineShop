package graywind.shop.dao;

import org.apache.ibatis.annotations.Param;

import graywind.shop.bean.User;

public interface UserMapper {
    public User getUser(@Param("username") String username);
    
    public User getUserById(@Param("id") long id);
    
    public void register(User user);
    
    public void updateBanlance(@Param("id") long id, @Param("balance") double balance);
}
