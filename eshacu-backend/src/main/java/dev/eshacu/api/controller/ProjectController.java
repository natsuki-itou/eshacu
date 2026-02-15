package dev.eshacu.api.controller;

import dev.eshacu.api.dto.common.PageResponse;
import dev.eshacu.api.dto.project.ProjectCardDto;
import dev.eshacu.api.dto.project.ProjectDetailDto;
import dev.eshacu.api.mapper.ProjectMapper;
import dev.eshacu.domain.Project;
import dev.eshacu.domain.repository.ProjectRepository;
import dev.eshacu.api.dto.common.TagDto;
import dev.eshacu.api.dto.ErrorResponse;
import dev.eshacu.api.exception.NotFoundException;

import jakarta.persistence.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: GPT任せ 修正必要そう
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectRepository repo;
    private final ProjectMapper mapper;
    private final ProjectMapper.MarkdownService md;

    public ProjectController(ProjectRepository repo, ProjectMapper mapper, ProjectMapper.MarkdownService md) {
        this.repo = repo;
        this.mapper = mapper;
        this.md = md;
    }

    @GetMapping
    public PageResponse<ProjectCardDto> list(@RequestParam(defaultValue="0") int page,
                                             @RequestParam(defaultValue="10") int size) {

        PageRequest pageable = PageRequest.of(page, size);

        Page<Project> resuletPage = repo.findByPublishedAtIsNotNullOrderByPublishedAtDesc(pageable);

        List<ProjectCardDto> items = resuletPage.map(mapper::toCard).getContent();

        return new PageResponse<>(
                items,
                resuletPage.getNumber(),
                resuletPage.getSize(),
                resuletPage.getTotalElements()
        );
    }

    @GetMapping("/{id}")
    public ProjectDetailDto get(@PathVariable Long id){
        Project e = repo.findById(id).orElseThrow(() -> new NotFoundException("Project not found" + id));
        return mapper.toDetail(e,md);
    }
}
