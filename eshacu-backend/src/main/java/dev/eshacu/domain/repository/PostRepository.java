package dev.eshacu.domain.repository;

import dev.eshacu.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
    Page<Post> findByPublishedTrueOrderByPublishedAtDesc(Pageable pageable);
}
