package c10y.camel;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"c10y.*"})	// same as @Configuration @EnableAutoConfiguration @ComponentScan 
public class ScratchTestApp {

	@Autowired
	CamelContext camelContext;

	public static void main(String[] args) {
		SpringApplication.run(ScratchTestApp.class, args);
    }
}