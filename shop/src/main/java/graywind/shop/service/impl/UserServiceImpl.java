package graywind.shop.service.impl;

import org.springframework.stereotype.Service;

import graywind.shop.bean.User;
import graywind.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public boolean hasUser(User user) {
		if(user.getUserName().equals(user.getPassword())){
			return true;
		}
		return false;
	}
	
}
