package graywind.shop.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import graywind.shop.interceptor.Auth;
import graywind.shop.service.CartService;

@Auth
@RestController
@RequestMapping(value = "/cart")
public class CartController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CartService cartSvc;

    @RequestMapping(value = "/add/{commoditId}", method = RequestMethod.GET)
    public String addCart(@PathVariable("commoditId") int commoditId, HttpServletRequest request) {
        long userId = (long) request.getSession().getAttribute("userid");
        String username = (String) request.getSession().getAttribute("username");
        logger.info("add commodit " + commoditId + " to user:" + userId);
        try {
            cartSvc.addCart(userId, commoditId);
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "添加到购物车成功";
    }
    
    @RequestMapping(value = "/delete/{commoditId}", method = RequestMethod.GET)
    public String deleteCart(@PathVariable("commoditId") int commoditId, HttpServletRequest request) {
        long userId = (long) request.getSession().getAttribute("userid");
        String username = (String) request.getSession().getAttribute("username");
        logger.info("delete commodit " + commoditId + " from user:" + userId);
        try {
            cartSvc.deleteCart(userId, commoditId);
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "删除购物车成功";
    }
    
    @RequestMapping(value = "/buy/{commoditId}", method = RequestMethod.GET)
    public String buy(@PathVariable("commoditId") int commoditId, HttpServletRequest request) {
        long userId = (long) request.getSession().getAttribute("userid");
        String username = (String) request.getSession().getAttribute("username");
        logger.info("buy commodit " + commoditId + " from user:" + userId);
        try {
            cartSvc.buy(userId, commoditId);
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "下单成功";
    }
}
