package dev.eshacu.api.dto.project;

import dev.eshacu.api.dto.common.TagDto;

import java.time.Instant;
import java.util.List;

public record ProjectDetailDto(Long id,
                               String title,
                               String excerpt,
                               String bodyHtml,
                               List<String> techStack,
                               List<String> images,
                               List<TagDto> tags,
                               Instant publishedAt) {
}
