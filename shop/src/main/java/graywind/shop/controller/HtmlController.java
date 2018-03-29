package graywind.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import graywind.shop.bean.Cart;
import graywind.shop.bean.Commodit;
import graywind.shop.bean.Transaction;
import graywind.shop.bean.User;
import graywind.shop.interceptor.Auth;
import graywind.shop.service.CartService;
import graywind.shop.service.CommoditService;
import graywind.shop.service.TransactionService;
import graywind.shop.service.UserService;

@Controller
public class HtmlController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommoditService commoditSvc;

    @Autowired
    private CartService cartSvc;

    @Autowired
    private UserService userSvc;
    
    @Autowired
    private TransactionService transactionSvc;
    
    @GetMapping("/")
    @Auth
    public String index(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize, Model model,
            HttpServletRequest request) {
        logger.info("Get page index/" + pageNum + "/" + pageSize);
        long userId = (long) request.getSession().getAttribute("userid");
        String username = (String) request.getSession().getAttribute("username");
        User user = userSvc.getUser(username);
        model.addAttribute("balance", user.getBalance());
        List<Commodit> commoditList = commoditSvc.getCommodit(userId);
        model.addAttribute("commodits", commoditList);
        return "static/index";
    }

    @GetMapping("/cart")
    @Auth
    public String cart(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize, Model model,
            HttpServletRequest request) {
        long userId = (long) request.getSession().getAttribute("userid");
        logger.info("用户" + userId + " 查看了购物车");
        List<Cart> carts = cartSvc.getCarts(userId);
        model.addAttribute("carts", carts);
        return "static/cart";
    }
    
    @GetMapping("/transaction")
    @Auth
    public String transaction(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize, Model model,
            HttpServletRequest request) {
        long userId = (long) request.getSession().getAttribute("userid");
        logger.info("用户" + userId + " 查看了订单");
        model.addAttribute("buys", transactionSvc.getBuy(userId));
        model.addAttribute("sells", transactionSvc.getSell(userId));
        return "static/transaction";
    }

    @GetMapping("/greeting")
    @Auth
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {
        model.addAttribute("name", name);
        return "static/greeting";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("success", true);
        return "static/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "static/register";
    }
}
