package graywind.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import graywind.shop.bean.User;
import graywind.shop.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userSvc;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, HttpServletRequest request, Model model) {
        try {
            if (userSvc.hasUser(user)) {
                user = userSvc.getUser(user.getUsername());
                HttpSession session = request.getSession();
            	session.setAttribute("username",user.getUsername());
            	session.setAttribute("userid",user.getId());
                return "redirect:/";
            } else {
                model.addAttribute("success", false);
                return "static/login";
            }
        } catch (Exception e) {
            logger.error("登陆失败：" + e.getMessage());
        }
        model.addAttribute("success", false);
        return "static/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user, Model model) {
        logger.info("注册用户名:" + user.getUsername() + ", 密码:" + user.getPassword());
        try {
            userSvc.registerUser(user);
            model.addAttribute("info", "注册用户" + user.getUsername() + "成功!");
            return "static/login";
        } catch (Exception e) {
            logger.error("注册失败：" + e.getMessage());
            model.addAttribute("success", false);
            model.addAttribute("info", e.getMessage());
        }
        return "static/register";
    }
    
    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    public String register(double amount, Model model,HttpServletRequest request) {
        long userId = (long) request.getSession().getAttribute("userid");
        logger.info("用户: " + userId + " 充值" + amount);
        try {
            userSvc.increaseBanlance(userId, amount);
            model.addAttribute("info", "充值成功");
        } catch (Exception e) {
            logger.error("充值失败：" + e.getMessage());
            model.addAttribute("info", "充值失败：" + e.getMessage());
        }
        return "static/result";
    }
}
