package graywind.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import graywind.shop.interceptor.Auth;

@Controller
public class HtmlController {
    @GetMapping("/")
    @Auth
    public String index(Model model) {
        return "static/index";
    }
    
    @GetMapping("/greeting")
    @Auth
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
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
