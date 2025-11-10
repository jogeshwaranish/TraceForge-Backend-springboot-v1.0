package com.TraceForge.AI.Service;

import com.TraceForge.AI.model.document;
import com.TraceForge.AI.repo.repository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class repositoryService {
    @Autowired
    private repository repo;

    public List<document> getFiles() {
        return repo.findAll();
    }

    // add more crud functionality later
}
