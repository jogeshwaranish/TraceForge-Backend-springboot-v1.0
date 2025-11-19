package com.TraceForge.AI;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
@EnableJpaRepositories(basePackages = "com.TraceForge.AI.repo")
@EntityScan(basePackages = "com.TraceForge.AI.model")
@SpringBootApplication
public class AiApplication {

	public static void main(String[] args) throws GitAPIException, IOException {
		SpringApplication.run(AiApplication.class, args);
	}

}
