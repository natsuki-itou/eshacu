package dev.eshacu.domain.repository;

import dev.eshacu.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByPublishedAtIsNotNullOrderByPublishedAtDesc(Pageable pageable);
}
