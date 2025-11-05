package com.TraceForge.AI;


import com.TraceForge.AI.Service.GitService;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.api.client.json.Json;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

@RestController
public class controller {

    @PostMapping("/getMarkDown/")
    public String getMarkDown(@RequestBody JsonNode body) throws GitAPIException, IOException {
        System.out.println(body);
        try {
            String path = body.get("path").asText();
            System.out.println(path);
            System.out.println("body exists and works with file");
            GitService gitService = new GitService(new File(path));
            return gitService.printCommits();
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("body not working");
        }
//      GitService gitService = new GitService(new File(path));
//      return gitService.printCommits();
        return "";
    }
}
