package pl.deepsoft.dmapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "pl.deepsoft.dmapp")
@SpringBootApplication
public class DmappApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmappApplication.class, args);
	}

}
