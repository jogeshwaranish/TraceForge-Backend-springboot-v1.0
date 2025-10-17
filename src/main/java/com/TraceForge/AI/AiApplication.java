package com.TraceForge.AI;

import com.TraceForge.AI.Service.GitService;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class AiApplication {

	public static void main(String[] args) throws GitAPIException, IOException {
//		SpringApplication.run(AiApplication.class, args);
        GitService gitService = new GitService(new File("/home/anishbellamkonda/Downloads/Expense_tracker/.git"));
        gitService.printCommits();
	}

}
