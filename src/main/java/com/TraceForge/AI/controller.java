package com.TraceForge.AI;


import com.TraceForge.AI.Service.GitService;
import com.TraceForge.AI.Service.repositoryService;
import com.TraceForge.AI.model.document;
import com.fasterxml.jackson.databind.JsonNode;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class controller {

    @Autowired
    private repositoryService repoService;

    @PostMapping("/getMarkDown/")
    public String getMarkDown(@RequestBody JsonNode body) throws GitAPIException, IOException {
        System.out.println(body);
        try {
            String path = body.get("path").asText();
            System.out.println(path);
            System.out.println("body exists and works with file");
            GitService gitService = new GitService(new File(path));
            String output = gitService.GenerateMarkdown();
            repoService.SaveRepo(new document(gitService.getRepoName(),gitService.getFilePath(),output));
            return output;
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("body not working");
        }
//      GitService gitService = new GitService(new File(path));
//      return gitService.printCommits();
        return "";
    }

    @GetMapping("/getMarkDowns")
    public List<document> getMarkDowns() throws GitAPIException, IOException {
        return repoService.getFiles();
    }
}
