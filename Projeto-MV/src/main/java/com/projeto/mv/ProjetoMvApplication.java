package com.projeto.mv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan({"com.projeto.mv"})
@EnableJpaRepositories({"com.projeto.mv"})
@ComponentScan(basePackages = {"com.projeto.mv"})
@SpringBootApplication(scanBasePackages = "com.projeto.mv")
public class ProjetoMvApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoMvApplication.class, args);
	}

}
