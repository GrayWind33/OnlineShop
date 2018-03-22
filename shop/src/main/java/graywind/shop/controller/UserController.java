package graywind.shop.controller;

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
    public String login(User user, Model model) {

        try {
            if (userSvc.hasUser(user)) {
                return "redirect:/";
            } else {
                model.addAttribute("success", false);
                return "static/login";
            }
        } catch (Exception e) {
            System.out.println("登陆失败：" + e);
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
}
