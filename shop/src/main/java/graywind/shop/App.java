package graywind.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//Spring Boot 应用的标识
@SpringBootApplication
//mapper 接口类扫描包配置
@EnableTransactionManagement 
@MapperScan("graywind.shop.dao")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
