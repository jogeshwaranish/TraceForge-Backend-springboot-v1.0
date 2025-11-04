package com.TraceForge.AI;


import com.TraceForge.AI.Service.GitService;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
public class controller {

    @PostMapping("/getMarkDown/")
    public String getMarkDown(@RequestBody String body) throws GitAPIException, IOException {
        System.out.println(body);
        try {
            File p1 = new File(body);
            System.out.println("body exists and works with file");
            GitService gitService = new GitService(new File(body));
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
