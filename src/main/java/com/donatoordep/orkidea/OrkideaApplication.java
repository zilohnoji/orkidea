package com.donatoordep.orkidea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement /* Manager for transactions */
@EntityScan(basePackages = { "com.donatoordep.orkidea.entities" }) /* Explicit package of entities (more performance) */
@EnableJpaRepositories(basePackages = { "com.donatoordep.orkidea.repositories" }) /* Explicit package of repositories */
public class OrkideaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrkideaApplication.class, args);
	}

}
