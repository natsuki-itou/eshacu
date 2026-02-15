package dev.eshacu.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(length = 500)
    private String summary;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String bodyMarkdown;

    private String heroImageUrl;
    private String repoUrl;
    private String appUrl;

    @ElementCollection
    private List<String> tags;
    @ElementCollection
    private List<String> techStack;
    @ElementCollection
    private List<String> images;

    private Instant publishedAt;
    private Instant updatedAt;

    // getters/setters
    @PrePersist void prePersist(){ this.updatedAt = Instant.now(); }
    @PreUpdate  void preUpdate(){  this.updatedAt = Instant.now(); }
}
