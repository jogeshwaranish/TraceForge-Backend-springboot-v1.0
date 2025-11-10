package com.TraceForge.AI.repo;

import com.TraceForge.AI.model.document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<document,Integer> {
}
