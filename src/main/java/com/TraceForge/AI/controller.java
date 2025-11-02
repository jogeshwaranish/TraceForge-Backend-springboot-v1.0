package com.TraceForge.AI;


import com.TraceForge.AI.Service.GitService;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class controller {

    @GetMapping("/getMarkDown/")
    public String getMarkDown() throws GitAPIException, IOException {
        GitService gitService = new GitService(new File("/home/anishbellamkonda/Downloads/Expense_tracker/.git"));
        return gitService.printCommits();
    }
}
