package graywind.shop.service;

import java.sql.SQLException;

import graywind.shop.bean.SessionData;
import graywind.shop.bean.User;

public interface UserService {
    
    /**
     * 验证登录名与密码
     * @param user
     * @return
     */
    public boolean hasUser(User user);
    
    /**
     * 获取用户详细信息
     * @param username
     * @return
     */
    public User getUser(String username);

    /**
     * 注册用户
     * @param user
     * @throws Exception
     */
    public void registerUser(User user) throws Exception;
    
    /**
     * 获取session信息(未实现)
     * @param session
     * @return
     */
    public SessionData getSession(String session);
    
    /**
     * 减少用户余额
     * @param userId
     * @param amount
     * @throws SQLException
     */
    public void decreaseBalance(long userId, double amount) throws SQLException;
    
    /**
     * 增加用户余额
     * @param userId
     * @param amount
     * @throws SQLException
     */
    public void increaseBanlance(long userId, double amount) throws SQLException;
}
