package com.TraceForge.AI.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@NoArgsConstructor
public class document {
    @Getter
    @Setter
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
}
