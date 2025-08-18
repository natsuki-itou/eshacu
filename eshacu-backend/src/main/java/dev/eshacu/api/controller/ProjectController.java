package dev.eshacu.api.controller;

import dev.eshacu.api.dto.common.PageResponse;
import dev.eshacu.api.dto.common.TagDto;
import dev.eshacu.api.dto.project.ProjectCardDto;
import dev.eshacu.api.mapper.ProjectMapper;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;

// TODO: GPT任せ 修正必要そう
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectMapper mapper;
    public ProjectController(ProjectMapper mapper) { this.mapper = mapper; }

    @GetMapping
    public PageResponse<ProjectCardDto> list(@RequestParam(defaultValue="0") int page,
                                             @RequestParam(defaultValue="10") int size) {
        // スタブデータ（#11 でDB化）
        var dto = new ProjectCardDto(
                1L, "Sample", "Summary", List.of(new TagDto("java"), new TagDto("spring")),
                Instant.now(), null, "https://github.com/...", "https://app.example"
        );
        return new PageResponse<>(List.of(dto), page, size, 1);
    }
}
