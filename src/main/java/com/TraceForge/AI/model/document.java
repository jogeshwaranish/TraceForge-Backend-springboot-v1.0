package com.TraceForge.AI.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.checkerframework.checker.interning.qual.InternedDistinct;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@NoArgsConstructor
public class document {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter
    @Setter
    private String repoName;

    @Getter
    @Setter
    private String file_path;

    @Getter
    @Setter
    private String markdown;

    public document(String repoName, String filePath, String outputMd) {
        this.repoName = repoName;
        this.file_path = filePath;
        this.markdown = outputMd;
    }
}
