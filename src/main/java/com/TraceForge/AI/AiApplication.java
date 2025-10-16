package com.TraceForge.AI;

import com.TraceForge.AI.Service.GitService;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiApplication {

	public static void main(String[] args) throws GitAPIException {
		SpringApplication.run(AiApplication.class, args);
        GitService gitService = new GitService();
        gitService.oldCommits();
	}

}
