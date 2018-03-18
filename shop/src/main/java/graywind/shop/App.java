package graywind.shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	// 指定下bean的名称
	@Bean(name = "hello")
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				System.out.println("Let's inspect the beans provided by Spring Boot:");
				String[] beanNames = ctx.getBeanDefinitionNames();
				for (String beanName : beanNames) {
					System.out.println(beanName);
				}
			}
		};
	}
}
