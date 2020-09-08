package c10y.camel;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"c10y.*"})
@EntityScan("c10y.*")
//@EnableJpaRepositories("c10y.*") for JPA
public class ScratchTestApp {

	@Autowired
	CamelContext camelContext;

	public static void main(String[] args) {
		SpringApplication.run(ScratchTestApp.class, args);
    }
}