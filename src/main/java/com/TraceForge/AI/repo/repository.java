package com.TraceForge.AI.repo;

import com.TraceForge.AI.model.document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface repository extends JpaRepository<document,Long> {
}
