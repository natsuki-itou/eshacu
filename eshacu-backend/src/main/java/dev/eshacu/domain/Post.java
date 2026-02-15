package dev.eshacu.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String bodyMarkdown;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    private Boolean published = false;
    private Instant publishedAt;
    private Instant updatedAt;

    @PrePersist void prePersist() { this.updatedAt = Instant.now(); }
    @PreUpdate  void preUpdate() {  this.updatedAt = Instant.now(); }
}
