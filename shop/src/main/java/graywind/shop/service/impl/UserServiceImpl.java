package graywind.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graywind.shop.bean.TestBean;
import graywind.shop.bean.User;
import graywind.shop.dao.TestMapper;
import graywind.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private TestMapper testMapper;
    
	@Override
	public boolean hasUser(User user) {
	    List<TestBean> testlist = testMapper.getTest();
	    for(TestBean test : testlist) {
	        System.out.println(test.getId() + test.getTxt());
	    }
	    if(user.getUserName().equals(user.getPassword())){
			return true;
		}
		return false;
	}
	
}
