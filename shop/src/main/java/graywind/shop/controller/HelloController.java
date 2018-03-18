package graywind.shop.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graywind.shop.bean.HelloBean;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/HelloWorld")
    public String hello() {
        return "Hello World!";
    }
    
    @RequestMapping("/HelloBean")
    public HelloBean hellobean() {
        HelloBean hello = new HelloBean();
        hello.setId("123");
        hello.setPassword("456");
        hello.setName("小明");
    	return hello;
    }
    
    @RequestMapping("/HelloBean/{id}")
    public HelloBean hellobean(@PathVariable("id") String id) {
        HelloBean hello = new HelloBean();
        hello.setId(id);
        hello.setPassword("456");
        hello.setName("小明");
    	return hello;
    }
}
