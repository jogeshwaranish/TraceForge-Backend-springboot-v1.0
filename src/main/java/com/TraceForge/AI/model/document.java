package com.TraceForge.AI.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Markdowns")
public class document implements Serializable {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter
    private String repoName;
    @Getter
    private String file_path;
    @Lob
    @Getter
    private String markdown;

    public document(String repoName, String filePath, String outputMd) {
        this.repoName = repoName;
        this.file_path = filePath;
        this.markdown = outputMd;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", repoName='" + repoName + '\'' +
                ", file_path='" + file_path + '\'' +
                ", markdown='" + markdown + '\'';
    }

// getters and setters (Lombok already generates them)
}
