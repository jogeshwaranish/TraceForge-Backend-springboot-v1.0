package com.TraceForge.AI.repo;

import com.TraceForge.AI.model.document;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repository extends JpaRepository<document,Integer> {
}
