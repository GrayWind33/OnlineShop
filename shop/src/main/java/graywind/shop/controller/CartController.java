package graywind.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import graywind.shop.interceptor.Auth;
import graywind.shop.service.CartService;

@Auth
@Controller
@RequestMapping(value = "/cart")
public class CartController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CartService cartSvc;

	@RequestMapping(value = "/add/{commoditId}", method = RequestMethod.GET)
	public String addCart(@PathVariable("commoditId") int commoditId,HttpServletRequest request) {
		long userId = (long) request.getSession().getAttribute("userid");
		String username = (String) request.getSession().getAttribute("username");
		logger.info("add commodit " + commoditId + " to user:" + userId);
		cartSvc.addCart(userId, commoditId);
		return "static/success";
	}
}
