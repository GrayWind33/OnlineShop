package graywind.shop.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import graywind.shop.bean.Cart;
import graywind.shop.bean.Commodit;
import graywind.shop.dao.CartMapper;
import graywind.shop.dao.CommoditMapper;
import graywind.shop.service.CartService;
import graywind.shop.service.TransactionService;
import graywind.shop.service.UserService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CommoditMapper commoditMapper;
    
    @Autowired
    private UserService userSvc;
    
    @Autowired
    private TransactionService transactionSvc;

    @Override
    public void addCart(long userId, long commoditId) throws SQLException {
        Cart cart = cartMapper.getSingleCart(userId, commoditId);
        Commodit commodit = commoditMapper.getSingleCommodit(commoditId);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setVolumn(1);
            cart.setCommoditId(commoditId);
            cartMapper.addCart(cart);
            if (commodit.getVolumn() < 1) {
                throw new SQLException("库存数量不足");
            }
            return;
        }
        if (commodit.getOwnerId() == userId) {
            throw new SQLException("用户与商品所有者不能相同");
        }
        cart.setVolumn(cart.getVolumn() + 1);
        if (commodit.getVolumn() < cart.getVolumn()) {
            throw new SQLException("库存数量不足");
        }
        cartMapper.updateCart(userId, commoditId, cart.getVolumn());
    }

    @Override
    public List<Cart> getCarts(long userId) {
        return cartMapper.getCarts(userId);
    }

    @Override
    public void deleteCart(long userId, long commoditId) throws SQLException {
        cartMapper.deleteCart(userId, commoditId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void buy(long userId, long commoditId) throws SQLException {
        Cart cart = cartMapper.getSingleCart(userId, commoditId);
        Commodit commodit = commoditMapper.getSingleCommodit(commoditId);
        if (cart.getVolumn() > commodit.getVolumn()) {
            throw new SQLException("库存数量不足");
        }
        userSvc.decreaseBalance(userId, commodit.getPrice() * cart.getVolumn());
        if (cart.getVolumn() == commodit.getVolumn()) {
            commoditMapper.delete(commoditId);
        } else {
            commoditMapper.update(commoditId, commodit.getVolumn() - cart.getVolumn());
        }
        cartMapper.deleteCart(userId, commoditId);
        transactionSvc.addTransaction(cart, commodit);
    }

}
