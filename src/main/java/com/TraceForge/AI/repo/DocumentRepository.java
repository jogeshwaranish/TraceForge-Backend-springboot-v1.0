package com.TraceForge.AI.repo;

import com.TraceForge.AI.model.document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends CrudRepository<document,Integer> {
    Optional<document> findByrepoName(String repoName);
}
