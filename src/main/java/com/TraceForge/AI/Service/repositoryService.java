package com.TraceForge.AI.Service;

import com.TraceForge.AI.model.document;
import com.TraceForge.AI.repo.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class repositoryService {
    @Autowired
    private DocumentRepository repo;

    public String SaveRepo(document generatedFile) {
        repo.save(generatedFile);
        return "Saved the file into repo";
    }

    public List<document> getFiles() {
        return repo.findAll();
    }

    // add more crud functionality later
}
