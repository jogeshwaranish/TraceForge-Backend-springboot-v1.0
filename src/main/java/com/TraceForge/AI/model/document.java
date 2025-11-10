package com.TraceForge.AI.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String repoName;
    private String file_path;
    @Lob
    private String markdown;

    public document(String repoName, String filePath, String outputMd) {
        this.repoName = repoName;
        this.file_path = filePath;
        this.markdown = outputMd;
    }

    // getters and setters (Lombok already generates them)
}
