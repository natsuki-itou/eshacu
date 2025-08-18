package dev.eshacu.api.dto.project;

import dev.eshacu.api.dto.common.TagDto;

import java.time.Instant;
import java.util.List;

public record ProjectCardDto(
        Long id, String title, String summary, List<TagDto> tags,
        Instant updatedAt, String heroImageUrl, String repoUrl, String appUrl) {
}
