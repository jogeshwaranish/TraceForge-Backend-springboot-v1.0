package com.TraceForge.AI.Service;

import com.TraceForge.AI.model.document;
import com.TraceForge.AI.repo.DocumentRepository;
import com.google.api.client.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class repositoryService {
    @Autowired
    private DocumentRepository repo;

    public String SaveRepo(document generatedFile) {
        repo.save(generatedFile);
        return "Saved the file into repo";
    }

    public List<document> getFiles() {
        return (List<document>) repo.findAll();
    }

    public String getFile(int id) {
        return repo.findById(id).get().getMarkdown();
    }

    public String getFilePath(String repo_name) {
        Optional<document> doc = repo.findByrepoName(repo_name);
        return doc.get().getFile_path();
    }

    // add more crud functionality later
}
