package graywind.shop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import graywind.shop.bean.Commodit;
import graywind.shop.interceptor.Auth;
import graywind.shop.service.CommoditService;

@Controller
public class HtmlController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommoditService commoditSvc;

	@GetMapping("/")
	@Auth
	public String index(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize, Model model) {
		logger.info("Get page index/" + pageNum + "/" + pageSize);
		List<Commodit> commoditList = commoditSvc.getCommodit();
		model.addAttribute("commodits", commoditList);
		return "static/index";
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
