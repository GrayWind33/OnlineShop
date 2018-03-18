package graywind.shop.controller;

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
}
