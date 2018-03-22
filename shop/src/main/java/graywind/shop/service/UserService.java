package graywind.shop.service;

import graywind.shop.bean.SessionData;
import graywind.shop.bean.User;

public interface UserService {
    public boolean hasUser(User user);

    public void registerUser(User user) throws Exception;
    
    public SessionData getSession(String session);
}
