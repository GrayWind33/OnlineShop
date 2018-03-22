package graywind.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graywind.shop.bean.User;
import graywind.shop.dao.UserMapper;
import graywind.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean hasUser(User user) {
        User trueUser = userMapper.getUser(user.getUsername());
        if (trueUser.getPassword().equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public void registerUser(User user) throws Exception {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            throw new Exception("输出用户名或密码为空!");
        }
        User trueUser = userMapper.getUser(user.getUsername());
        if (trueUser != null) {
            throw new Exception("该用户名已存在!");
        }
        userMapper.register(user);
    }
}
